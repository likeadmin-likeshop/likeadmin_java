package com.mdd.front.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.entity.user.User;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.NoticeEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.user.UserAuthMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.plugin.notice.NoticeCheck;
import com.mdd.common.plugin.wechat.WxMnpDriver;
import com.mdd.common.util.*;
import com.mdd.front.cache.ScanLoginCache;
import com.mdd.front.service.ILoginService;
import com.mdd.front.vo.login.LoginTokenVo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpOAuth2ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 登录服务实现类
 */
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    UserMapper userMapper;

    @Resource
    UserAuthMapper userAuthMapper;

    /**
     * 注册账号
     *
     * @author fzr
     * @param username 账号
     * @param password 密码
     * @param terminal 总端
     */
    @Override
    public void register(String username, String password, Integer terminal) {
        User model = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,sn,username")
                .eq("username", username)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.isNull(model, "账号已存在,换一个吧!");

        Integer sn  = this.__generateSn();
        String salt = ToolUtils.randomString(5);
        String pwd  = ToolUtils.makeMd5(password+salt);

        User user = new User();
        user.setSn(sn);
        user.setNickname("用户"+sn);
        user.setUsername(username);
        user.setPassword(pwd);
        user.setSalt(salt);
        user.setAvatar("/api/static/default_avatar.png");
        user.setChannel(terminal);
        user.setIsNew(1);
        user.setCreateTime(System.currentTimeMillis() / 1000);
        user.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.insert(user);
    }

    /**
     * 账号登录
     *
     * @author fzr
     * @param username 账号
     * @param password 密码
     * @return LoginTokenVo
     */
    @Override
    public LoginTokenVo accountLogin(String username, String password, Integer terminal) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,password,salt,mobile,is_disable,is_new")
                .eq("username", username)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user, "账号不存在!");
        String pwd = ToolUtils.makeMd5(password+user.getSalt());
        Assert.isFalse(!pwd.equals(user.getPassword()), "账号或密码错误!");
        Assert.isFalse(!user.getIsDisable().equals(0), "账号已被禁用!");

        return this.__loginToken(user.getId(), user.getMobile(), user.getIsNew(), terminal);
    }

    /**
     * 手机号登录
     *
     * @author fzr
     * @param mobile 手机号
     * @param code 验证码
     * @return LoginTokenVo
     */
    @Override
    public LoginTokenVo mobileLogin(String mobile, String code, Integer terminal) {
        // 校验验证码
        int sceneCode = NoticeEnum.LOGIN_CODE.getCode();
        if (!NoticeCheck.verify(sceneCode, code)) {
            throw new OperateException("验证码错误!");
        }

        // 查询手机号
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,mobile,is_disable,is_new")
                .eq("mobile", mobile)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user, "账号不存在!");
        Assert.isFalse(user.getIsDisable() != 0, "账号已禁用!");

        return this.__loginToken(user.getId(), user.getMobile(), user.getIsNew(), terminal);
    }

    /**
     * 微信小程序登录
     *
     * @author fzr
     * @param code 编码
     * @param terminal 终端
     * @return LoginTokenVo
     */
    @Override
    @Transactional
    public LoginTokenVo mnpLogin(String code, Integer terminal) {
        try {
            WxMaService wxMaService = WxMnpDriver.mnp();
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
            String openId = sessionResult.getOpenid();
            String uniId = sessionResult.getUnionid();
            String unionId = uniId == null ? "0" : uniId;

            return this.__wxLoginHandle(openId, unionId, terminal);
        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }

    /**
     * 公众号登录
     *
     * @author fzr
     * @param code 编码
     * @param terminal 终端
     * @return LoginTokenVo
     */
    @Override
    public LoginTokenVo officeLogin(String code, Integer terminal) {
        try {
            WxMpService wxMpService = WxMnpDriver.oa();
            WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
            String uniId = wxOAuth2AccessToken.getUnionId();
            String openId  = wxOAuth2AccessToken.getOpenId();
            String unionId = uniId == null ? "0" : uniId;
            return this.__wxLoginHandle(openId, unionId, terminal);
        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return String
     */
    @Override
    public String oaCodeUrl(String url) {
        WxMpService wxMpService = WxMnpDriver.oa();
        WxMpOAuth2ServiceImpl wxMpOAuth2Service = new WxMpOAuth2ServiceImpl(wxMpService);
        return wxMpOAuth2Service.buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }

    /**
     * 扫码链接
     *
     * @author fzr
     * @param session session
     * @return String
     */
    @Override
    public String scanCodeUrl(String url, HttpSession session) {
        // 获取AppId
        String appId = ConfigUtils.get("op_channel", "appId", "");

        // 微信开放平台授权
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 回调地址
        String redirectUrl = url;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new OperateException(e.getMessage());
        }

        // 防止csrf攻击
        String state = ToolUtils.makeUUID().replaceAll("-", "");
        ScanLoginCache.set(session.getId(), state);

        //生成QrcodeUrl
        return String.format(baseUrl, appId, redirectUrl, state);
    }

    /**
     * 扫码登录
     *
     * @author fzr
     * @param code 编码
     * @param state 标识
     * @param terminal 终端
     * @param session 会话
     */
    @Override
    public LoginTokenVo scanLogin(String code, String state, Integer terminal, HttpSession session) {
        if (!ScanLoginCache.get(session.getId()).equals(state)) {
            throw new OperateException("二维码已失效或不存在,请重新操作");
        }

        // 得到配置和授权临时票据code
        String appId = ConfigUtils.get("op_channel", "appId", "");
        String appSecret = ConfigUtils.get("op_channel", "appSecret", "");

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        Map<String, String> resultMap;
        try {
            String accessTokenUrl = String.format(baseAccessTokenUrl, appId, appSecret, code);
            String result = HttpUtils.sendGet(accessTokenUrl);
            resultMap = MapUtils.jsonToMap(result);
        } catch (Exception e) {
            throw new OperateException("获取access_token失败:"+e.getMessage());
        }

        // 访问微信获取用户信息 (openId,unionId,昵称,头像等)
        String accessToken = resultMap.get("access_token");
        String openid = resultMap.get("openid");
        String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
        Map<String, String> userinfoMap;
        try {
            String resultUserInfo = HttpUtils.sendGet(userInfoUrl);
            userinfoMap = MapUtils.jsonToMap(resultUserInfo);
        } catch (Exception e) {
            throw new OperateException("获取用户信息失败:"+e.getMessage());
        }

        String openId  = userinfoMap.get("openid");
        String uniId   = userinfoMap.get("unionid");
        String unionId = uniId == null ? "0" : uniId;
        return this.__wxLoginHandle(openId, unionId, terminal);
    }

    /**
     * 处理微信登录
     *
     * @param openId   (openId)
     * @param unionId  (unionId)
     * @param terminal (terminal)
     * @return LoginTokenVo
     */
    private LoginTokenVo __wxLoginHandle(String openId, String unionId, Integer terminal) {
        // 查询授权
        UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                .nested(wq->wq
                    .eq("unionid", unionId).or()
                    .eq("openid", openId)
                ).last("limit 1"));

        // 查询用户
        User user = null;
        if (StringUtils.isNotNull(userAuth)) {
            user = userMapper.selectOne(new QueryWrapper<User>()
                    .eq("is_delete", 0)
                    .eq("id", userAuth.getUserId())
                    .last("limit 1"));
        }

        // 创建用户
        if (StringUtils.isNull(user)) {
            Integer sn = this.__generateSn();
            User model = new User();
            model.setSn(sn);
            model.setAvatar("/api/static/default_avatar.png");
            model.setNickname("用户" + sn);
            model.setUsername("u" + sn);
            model.setChannel(terminal);
            model.setSex(0);
            model.setLastLoginIp(IpUtils.getHostIp());
            model.setLastLoginTime(System.currentTimeMillis() / 1000);
            model.setUpdateTime(System.currentTimeMillis() / 1000);
            model.setCreateTime(System.currentTimeMillis() / 1000);
            model.setIsNew(1);
            userMapper.insert(model);
            user = model;
        }

        // 终端授权
        UserAuth auth = userAuthMapper.selectOne(
                new QueryWrapper<UserAuth>()
                        .eq("openid", openId)
                        .eq("terminal", terminal)
                        .last("limit 1"));

        // 创建授权
        if (StringUtils.isNull(auth)) {
            UserAuth authModel = new UserAuth();
            authModel.setUserId(user.getId());
            authModel.setUnionid(unionId);
            authModel.setOpenid(openId);
            authModel.setTerminal(terminal);
            authModel.setCreateTime(System.currentTimeMillis() / 1000);
            authModel.setUpdateTime(System.currentTimeMillis() / 1000);
            userAuthMapper.insert(authModel);
        } else if (StringUtils.isEmpty(auth.getUnionid())) {
            auth.setUnionid(unionId);
            auth.setUpdateTime(System.currentTimeMillis() / 1000);
            userAuthMapper.updateById(auth);
        }

        return this.__loginToken(user.getId(), user.getMobile(), user.getIsNew(), terminal);
    }

    /**
     * 处理录令牌
     *
     * @author fzr
     * @param userId 用户ID
     * @param mobile 用户手机
     * @param terminal 终端
     * @return LoginTokenVo
     */
    private LoginTokenVo __loginToken(Integer userId, String mobile, Integer isNew, Integer terminal) {
        // 实现账号登录
        StpUtil.login(userId, String.valueOf(terminal));

        // 更新登录信息
        User user = new User();
        user.setLastLoginIp(IpUtils.getHostIp());
        user.setLastLoginTime(System.currentTimeMillis() / 1000);
        userMapper.update(user, new QueryWrapper<User>().eq("id", userId));

        // 返回登录信息
        LoginTokenVo vo = new LoginTokenVo();
        vo.setId(userId);
        vo.setIsBindMobile(!StringUtils.isEmpty(mobile));
        vo.setToken(StpUtil.getTokenValue());
        vo.setIsNew(isNew);
        return vo;
    }

    /**
     * 生成用户编号
     *
     * @author fzr
     * @return Integer
     */
    private Integer __generateSn() {
        Integer sn;
        while (true) {
            sn = Integer.parseInt(ToolUtils.randomInt(8));
            User snModel = userMapper.selectOne(new QueryWrapper<User>()
                    .select("id,sn")
                    .eq("sn", sn)
                    .last("limit 1"));
            if (snModel == null) {
                break;
            }
        }
        return sn;
    }

}
