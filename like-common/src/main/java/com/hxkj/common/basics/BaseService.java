package com.hxkj.common.basics;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 基础服务接口类
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 设置搜索条件
     *
     * @author fzr
     * @param queryWrapper 条件构造器
     * @param params 参数[条件:键@数据库字段:类型]
     * @param conditions 条件
     */
    default void setSearch(QueryWrapper<T> queryWrapper, Map<String, String> params, String[] conditions) {

        for (String condition : conditions) {
            String[] array   = condition.split(":");
            String type      = array.length > 2 ? array[2].trim() : "";
            String where     = array[0].trim();
            String[] keyArr  = array[1].trim().split("@");
            String key       = keyArr[0].trim();
            String field     = keyArr.length > 1 ? keyArr[1].trim() : keyArr[0].trim();
            String value     = params.getOrDefault(key, "");

            if (value.equals("")) {
                continue;
            }

            if ((!type.equals("") && !Arrays.asList("int", "long", "str").contains(type))) {
                System.out.println("搜索参数类型不在固定值内[int,long,str]");
                continue;
            }

            Object val = value;
            switch (where) {
                case "=":
                case "<>":
                case ">":
                case ">=":
                case "<":
                case "<=":
                    if (type.equals("int")) {
                        val = Integer.parseInt(value);
                    } else if (type.equals("long")) {
                        val = Long.parseLong(value);
                    }
                case "in":
                case "notIn":
                    if (type.equals("int")) {
                        List<Integer> intData = new ArrayList<>();
                        for (String v : value.split(",")) {
                            intData.add(Integer.parseInt(v.trim()));
                        }
                        val = intData;
                    } else if (type.equals("long")){
                        List<Long> longData = new ArrayList<>();
                        for (String v : value.split(",")) {
                            longData.add(Long.parseLong(v.trim()));
                        }
                        val = longData;
                    }
            }

            switch (where) {
                case "=":
                    queryWrapper.eq(field, val);
                    break;
                case "<>":
                    queryWrapper.ne(field, val);
                    break;
                case ">":
                    queryWrapper.gt(field, val);
                    break;
                case ">=":
                    queryWrapper.ge(field, val);
                    break;
                case "<":
                    queryWrapper.lt(field, val);
                    break;
                case "<=":
                    queryWrapper.le(field, val);
                    break;
                case "between":
                    String[] betArr = value.split(",");
                    if (type.equals("int")) {
                        queryWrapper.between(field, Integer.parseInt(betArr[0]), Integer.parseInt(betArr[1]));
                    } else if (type.equals("long")){
                        queryWrapper.between(field, Long.parseLong(betArr[0]), Long.parseLong(betArr[1]));
                    } else {
                        queryWrapper.between(field, betArr[0], betArr[1]);
                    }
                    break;
                case "notBetween":
                    String[] notBetArr = value.split(",");
                    if (type.equals("int")) {
                        queryWrapper.notBetween(field, Integer.parseInt(notBetArr[0]), Integer.parseInt(notBetArr[1]));
                    } else if (type.equals("long")){
                        queryWrapper.notBetween(field, Long.parseLong(notBetArr[0]), Long.parseLong(notBetArr[1]));
                    } else {
                        queryWrapper.notBetween(field, notBetArr[0], notBetArr[1]);
                    }
                    break;
                case "like":
                    queryWrapper.like(field, val);
                    break;
                case "notLike":
                    queryWrapper.notLike(field, val);
                    break;
                case "likeLeft":
                    queryWrapper.likeLeft(field, val);
                    break;
                case "likeRight":
                    queryWrapper.likeRight(field, val);
                    break;
                case "in":
                    queryWrapper.in(field, val);
                    break;
                case "notIn":
                    queryWrapper.notIn(field, val);
                    break;
            }
        }
    }

    /**
     * 求和聚合
     *
     * @param field 字段名
     * @param queryWrapper 条件构造器
     * @return Long
     */
    default BigDecimal sum(String field, QueryWrapper<T> queryWrapper) {
        queryWrapper.select("IFNULL(sum("+field+"), 0) as totalValue");
        Map<String, Object> map = this.getMap(queryWrapper);
        return (BigDecimal) map.get("totalValue");
    }

}
