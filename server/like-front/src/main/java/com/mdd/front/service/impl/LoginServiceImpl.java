package com.mdd.front.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.entity.user.User;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.NoticeEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.user.UserAuthMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.util.*;
import com.mdd.front.config.FrontConfig;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.login.RegisterValidate;
import com.mdd.front.validate.login.ForgetPwdValidate;
import com.mdd.front.validate.login.ScanLoginValidate;
import com.mdd.front.vo.LoginTokenVo;
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
     * @param registerValidate 参数
     */
    @Override
    public void register(RegisterValidate registerValidate) {
        User model = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,sn,username")
                .eq("username", registerValidate.getUsername())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.isNull(model, "账号已存在,换一个吧!");

        Integer sn  = this.randMakeSn();
        String salt = ToolsUtils.randomString(5);
        String pwd  = ToolsUtils.makeMd5(registerValidate.getPassword()+salt);

        User user = new User();
        user.setSn(sn);
        user.setNickname("用户"+sn);
        user.setUsername(registerValidate.getUsername());
        user.setPassword(pwd);
        user.setSalt(salt);
        user.setAvatar("/api/static/default_avatar.png");
        user.setChannel(registerValidate.getClient());
        user.setCreateTime(System.currentTimeMillis() / 1000);
        user.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.insert(user);
    }

    /**
     * 账号登录
     *
     * @author fzr
     * @param params 参数
     * @return LoginTokenVo
     */
    @Override
    public LoginTokenVo accountLogin(Map<String, String> params) {
        Assert.notNull(params.get("username"), "username参数缺失!");
        Assert.notNull(params.get("password"), "password参数缺失!");
        String username = params.get("username");
        String password = params.get("password");

        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,password,salt,mobile,is_disable")
                .eq("username", username)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user, "账号不存在!");
        String pwd = ToolsUtils.makeMd5(password+user.getSalt());
        Assert.isFalse(!pwd.equals(user.getPassword()), "账号或密码错误!");
        Assert.isFalse(user.getIsDisable() != 0, "账号已被禁用!");

        // 更新登录信息
        user.setLastLoginIp(IpUtils.getHostIp());
        user.setLastLoginTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(user);

        return this.makeLoginToken(user.getId(), user.getMobile());
    }

    /**
     * 手机号登录
     *
     * @author fzr
     * @param params 参数
     * @return LoginTokenVo
     */
    @Override
    public LoginTokenVo mobileLogin(Map<String, String> params) {
        Assert.notNull(params.get("mobile"), "mobile参数缺失!");
        Assert.notNull(params.get("code"), "code参数缺失!");
        String mobile = params.get("mobile");
        String code   = params.get("code").toLowerCase();

        // 校验验证码
        int typeCode = NoticeEnum.SMS_LOGIN_CODE.getCode();
        Object smsCode = RedisUtils.get(GlobalConfig.redisSmsCode+typeCode+":"+mobile);
        if (StringUtils.isNull(smsCode) || !smsCode.toString().equals(code)) {
            throw new OperateException("验证码错误!");
        }

        // 删除验证码
        RedisUtils.del(GlobalConfig.redisSmsCode+typeCode+":"+mobile);

        // 查询手机号
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,mobile,is_disable")
                .eq("mobile", mobile)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user, "账号不存在!");
        Assert.isFalse(user.getIsDisable() != 0, "账号已禁用!");

        // 更新登录信息
        user.setLastLoginIp(IpUtils.getHostIp());
        user.setLastLoginTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(user);

        return this.makeLoginToken(user.getId(), user.getMobile());
    }

    /**
     * 微信小程序登录
     *
     * @author fzr
     * @param code 微信code
     * @param client 来源客户端
     * @return LoginTokenVo
     */
    @Override
    @Transactional
    public LoginTokenVo mnpLogin(String code, Integer client) {
        try {
            WxMaService wxMaService = WeChatUtils.mnp();
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
            String openId = sessionResult.getOpenid();
            String uniId = sessionResult.getUnionid();
            String unionId = uniId == null ? "0" : uniId;

            UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                    .nested(wq->wq
                        .eq("openid", openId).or()
                        .eq("unionid", unionId)
                    ).last("limit 1"));

            User user = null;
            Integer userId;
            if (StringUtils.isNotNull(userAuth)) {
                 user = userMapper.selectOne(new QueryWrapper<User>()
                        .eq("id", userAuth.getUserId())
                        .eq("is_delete", 0)
                        .last("limit 1"));
            }

            if (StringUtils.isNull(user)) {
                Integer sn  = this.randMakeSn();
                User model = new User();
                model.setSn(sn);
                model.setAvatar("/api/static/default_avatar.png");
                model.setNickname("用户"+sn);
                model.setUsername("u"+sn);
                model.setSex(0);
                model.setChannel(client);
                model.setLastLoginIp(IpUtils.getHostIp());
                model.setLastLoginTime(System.currentTimeMillis() / 1000);
                model.setCreateTime(System.currentTimeMillis() / 1000);
                model.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.insert(model);
                user = model;
                userId = model.getId();

                if (StringUtils.isNull(userAuth)) {
                    UserAuth auth = new UserAuth();
                    auth.setUserId(model.getId());
                    auth.setOpenid(openId);
                    auth.setUnionid(unionId.equals("0") ? "" : unionId);
                    auth.setClient(client);
                    auth.setCreateTime(System.currentTimeMillis() / 1000);
                    auth.setUpdateTime(System.currentTimeMillis() / 1000);
                    userAuthMapper.insert(auth);
                }
            } else {
                // 更新微信标识
                userId = user.getId();
                if (StringUtils.isEmpty(userAuth.getUnionid()) && StringUtils.isNotEmpty(sessionResult.getUnionid())) {
                    userAuth.setUnionid(sessionResult.getUnionid());
                    userAuthMapper.updateById(userAuth);
                }

                // 更新登录信息
                user.setLastLoginIp(IpUtils.getHostIp());
                user.setLastLoginTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(user);
            }


            return this.makeLoginToken(userId, user.getMobile());
        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }

    /**
     * 公众号登录
     *
     * @author fzr
     * @return LoginTokenVo
     */
    @Override
    public LoginTokenVo officeLogin(String code, Integer client) {
        try {
            WxMpService wxMpService = WeChatUtils.official();
            WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
            String uniId = wxOAuth2AccessToken.getUnionId();
            String openId  = wxOAuth2AccessToken.getOpenId();
            String unionId = uniId == null ? "0" : uniId;

            UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                    .nested(wq->wq
                        .eq("unionid", unionId).or()
                        .eq("openid", openId)
                    ).last("limit 1"));

            Integer userId;
            User user = null;
            if (StringUtils.isNotNull(userAuth)) {
                user = userMapper.selectOne(new QueryWrapper<User>()
                        .eq("is_delete", 0)
                        .eq("id", userAuth.getUserId())
                        .last("limit 1"));
            }

            if (StringUtils.isNull(user)) {
                Integer sn  = this.randMakeSn();
                User model = new User();
                model.setSn(sn);
                model.setAvatar("/api/static/default_avatar.png");
                model.setNickname("用户" + sn);
                model.setUsername("u" + sn);
                model.setChannel(client);
                model.setSex(0);
                model.setLastLoginIp(IpUtils.getHostIp());
                model.setLastLoginTime(System.currentTimeMillis() / 1000);
                model.setUpdateTime(System.currentTimeMillis() / 1000);
                model.setCreateTime(System.currentTimeMillis() / 1000);
                userMapper.insert(model);
                userId = model.getId();
                user = model;

                if (StringUtils.isNull(userAuth)) {
                    UserAuth auth = new UserAuth();
                    auth.setUserId(model.getId());
                    auth.setUnionid(unionId);
                    auth.setOpenid(openId);
                    auth.setClient(ClientEnum.OA.getCode());
                    auth.setCreateTime(System.currentTimeMillis() / 1000);
                    auth.setUpdateTime(System.currentTimeMillis() / 1000);
                    userAuthMapper.insert(auth);
                }
            } else {
                // 更新微信标识
                userId = user.getId();
                if (StringUtils.isEmpty(userAuth.getUnionid()) && StringUtils.isNotEmpty(unionId)) {
                    userAuth.setUnionid(unionId);
                    userAuthMapper.updateById(userAuth);
                }

                // 更新登录信息
                user.setLastLoginIp(IpUtils.getHostIp());
                user.setLastLoginTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(user);
            }

            return this.makeLoginToken(userId, user.getMobile());
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
    public String codeUrl(String url) {
        WxMpService wxMpService = WeChatUtils.official();
        WxMpOAuth2ServiceImpl wxMpOAuth2Service = new WxMpOAuth2ServiceImpl(wxMpService);
        return wxMpOAuth2Service.buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }

    /**
     * 忘记密码
     *
     * @author fzr
     * @param forgetPwdValidate 参数
     */
    @Override
    public void forgotPassword(ForgetPwdValidate forgetPwdValidate) {
        String mobile = forgetPwdValidate.getMobile();
        String code = forgetPwdValidate.getCode();
        String password = forgetPwdValidate.getPassword();

        // 校验验证码
        int typeCode = NoticeEnum.SMS_FORGOT_PASSWORD_CODE.getCode();
        Object smsCode = RedisUtils.get(GlobalConfig.redisSmsCode+typeCode+":"+mobile);
        if (StringUtils.isNull(smsCode) || !smsCode.toString().equals(code)) {
            throw new OperateException("验证码错误!");
        }

        // 删除验证码
        RedisUtils.del(GlobalConfig.redisSmsCode+typeCode+":"+mobile);

        // 查询手机号
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,mobile,is_disable")
                .eq("is_delete", 0)
                .eq("mobile", mobile)
                .last("limit 1"));

        // 验证账号
        Assert.notNull(user, "账号不存在!");

        String salt = ToolsUtils.randomString(5);
        String pwd  = ToolsUtils.makeMd5(password.trim()+salt);

        // 更新密码
        user.setPassword(pwd);
        user.setSalt(salt);
        user.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(user);
    }

    /**
     * 扫码链接
     *
     * @author fzr
     * @param session session
     * @return String
     */
    @Override
    public String getScanCode(HttpSession session) {
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
        String redirectUrl = "https://www.baidu.com/";
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new OperateException(e.getMessage());
        }

        // 防止csrf攻击
        String state = ToolsUtils.makeUUID().replaceAll("-", "");
        RedisUtils.set("wechat-open-state-"+session.getId(), state, 600);

        //生成qrcodeUrl
        return String.format(baseUrl, appId, redirectUrl, state);
    }

    /**
     * 扫码登录
     *
     * @author fzr
     * @param scanLoginValidate 参数
     */
    @Override
    public void scanLogin(ScanLoginValidate scanLoginValidate, HttpSession session) {
        Object o = RedisUtils.get("wechat-open-state-"+session.getId());
        if (StringUtils.isNull(o) || !o.toString().equals(scanLoginValidate.getState())) {
            throw new OperateException("二维码已失效或不存在,请重新操作");
        }

        // 得到配置和授权临时票据code
        String code = scanLoginValidate.getCode();
        String appId = ConfigUtils.get("op_channel", "appId", "");
        String appSecret = ConfigUtils.get("op_channel", "appSecret", "");

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String result = null;
        try {
            String accessTokenUrl = String.format(baseAccessTokenUrl, appId, appSecret, code);
            result = HttpUtils.sendGet(accessTokenUrl);
        } catch (Exception e) {
            throw new OperateException("获取access_token失败:"+e.getMessage());
        }

        String accessToken = "";
        String openid = "";

        // 访问微信获取用户信息
        String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
        String resultUserInfo = null;
        try {
            resultUserInfo = HttpUtils.sendGet(userInfoUrl);
        } catch (Exception e) {
            throw new OperateException("获取用户信息失败:"+e.getMessage());
        }
    }

    /**
     * 生成登录Token
     *
     * @author fzr
     * @param userId 用户ID
     * @param mobile 用户手机
     * @return LoginTokenVo
     */
    private LoginTokenVo makeLoginToken(Integer userId, String mobile) {
        String token = ToolsUtils.makeToken();
        int tokenValidTime = Integer.parseInt(YmlUtils.get("like.token-valid-time"));
        RedisUtils.set(FrontConfig.frontendTokenKey+token, userId, tokenValidTime);

        LoginTokenVo vo = new LoginTokenVo();
        vo.setId(userId);
        vo.setIsBindMobile(!mobile.equals(""));
        vo.setToken(token);
        return vo;
    }

    /**
     * 生成用户编号
     *
     * @author fzr
     * @return Integer
     */
    private Integer randMakeSn() {
        Integer sn;
        while (true) {
            sn = Integer.parseInt(ToolsUtils.randomInt(8));
            User snModel = userMapper.selectOne(new QueryWrapper<User>()
                    .select("id,sn,username")
                    .eq("sn", sn)
                    .last("limit 1"));
            if (snModel == null) {
                break;
            }
        }
        return sn;
    }

}
