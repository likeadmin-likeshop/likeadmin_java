package com.mdd.generator.config;

public class GenConfig {

    // 基础包名
    public static String packageName = "com.mdd";

    // 后台应用
    public static String adminPackage   = "like-admin/src/main/java/com/mdd/admin";

    // 公共应用
    public static String commonPackage = "like-common/src/main/java/com/mdd/common";

    // 是否去除表前缀
    public static Boolean isRemoveTablePrefix = true;

    // 注释类型[top=顶, right=右]
    public static String notesType = "right";

}
