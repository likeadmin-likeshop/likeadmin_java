package com.mdd.common.util;


import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class TimeUtils {

    /**
     * 时间戳转日期(默认格式)
     *
     * @author fzr
     * @param time 时间戳
     * @return String
     */
    public static String timestampToDate(Long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time * 1000));
    }

    /**
     * 时间戳转日期(默认格式)
     *
     * @author fzr
     * @param time 时间戳
     * @return String
     */
    public static String timestampToDate(String time) {
        if (time == null) {
            time = "0";
        }
        long longTime = Long.parseLong(time);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(longTime * 1000));
    }

    /**
     * 时间戳转日期(指定格式)
     *
     * @author fzr
     * @param time 时间戳
     * @param format 格式串
     * @return String
     */
    public static String timestampToDate(Long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time * 1000));
    }

    /**
     * 时间戳转日期(指定格式)
     *
     * @author fzr
     * @param time 时间戳
     * @param format 格式串
     * @return String
     */
    public static String timestampToDate(String time, String format) {
        long longTime = Long.parseLong(time);
        return new SimpleDateFormat(format).format(new Date(longTime * 1000));
    }

    /**
     * 日期转时间戳
     *
     * @author fzr
     * @param date 日期
     * @return Long
     */
    public static Long dateToTimestamp(String date) {
        String dateTime = TimeUtils.formatDate(date);
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateTime, new ParsePosition(0)).getTime() / 1000;
    }

    /**
     * 毫秒转日期时间
     *
     * @author fzr
     * @param time 毫秒
     * @return String
     */
    public static String millisecondToDate(Long time) {
        Date date = new Date();
        date.setTime(time);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 毫秒转日期时间
     *
     * @author fzr
     * @param time 毫秒
     * @return String
     */
    public static String millisecondToDate(String time) {
        Date date = new Date();
        date.setTime(Long.parseLong(time));
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 毫秒转日期时间
     *
     * @author fzr
     * @param time 毫秒
     * @param format 格式串
     * @return String
     */
    public static String millisecondToDate(Long time, String format) {
        Date date = new Date();
        date.setTime(time);
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 毫秒转日期时间
     *
     * @author fzr
     * @param time 毫秒
     * @param format 格式串
     * @return String
     */
    public static String millisecondToDate(String time, String format) {
        Date date = new Date();
        date.setTime(Long.parseLong(time));
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期转固定格式 yyyy-MM-dd HH:mm:ss
     *
     * @author fzr
     * @param dateStr 日期时间
     * @return String
     */
    public static String formatDate(String dateStr){
        dateStr = dateStr.trim();
        HashMap<String, String> dateRegFormat = new HashMap<>();
        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH-mm");//2014-03-12 12:05
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH");//2014-03-12 12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2014-03-12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2014-03
        dateRegFormat.put("^\\d{4}$", "yyyy");//2014
        dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
        dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
        dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
        dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
        dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm-ss");//13:05:34 拼接当前日期
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05 拼接当前日期
        dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatter2;
        String dateReplace;
        String strSuccess = "";
        try {
            for (String key : dateRegFormat.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
                    if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$") || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {
                        // 13:05:34 或 13:05 拼接当前日期
                        dateStr = curDate + "-" + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {
                        //21.1 (日.月) 拼接当前年份
                        dateStr = curDate.substring(0, 4) + "-" + dateStr;
                    }
                    dateReplace = dateStr.replaceAll("\\D+", "-");
                    strSuccess = formatter1.format(formatter2.parse(dateReplace));
                    break;
                }
            }
            return strSuccess;
        } catch (Exception ignored) { }
        return "";
    }

    /**
     * 返回当前时间戳
     *
     * @author fzr
     * @return Long
     */
    public static Long timestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 返回当前Date型日期
     *
     *  @author fzr
     * @return Date() 当前日期
     */
    public static Date nowDate() {
        return new Date();
    }

    /**
     * 返回今日开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> today() {
        List<Long> list = new ArrayList<>();
        // 开始时间
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        list.add(todayStart.getTime().getTime() / 1000 - 43200);

        // 结束时间
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        list.add(todayEnd.getTime().getTime() / 1000);

        return list;
    }

    /**
     * 返回昨日开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> yesterday() {
        List<Long> today = TimeUtils.today();
        List<Long> list = new ArrayList<>();
        list.add(today.get(0) - 86400);
        list.add(today.get(1) - 86400);
        return list;
    }

    /**
     * 返回本周开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> week() {
        List<Long> list = new ArrayList<>();
        // 开始时间
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        list.add(cal.getTimeInMillis() / 1000);
        // 结束时间
        list.add(list.get(0) + ((7 * 24 * 60 * 60 * 1000) / 1000)-1);
        return list;
    }

    /**
     * 返回上周开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> lastWeek() {
        List<Long> week = TimeUtils.week();
        List<Long> list = new ArrayList<>();
        list.add(week.get(0) - 604800);
        list.add(week.get(1) - 604800);
        return list;
    }

    /**
     * 返回今天是周几
     *
     * @author fzr
     * @return Long
     */
    public static Long dayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
        long week = cal.get(Calendar.DAY_OF_WEEK);
        if(isFirstSunday){
            week = (week -1) == 0? 7:(week - 1);
        }
        return week;
    }

    /**
     * 返回本月开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> month() {
        List<Long> list = new ArrayList<>();
        // 开始时间
        Calendar calStart = Calendar.getInstance();
        calStart.set(calStart.get(Calendar.YEAR),calStart.get(Calendar.MONTH), calStart.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        calStart.set(Calendar.DAY_OF_MONTH,calStart.getActualMinimum(Calendar.DAY_OF_MONTH));
        list.add(calStart.getTimeInMillis() / 1000);

        // 结束时间
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(calEnd.get(Calendar.YEAR),calEnd.get(Calendar.MONTH), calEnd.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        calEnd.set(Calendar.DAY_OF_MONTH, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        calEnd.set(Calendar.HOUR_OF_DAY, 24);
        list.add(calEnd.getTimeInMillis() / 1000 - 1);
        return list;
    }

    /**
     * 返回上个月开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> lastMonth() {
        List<Long> list = new ArrayList<>();
        // 开始时间
        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.MONTH, -1);
        calStart.set(Calendar.DAY_OF_MONTH,1);
        calStart.set(Calendar.HOUR, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        list.add((calStart.getTimeInMillis() / 1000) - 43200);

        // 结束时间
        Calendar calEnd = Calendar.getInstance();
        calEnd.set(calEnd.get(Calendar.YEAR),calEnd.get(Calendar.MONTH), calEnd.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        calEnd.set(Calendar.DAY_OF_MONTH,calEnd.getActualMinimum(Calendar.DAY_OF_MONTH));
        list.add((calEnd.getTimeInMillis() / 1000)-1);
        return list;
    }

    /**
     * 返回今年开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> year() {
        List<Long> list = new ArrayList<>();
        // 开始时间
        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.YEAR, 0);
        calStart.add(Calendar.DATE, 0);
        calStart.add(Calendar.MONTH, 0);
        calStart.set(Calendar.DAY_OF_YEAR, 1);
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        list.add(calStart.getTimeInMillis() / 1000);

        // 结束时间
        Calendar calEnd = Calendar.getInstance();
        int year = calEnd.get(Calendar.YEAR);
        calEnd.clear();
        calEnd.set(Calendar.YEAR, year);
        calEnd.set(Calendar.HOUR_OF_DAY, 23);
        calEnd.set(Calendar.MINUTE, 59);
        calEnd.set(Calendar.SECOND, 59);
        calEnd.set(Calendar.MILLISECOND, 999);
        calEnd.roll(Calendar.DAY_OF_YEAR, -1);
        list.add(calEnd.getTimeInMillis() / 1000);
        return list;
    }

    /**
     * 返回去年开始和结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> lastYear() {
        List<Long> list = new ArrayList<>();
        // 开始时间
        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.YEAR, -1);
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.DAY_OF_YEAR, 1);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        list.add(calStart.getTimeInMillis() / 1000);

        // 结束时间
        Calendar calEnd = Calendar.getInstance();
        calEnd.add(Calendar.YEAR, -1);
        calEnd.set(Calendar.MONTH, calEnd.getActualMaximum(Calendar.MONTH));
        calEnd.set(Calendar.DAY_OF_MONTH, calEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        calEnd.set(Calendar.HOUR_OF_DAY, 23);
        calEnd.set(Calendar.MINUTE, 59);
        calEnd.set(Calendar.SECOND, 59);
        calEnd.set(Calendar.MILLISECOND, 999);
        list.add(calEnd.getTimeInMillis() / 1000);
        return list;
    }

    /**
     * 获取几天前零点到现在/昨日结束的时间戳
     *
     * @author fzr
     * @return List
     */
    public static List<Long> dayToNow(int day) {
        List<Long> today = TimeUtils.today();
        List<Long> list = new ArrayList<>();
        list.add(today.get(0) - day * 86400L);
        list.add(today.get(0) -1);
        return list;
    }

    /**
     * 返回几天前的时间戳
     *
     * @author fzr
     * @param day (天)
     * @return Long
     */
    public Long daysAgo(long day) {
        long currTime = System.currentTimeMillis() / 1000;
        return currTime - (day * 86400);
    }

    /**
     * 返回几天后的时间戳
     *
     * @author fzr
     * @param day (天)
     * @return Long
     */
    public Long daysAfter(long day) {
        long currTime = System.currentTimeMillis() / 1000;
        return currTime + (day * 86400);
    }

    /**
     * 返回月份第几天
     *
     * @author fzr
     * @return int
     */
    public static Integer monthDay(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取几天前的日期列表
     *
     * @author fzr
     * @param day 获取多少天
     * @return [2022-03-29, 2022-03-30, 2022-03-31, 2022-04-01]
     */
    public static List<String> daysAgoDate(Integer day) {
        long time = TimeUtils.today().get(0);

        List<String> data = new ArrayList<>();
        for (int i=0; i<day; i++) {
            if (i != 0) {
                time -= 86400;
            }

            data.add(TimeUtils.timestampToDate(time, "yyyy-MM-dd"));
        }

        Collections.reverse(data);
        return data;
    }

    /**
     * 获取几天前的日期列表
     *
     * @author fzr
     * @param day 获取多少天
     * @return [1648483200, 1648569600, 1648656000, 1648742400]
     */
    public static List<Long> daysAgoTime(Integer day) {
        long time = TimeUtils.today().get(0);

        List<Long> data = new ArrayList<>();
        for (int i=0; i<day; i++) {
            if (i != 0) {
                time -= 86400;
            }

            data.add(time);
        }

        Collections.reverse(data);
        return data;
    }

    /**
     * 返回服务器启动时间
     */
    public static Date serverStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     *
     * @author fzr
     * @param endDate 结束时间
     * @param nowDate 开始时间
     * @return String
     */
    public static String datePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
