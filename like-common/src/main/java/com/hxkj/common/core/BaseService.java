package com.hxkj.common.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface BaseService<T> extends IService<T> {

    default void setSearch(QueryWrapper<T> queryWrapper, Map<String, String> params, String[] conditions) {

        for (String condition : conditions) {
            String[] array = condition.split(":");
            String type   = array[0].trim();
            String where  = array[2].trim();
            String[] arr  = array[1].trim().split("@");
            String key    = arr[0].trim();
            String field  = arr.length > 1 ? arr[1].trim() : arr[0].trim();
            String value  = params.getOrDefault(key, "");

            if (value.equals("")) {
                continue;
            }

            Object val = value;
            switch (type) {
                case "int":
                    val = Integer.parseInt(value);
                    break;
                case "long":
                    val = Long.parseLong(value);
                    break;
                case "strList":
                    val = Arrays.asList(value.split(","));
                    break;
                case "intList":
                    List<Integer> intData = new ArrayList<>();
                    for (String v : value.split(",")) {
                        intData.add(Integer.parseInt(v.trim()));
                    }
                    val = intData;
                    break;
                case "longList":
                    List<Long> longData = new ArrayList<>();
                    for (String v : value.split(",")) {
                        longData.add(Long.parseLong(v.trim()));
                    }
                    val = longData;
                    break;
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
                    queryWrapper.between(field, Integer.parseInt(betArr[0]),  Integer.parseInt(betArr[1]));
                    break;
                case "notBetween":
                    String[] notBetArr = value.split(",");
                    queryWrapper.notBetween(field, Integer.parseInt(notBetArr[0]),  Integer.parseInt(notBetArr[1]));
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

}
