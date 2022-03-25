package com.hxkj.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     *
     * @author fzr
     * @param throwable 异常
     * @return String
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
