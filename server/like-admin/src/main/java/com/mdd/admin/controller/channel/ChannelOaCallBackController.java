package com.mdd.admin.controller.channel;

import com.mdd.admin.service.IChannelOaCallBackService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("api/channel/oa")
@Api(tags = "公众号服务器验证及消息回复")
public class ChannelOaCallBackController {


    @Resource
    private IChannelOaCallBackService iChannelOaCallBackService;

    // 公众号服务器验证， 消息回复
    @GetMapping(name = "/callback", produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        return iChannelOaCallBackService.checkSignature(signature, timestamp, nonce, echostr);
    }


    // 消息回复
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {

        return iChannelOaCallBackService.post(requestBody, signature, timestamp, nonce, encType, msgSignature);
    }


}
