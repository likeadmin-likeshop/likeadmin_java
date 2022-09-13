package com.mdd.admin.service.channel.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import com.google.common.collect.Maps;
import com.mdd.admin.service.channel.IChannelOaMenuService;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMenuServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChannelOaMenuServiceImpl implements IChannelOaMenuService {

    @Override
    public Object list() {

        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId("");
        wxMpDefaultConfig.setSecret("");
        wxMpDefaultConfig.setToken("");
        wxMpDefaultConfig.setAesKey("");

        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(wxMpDefaultConfig);

        try {
            WxMpMenuService wxMpMenuService = new WxMpMenuServiceImpl(service);

            WxMpGetSelfMenuInfoResult result = wxMpMenuService.getSelfMenuInfo();
            System.out.println(result);
        } catch (WxErrorException e) {
            System.out.println("哈哈哈哈哈");
            System.out.println(e.getError());
        }

        return null;
    }

}
