package com.mdd.common.core;

import com.mdd.common.entity.server.*;
import com.mdd.common.utils.ArithUtil;
import com.mdd.common.utils.IpUtil;
import com.mdd.common.utils.TimeUtil;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 服务器相关信息
 */
public class ServerResult {

    private static final int OSHI_WAIT_SECOND = 1000;

    private static final long KB = 1024;
    private static final long MB = 1024 * 1024;
    private static final long GB = 1024 * 1024 * 1024;

    private final Cpu cpu = new Cpu(); // CPU相关信息
    private final Mem mem = new Mem(); // 內存相关信息
    private final Jvm jvm = new Jvm(); // JVM相关信息
    private final Sys sys = new Sys(); // 服务器相关信息
    private final List<Disk> disk = new LinkedList<>(); // 磁盘相关信息

    /**
     * 拷贝数据
     */
    public Map<String, Object> copyTo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        setCpuInfo(hal.getProcessor());
        setMemInfo(hal.getMemory());
        setSysInfo();
        setJvmInfo();
        setSysFiles(si.getOperatingSystem());

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("cpu", this.cpu);
        map.put("mem", this.mem);
        map.put("sys", this.sys);
        map.put("disk", this.disk);
        map.put("jvm", this.jvm);
        return map;
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor) {
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softer = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long ioWait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + ioWait + irq + softer + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(ArithUtil.round(ArithUtil.mul(totalCpu, 100), 2));
        cpu.setSys(ArithUtil.round(ArithUtil.mul(cSys / cpu.getTotal(), 100), 2));
        cpu.setUsed(ArithUtil.round(ArithUtil.mul(user / cpu.getTotal(), 100), 2));
        cpu.setWait(ArithUtil.round(ArithUtil.mul(ioWait / cpu.getTotal(), 100), 2));
        cpu.setFree( ArithUtil.round(ArithUtil.mul(idle / cpu.getTotal(), 100), 2));
    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memory) {
        int number = (1024 * 1024 * 1024);
        mem.setTotal(ArithUtil.div(memory.getTotal(), number, 2));
        mem.setUsed(ArithUtil.div(memory.getTotal() - memory.getAvailable(), number, 2));
        mem.setFree(ArithUtil.div(memory.getAvailable(), number, 2));
        mem.setUsage(ArithUtil.mul(ArithUtil.div(mem.getUsed(), memory.getTotal(), 4), 100));
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo() {
        Properties props = System.getProperties();
        sys.setComputerName(IpUtil.getHostName());
        sys.setComputerIp(IpUtil.getHostIp());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo() {
        Properties props = System.getProperties();
        jvm.setTotal(ArithUtil.div(Runtime.getRuntime().totalMemory(), (1024 * 1024), 2));
        jvm.setMax(ArithUtil.div(Runtime.getRuntime().maxMemory(), (1024 * 1024), 2));
        jvm.setFree(ArithUtil.div(Runtime.getRuntime().freeMemory(), (1024 * 1024), 2));
        jvm.setUsage(ArithUtil.mul(ArithUtil.div(jvm.getTotal() - jvm.getFree(), jvm.getTotal(), 4), 100));
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
        jvm.setName(ManagementFactory.getRuntimeMXBean().getVmName());
        jvm.setInputArgs(ManagementFactory.getRuntimeMXBean().getInputArguments().toString());
        jvm.setRunTime(TimeUtil.datePoor(TimeUtil.nowDate(), TimeUtil.serverStartDate()));
        jvm.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(TimeUtil.serverStartDate()));
    }

    /**
     * 设置磁盘信息
     */
    private void setSysFiles(OperatingSystem os) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray)
        {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            Disk sysFile = new Disk();
            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(convertFileSize(total));
            sysFile.setFree(convertFileSize(free));
            sysFile.setUsed(convertFileSize(used));
            sysFile.setUsage(ArithUtil.mul(ArithUtil.div(used, total, 4), 100));
            disk.add(sysFile);
        }
    }

    /**
     * 字节转换
     */
    public String convertFileSize(long size) {

        if (size >= GB)
        {
            return String.format("%.1f GB", (float) size / GB);
        }

        if (size >= MB)
        {
            float f = (float) size / MB;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        }

        if (size >= KB)
        {
            float f = (float) size / KB;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        }

        return String.format("%d B", size);
    }

}
