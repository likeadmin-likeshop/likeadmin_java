package com.mdd.common.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * 系统文件配置操作工具
 */
@Component
public class YmlUtil implements EnvironmentAware {

    private static Environment env;

    /**
     * 设置环境变量
     *
     * @author fzr
     * @param environment 环境变量
     */
    @Override
    public void setEnvironment(Environment environment) {
        YmlUtil.env = environment;
    }

    /**
     * 根据Key获取值
     *
     * @author fzr
     * @param key 键
     * @return String
     */
    public static String get(String key) {
        return env.getProperty(key);
    }

}
