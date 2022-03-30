package com.hxkj.admin.service.impl;

import com.hxkj.admin.config.SystemConfig;
import com.hxkj.admin.service.ISystemAdminService;
import com.hxkj.admin.service.ISystemLoginService;
import com.hxkj.admin.validate.SysLoginParam;
import com.hxkj.common.entity.system.SystemAdmin;
import com.hxkj.common.enums.HttpEnum;
import com.hxkj.common.exception.LoginException;
import com.hxkj.common.exception.OperateException;
import com.hxkj.common.utils.HttpUtil;
import com.hxkj.common.utils.RedisUtil;
import com.hxkj.common.utils.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ISystemLoginServiceImpl implements ISystemLoginService {

    @Resource
    ISystemAdminService iSystemAdminService;

    /**
     * 登录
     *
     * @author fzr
     * @param sysLoginParam 登录参数
     * @return token
     */
    @Override
    public Map<String, Object> login(SysLoginParam sysLoginParam) {
        String username = sysLoginParam.getUsername();
        String password = sysLoginParam.getPassword();

        SystemAdmin sysAdmin = iSystemAdminService.findByUsername(username);
        if (sysAdmin == null || sysAdmin.getIsDelete() == 1) {
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        if (sysAdmin.getIsDisable() == 1) {
            throw new LoginException(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
        }

        String newPWd = password + sysAdmin.getId() + sysAdmin.getSalt();
        String md5Pwd = ToolsUtil.makeMd5(newPWd);
        if (!md5Pwd.equals(sysAdmin.getPassword())) {
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        try {
            sysAdmin.setLastLoginIp(HttpUtil.ip());
            sysAdmin.setLastLoginTime(System.currentTimeMillis() / 1000);
            iSystemAdminService.updateById(sysAdmin);

            String token = ToolsUtil.makeToken();
            RedisUtil.set(SystemConfig.backstageTokenKey+token, sysAdmin.getId(), 7200);
            iSystemAdminService.cacheAdminUserByUid(sysAdmin.getId());

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("token", token);
            return response;
        } catch (Exception e) {
            throw new OperateException(e.getMessage());
        }
    }

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    @Override
    public void logout(String token) {
        RedisUtil.del(SystemConfig.backstageTokenKey + token);
    }

}
