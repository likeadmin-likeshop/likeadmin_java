package com.mdd.front.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.config.GlobalConfig;
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
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IUserService;
import com.mdd.front.validate.users.*;
import com.mdd.front.vo.users.UserCenterVo;
import com.mdd.front.vo.users.UserInfoVo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @Resource
    UserAuthMapper userAuthMapper;

    /**
     * 个人中心
     *
     * @author fzr
     * @param userId 用户ID
     * @return UserCenterVo
     */
    @Override
    public UserCenterVo center(Integer userId, Integer terminal) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,sn,avatar,real_name,nickname,username,mobile,money,is_new,password")
                .eq("id", userId)
                .last("limit 1"));

        UserCenterVo vo = new UserCenterVo();
        BeanUtils.copyProperties(user, vo);
        if (user.getAvatar().equals("")) {
            String avatar = ConfigUtils.get("user", "defaultAvatar", "");
            vo.setAvatar(UrlUtils.toAbsoluteUrl(avatar));
        } else {
            vo.setAvatar(UrlUtils.toAbsoluteUrl(user.getAvatar()));
        }

        vo.setIsBindWechat(false);
        if (terminal.equals(ClientEnum.OA.getCode()) || terminal.equals(ClientEnum.MNP.getCode())) {
            UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                    .select("id,openid,terminal")
                    .eq("user_id", userId)
                    .eq("terminal", terminal)
                    .last("limit 1"));
            vo.setIsBindWechat(userAuth != null);
        }

        // 是否有设置登录密码
        vo.setHasPwd(StringUtils.isNotBlank(user.getPassword()));

        return vo;
    }

    /**
     * 个人信息
     *
     * @author fzr
     * @param userId 用户ID
     * @return UserInfoVo
     */
    @Override
    public UserInfoVo info(Integer userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,sn,avatar,real_name,nickname,username,mobile,password,sex,create_time")
                .eq("id", userId)
                .last("limit 1"));

        UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                .select("id,openid")
                .eq("user_id", userId)
                .last("limit 1"));

        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(user, vo);
        vo.setIsPassword(!user.getPassword().equals(""));
        vo.setIsBindMnp(userAuth != null);
        vo.setVersion(GlobalConfig.version);
        vo.setSex(user.getSex());
        vo.setCreateTime(TimeUtils.timestampToDate(user.getCreateTime()));

        if (!user.getAvatar().equals("")) {
            vo.setAvatar(UrlUtils.toAbsoluteUrl(user.getAvatar()));
        } else {
            String avatar = ConfigUtils.get("user", "defaultAvatar", "");
            vo.setAvatar(UrlUtils.toAbsoluteUrl(avatar));
        }

        return vo;
    }

    /**
     * 编辑信息
     *
     * @author fzr
     * @param updateValidate 参数
     * @param userId 用户ID
     */
    @Override
    public void edit(UserUpdateValidate updateValidate, Integer userId) {
        String field = updateValidate.getField();
        String value = updateValidate.getValue();

        switch (field) {
            case "avatar":
                User avatarUser = new User();
                avatarUser.setId(userId);
                avatarUser.setAvatar(UrlUtils.toRelativeUrl(value));
                avatarUser.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(avatarUser);
                break;
            case "username":
                User usernameUser = userMapper.selectOne(new QueryWrapper<User>()
                        .select("id,username")
                        .eq("username", value)
                        .eq("is_delete", 0)
                        .last("limit 1"));

                if (StringUtils.isNotNull(usernameUser) && !usernameUser.getId().equals(userId)) {
                    throw new OperateException("账号已被使用!");
                }

                if (StringUtils.isNotNull(usernameUser) && usernameUser.getUsername().equals(value)) {
                    throw new OperateException("新账号与旧账号一致,修改失败!");
                }

                User u = new User();
                u.setId(userId);
                u.setUsername(value);
                u.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(u);
                break;
            case "nickname":
                User nicknameUser = new User();
                nicknameUser.setId(userId);
                nicknameUser.setNickname(value);
                nicknameUser.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(nicknameUser);
                break;
            case "sex":
                User sexUser = new User();
                sexUser.setId(userId);
                sexUser.setSex(Integer.parseInt(value));
                sexUser.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(sexUser);
                break;
            default:
                throw new OperateException("不被支持的类型");
        }
    }

    /**
     * 修改密码
     *
     * @author fzr
     * @param password 新密码
     * @param userId 用户ID
     */
    @Override
    public void changePwd(String password, String oldPassword, Integer userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,password,salt")
                .eq("id", userId)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals("")) {
            Assert.notNull(oldPassword, "oldPassword参数缺失");
            String oldPwd = ToolUtils.makeMd5(oldPassword.trim() + user.getSalt());
            if (!oldPwd.equals(user.getPassword())) {
                throw new OperateException("原密码不正确!");
            }
        }

        String salt = ToolUtils.randomString(5);
        String pwd  = ToolUtils.makeMd5(password.trim()+salt);

        User u = new User();
        u.setId(userId);
        u.setPassword(pwd);
        u.setSalt(salt);
        u.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(u);
    }

    /**
     * 忘记密码
     *
     * @author fzr
     * @param password 新密码
     * @param mobile 手机号
     * @param code 验证码
     */
    @Override
    public void forgotPwd(String password, String mobile, String code) {
        // 校验验证码
        int sceneCode = NoticeEnum.FORGOT_PASSWORD_CODE.getCode();
        if (!NoticeCheck.verify(sceneCode, code)) {
            throw new OperateException("验证码错误!");
        }

        // 查询手机号
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,mobile,is_disable")
                .eq("is_delete", 0)
                .eq("mobile", mobile)
                .last("limit 1"));

        // 验证账号
        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(user, "账号不存在!");

        String salt = ToolUtils.randomString(5);
        String pwd  = ToolUtils.makeMd5(password.trim()+salt);

        // 更新密码
        user.setPassword(pwd);
        user.setSalt(salt);
        user.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(user);
    }

    /**
     * 绑定手机
     *
     * @author fzr
     * @param mobileValidate 参数
     * @param userId 用户ID
     */
    @Override
    public void bindMobile(UserPhoneBindValidate mobileValidate, Integer userId) {
        String type   = mobileValidate.getType();
        String mobile = mobileValidate.getMobile();
        String code   = mobileValidate.getCode().toLowerCase();

        // 校验验证码
        int sceneCode = type.equals("bind") ? NoticeEnum.BIND_MOBILE_CODE.getCode() : NoticeEnum.CHANGE_MOBILE_CODE.getCode() ;
        if (!NoticeCheck.verify(sceneCode, code)) {
            throw new OperateException("验证码错误!");
        }

        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,username,mobile")
                .eq("mobile", mobile)
                .eq("is_delete", 0)
                .last("limit 1"));

        if (StringUtils.isNotNull(user) && user.getId().equals(userId)) {
            throw new OperateException("手机号已被其它账号绑定!");
        }

        User u = new User();
        u.setId(userId);
        u.setMobile(mobile);
        u.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(u);
    }

    /**
     * 微信手机号
     *
     * @author fzr
     * @param code 获取手机号的Code
     */
    @Override
    public void mnpMobile(String code) {
        Map<String, String> config = ConfigUtils.get("mp_channel");
        WxMaService wxMaService = new WxMaServiceImpl();
        WxMaDefaultConfigImpl wxConfig = new WxMaDefaultConfigImpl();
        wxConfig.setSecret(config.getOrDefault("appSecret", ""));
        wxConfig.setAppid(config.getOrDefault("appId", ""));
        wxMaService.setWxMaConfig(wxConfig);

        try {
            WxMaPhoneNumberInfo wxMaPhoneNumberInfo = wxMaService.getUserService().getNewPhoneNoInfo(code);

            Integer userId = LikeFrontThreadLocal.getUserId();
            User user = new User();
            user.setId(userId);
            user.setMobile(wxMaPhoneNumberInfo.getPhoneNumber());
            user.setUpdateTime(System.currentTimeMillis() / 1000);
            userMapper.updateById(user);
        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }


    /**
     * 更新新用户昵称头像等信息
     *
     * @param newUserUpdateValidate 参数
     * @param userId 用户id
     */
    @Override
    public void updateNewUserInfo(NewUserUpdateValidate newUserUpdateValidate, Integer userId) {
        User user = new User();
        user.setId(userId);
        user.setNickname(newUserUpdateValidate.getNickname());
        user.setAvatar(UrlUtils.toRelativeUrl(newUserUpdateValidate.getAvatar()));
        user.setIsNew(0);
        user.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(user);
    }

    /**
     * 绑定小程序
     *
     * @param bindMnpValidate 参数
     * @param userId 用户ID
     */
    @Override
    public void bindMnp(UserBindWechatValidate bindMnpValidate, Integer userId) {
        try {
            // 通过code获取微信信息
            String code = bindMnpValidate.getCode();
            WxMaService wxMaService = WxMnpDriver.mnp();
            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
            String openId = sessionResult.getOpenid();
            String uniId = sessionResult.getUnionid();
            String unionId = uniId == null ? "0" : uniId;

            // 授权校验,未授权创建授权，已授权返回
            bindWechatAuth(openId, unionId, ClientEnum.MNP.getCode(), userId);

        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }

    /**
     * 绑定公众号
     *
     * @param bindOaValidate 参数
     * @param userId 用户ID
     */
    @Override
    public void bindOa(UserBindWechatValidate bindOaValidate, Integer userId) {
        try {
            // 通过code获取微信信息
            WxMpService wxMpService = WxMnpDriver.oa();
            WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(bindOaValidate.getCode());
            String uniId = wxOAuth2AccessToken.getUnionId();
            String openId  = wxOAuth2AccessToken.getOpenId();
            String unionId = uniId == null ? "0" : uniId;

            // 授权校验,未授权创建授权，已授权返回
            bindWechatAuth(openId, unionId, ClientEnum.OA.getCode(), userId);

        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }

    /**
     * 绑定微信授权
     *
     * @param openId openId
     * @param unionId unionId
     * @param terminal 客户端端
     * @param userId 用户ID
     */
    public void bindWechatAuth(String openId, String unionId, Integer terminal, Integer userId) {
        // 授权表中查找授权记录
        UserAuth userAuthOpenId = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                .eq("openid", openId)
                .last("limit 1"));

        if (userAuthOpenId != null) {
            // 该微信已绑定
            throw new OperateException("该微信已绑定");
        }

        // 已有授权，返回已绑定微信。 没有授权，绑定微信
        if (!StringUtils.isBlank(unionId)) {
            UserAuth userAuthUnionId = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                    .eq("unionid", unionId)
                    .last("limit 1"));

            if (userAuthUnionId != null && !userId.equals(userAuthUnionId.getUserId())) {
                // 该微信已绑定
                throw new OperateException("该微信已绑定");
            }
        }

        // 记录微信授权
        UserAuth authModel = new UserAuth();
        authModel.setUserId(userId);
        authModel.setUnionid(unionId);
        authModel.setOpenid(openId);
        authModel.setTerminal(terminal);
        authModel.setCreateTime(System.currentTimeMillis() / 1000);
        authModel.setUpdateTime(System.currentTimeMillis() / 1000);
        userAuthMapper.insert(authModel);
    }

}
