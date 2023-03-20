package com.mdd.front.service;

import com.mdd.front.validate.RechargeValidate;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * 充值余额接口服务类
 */
public interface IRechargeService {

    Map<String, Object> placeOrder(Integer userId, Integer terminal, RechargeValidate rechargeValidate);

}
