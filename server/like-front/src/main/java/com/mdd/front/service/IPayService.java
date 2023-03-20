package com.mdd.front.service;

/**
 * 支付接口服务类
 */
public interface IPayService {

    void prepay(String scene, Integer orderId, Integer terminal) throws Exception;

}
