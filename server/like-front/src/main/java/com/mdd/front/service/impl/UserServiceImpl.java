package com.mdd.front.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.entity.user.User;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.system.SystemConfigMapper;
import com.mdd.common.mapper.user.UserAuthMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.front.service.IUserService;
import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
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
    public UserCenterVo center(Integer userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id,sn,avatar,real_name,nickname,username,mobile")
                .eq("id", userId)
                .last("limit 1"));

        UserCenterVo vo = new UserCenterVo();
        BeanUtils.copyProperties(user, vo);
        if (user.getAvatar().equals("")) {
            String avatar = ConfigUtil.get("user", "defaultAvatar", "");
            vo.setAvatar(UrlUtil.toAbsoluteUrl(avatar));
        } else {
            vo.setAvatar(UrlUtil.toAbsoluteUrl(user.getAvatar()));
        }

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
                .eq("client", ClientEnum.MNP.getCode())
                .last("limit 1"));

        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(user, vo);
        vo.setIsPassword(!user.getPassword().equals(""));
        vo.setIsBindMnp(userAuth != null);
        vo.setVersion(GlobalConfig.version);
        vo.setSex(user.getSex());
        vo.setCreateTime(TimeUtil.timestampToDate(user.getCreateTime()));

        if (!user.getAvatar().equals("")) {
            vo.setAvatar(UrlUtil.toAbsoluteUrl(user.getAvatar()));
        } else {
            String avatar = ConfigUtil.get("user", "defaultAvatar", "");
            vo.setAvatar(UrlUtil.toAbsoluteUrl(avatar));
        }

        return vo;
    }

    /**
     * 编辑信息
     *
     * @author fzr
     * @param params 参数
     * @param userId 用户ID
     */
    @Override
    public void edit(Map<String, String> params, Integer userId) {
        String field = params.getOrDefault("field", "").trim();
        String value =  params.getOrDefault("value", "").trim();

        switch (field) {
            case "avatar":
                User avatarUser = new User();
                avatarUser.setId(userId);
                avatarUser.setAvatar(UrlUtil.toRelativeUrl(value));
                avatarUser.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(avatarUser);
                break;
            case "username":
                User usernameUser = userMapper.selectOne(new QueryWrapper<User>()
                        .select("id,username")
                        .eq("username", value)
                        .eq("is_delete", 0)
                        .last("limit 1"));

                if (StringUtil.isNotNull(usernameUser) && !usernameUser.getId().equals(userId)) {
                    throw new OperateException("账号已被使用!");
                }

                if (StringUtil.isNotNull(usernameUser) && usernameUser.getUsername().equals(value)) {
                    throw new OperateException("新账号与旧账号一致,修改失败!");
                }

                usernameUser.setId(userId);
                usernameUser.setUsername(value);
                usernameUser.setUpdateTime(System.currentTimeMillis() / 1000);
                userMapper.updateById(usernameUser);
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
     * 微信手机号
     *
     * @author fzr
     * @param code 获取手机号的Code
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> mnpMobile(String code) {
        Map<String, String> config = ConfigUtil.get("mp_channel");
        WxMaService wxMaService = new WxMaServiceImpl();
        WxMaDefaultConfigImpl wxConfig = new WxMaDefaultConfigImpl();
        wxConfig.setSecret(config.getOrDefault("appSecret", ""));
        wxConfig.setAppid(config.getOrDefault("appId", ""));
        wxMaService.setWxMaConfig(wxConfig);

        try {
            WxMaPhoneNumberInfo wxMaPhoneNumberInfo = wxMaService.getUserService().getNewPhoneNoInfo(code);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("countryCode", wxMaPhoneNumberInfo.getCountryCode());
            response.put("phoneNumber", wxMaPhoneNumberInfo.getPhoneNumber());
            return response;
        } catch (WxErrorException e) {
            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
        }
    }

}
