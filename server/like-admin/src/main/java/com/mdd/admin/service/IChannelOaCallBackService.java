package com.mdd.admin.service;


/**
 * 公众号服务器回调
 */
public interface IChannelOaCallBackService {


    /**
     * 服务器验证
     */
    String checkSignature(String signature, String timestamp, String nonce, String echostr);


    /**
     * 消息回复
     *
     * @param requestBody
     * @param signature
     * @param timestamp
     * @param nonce
     * @param encType
     * @param msgSignature
     * @return
     */
    String post(String requestBody, String signature, String timestamp, String nonce, String encType, String msgSignature);

}
