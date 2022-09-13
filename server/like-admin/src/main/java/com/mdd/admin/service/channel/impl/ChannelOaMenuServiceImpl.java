package com.mdd.admin.service.channel.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.google.common.collect.Maps;
import com.mdd.admin.service.channel.IChannelOaMenuService;
import com.mdd.common.exception.OperateException;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.ToolsUtil;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
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

import java.util.LinkedList;
import java.util.List;
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

    @Override
    public void save(List<Object> objs, Boolean isPublish) {
        if (objs.size() > 3) {
            throw new OperateException("一级菜单超出限制(最多3个)");
        }

        List<Map<String, String>> params = new LinkedList<>();
        for (Object o : objs) {
            params.add(ToolsUtil.objectToMap(o));
        }

        List<WxMenuButton> menuButtons = new LinkedList<>();
        for (Map<String, String> item : params) {
            // 一级菜单
            Assert.notNull(item.get("name"), "一级菜单名称不能为空");
            WxMenuButton wxMenuButton = new WxMenuButton();
            if (Integer.parseInt(item.get("menuType")) == 1) {
                Assert.notNull(item.get("visitType"), "一级菜单visitType数缺失");
                if (item.get("visitType").equals("miniprogram")) {
                    Assert.notNull(item.get("appId"), "一级菜单appId参数缺失");
                    Assert.notNull(item.get("url"), "一级菜单url数缺失");
                    Assert.notNull(item.get("pagePath"), "一级菜单pagePath数缺失");
                    wxMenuButton.setType(item.get("visitType"));
                    wxMenuButton.setAppId(item.get("appId"));
                    wxMenuButton.setUrl(item.get("url"));
                    wxMenuButton.setPagePath(item.get("pagePath"));
                } else {
                    Assert.notNull(item.get("url"), "一级菜单url数缺失");
                    wxMenuButton.setType(item.get("visitType"));
                    wxMenuButton.setUrl(item.get("url"));
                }
                menuButtons.add(wxMenuButton);
            }

             // 子级菜单
            if (Integer.parseInt(item.get("menuType")) == 2) {
                Assert.notNull(item.get("subButtons"), "子级菜单不能为空");
                List<Map<String, String>> subButtons = ArrayUtil.stringToListAsMapStr(item.get("subButtons"));

                if (subButtons.size() > 5) {
                    throw new OperateException("子级菜单超出限制(最多5个)");
                }
                for (Map<String, String> subItem : subButtons) {
                    WxMenuButton subMenuButton = new WxMenuButton();
                    if (Integer.parseInt(subItem.get("menuType")) == 1) {
                        Assert.notNull(subItem.get("visitType"), "子级菜单visitType参数缺失!");
                        if (subItem.get("visitType").equals("miniprogram")) {
                            Assert.notNull(subItem.get("appId"), "子级菜单appId参数缺失!");
                            Assert.notNull(subItem.get("url"), "子级菜单url数缺失!");
                            Assert.notNull(subItem.get("pagePath"), "子级菜单pagePath数缺失!");
                            wxMenuButton.setType(subItem.get("visitType"));
                            wxMenuButton.setAppId(subItem.get("appId"));
                            wxMenuButton.setUrl(subItem.get("url"));
                            wxMenuButton.setPagePath(subItem.get("pagePath"));
                        } else {
                            Assert.notNull(subItem.get("url"), "子级菜单url数缺失");
                            wxMenuButton.setType(subItem.get("visitType"));
                            wxMenuButton.setUrl(subItem.get("url"));
                        }
                        menuButtons.add(subMenuButton);
                    }
                }
            }
        }

        ConfigUtil.set("oa_channel", "menus", JSON.toJSONString(menuButtons));

        if (isPublish) {
            WxMenu wxMenu = new WxMenu();
            wxMenu.setButtons(menuButtons);
        }
    }

}
