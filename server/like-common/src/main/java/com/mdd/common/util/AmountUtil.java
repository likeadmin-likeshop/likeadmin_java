package com.mdd.common.util;

import com.baomidou.mybatisplus.core.toolkit.Assert;

import java.math.BigDecimal;

/**
 * 金额处理工具
 */
public class AmountUtil {

    private AmountUtil() {}

    /**
     * 将单位为元的金额转换为单位为分
     *
     * @param yuan 单位为元的字符型值
     * @return int
     */
    public static int yuan2Fen(String yuan) {
        int value;

        try {
            BigDecimal var1 = new BigDecimal(yuan);
            BigDecimal var2 = new BigDecimal(100);
            BigDecimal var3 = var1.multiply(var2);
            value = Integer.parseInt(var3.stripTrailingZeros().toPlainString());
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("非法金额[%s]", yuan));
        }

        Assert.isTrue(value >= 0, String.format("非法金额[%s]", yuan));
        return value;
    }

    /**
     * 将单位为分的金额转换为单位为元
     *
     * @param fen 单位为分的字符型值
     * @return String
     */
    public static String fen2Yuan(int fen) {
        BigDecimal var1 = new BigDecimal(fen);
        BigDecimal var2 = new BigDecimal(100);
        BigDecimal var3 = var1.divide(var2, 3);
        return var3.stripTrailingZeros().toPlainString();
    }

    /**
     * 将单位为分的金额转换为单位为元
     *
     * @param fen 单位为分的字符型值
     * @return BigDecimal
     */
    public static BigDecimal fen2YuanDecimal(int fen) {
        BigDecimal var1 = new BigDecimal(fen);
        BigDecimal var2 = new BigDecimal(100);
        return var1.divide(var2, 3);
    }

}
