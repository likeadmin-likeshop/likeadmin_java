package com.hxkj.admin.service.impl;

import com.hxkj.admin.config.SystemConfig;
import com.hxkj.admin.service.ISystemAdminService;
import com.hxkj.admin.service.ISystemLoginService;
import com.hxkj.admin.validate.system.SystemLoginParam;
import com.hxkj.common.entity.system.SystemAdmin;
import com.hxkj.common.entity.system.SystemLogLogin;
import com.hxkj.common.enums.HttpEnum;
import com.hxkj.common.exception.LoginException;
import com.hxkj.common.exception.OperateException;
import com.hxkj.common.mapper.system.SystemAdminMapper;
import com.hxkj.common.mapper.system.SystemLogLoginMapper;
import com.hxkj.common.utils.*;
import nl.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ISystemLoginServiceImpl implements ISystemLoginService {

    @Resource
    SystemLogLoginMapper systemLogLoginMapper;

    @Resource
    SystemAdminMapper systemAdminMapper;

    @Resource
    ISystemAdminService iSystemAdminService;


    private static final Logger log = LoggerFactory.getLogger(ISystemLoginServiceImpl.class);

    /**
     * 登录
     *
     * @author fzr
     * @param systemLoginParam 登录参数
     * @return token
     */
    @Override
    public Map<String, Object> login(SystemLoginParam systemLoginParam) {
        String username = systemLoginParam.getUsername();
        String password = systemLoginParam.getPassword();

        SystemAdmin sysAdmin = iSystemAdminService.findByUsername(username);
        if (sysAdmin == null || sysAdmin.getIsDelete() == 1) {
            this.recordLoginLog(0, systemLoginParam.getUsername(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        if (sysAdmin.getIsDisable() == 1) {
            this.recordLoginLog(sysAdmin.getId(), systemLoginParam.getUsername(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
        }

        String newPWd = password + sysAdmin.getId() + sysAdmin.getSalt();
        String md5Pwd = ToolsUtil.makeMd5(newPWd);
        if (!md5Pwd.equals(sysAdmin.getPassword())) {
            this.recordLoginLog(sysAdmin.getId(), systemLoginParam.getUsername(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        try {
            sysAdmin.setLastLoginIp(HttpUtil.ip());
            sysAdmin.setLastLoginTime(System.currentTimeMillis() / 1000);
            systemAdminMapper.updateById(sysAdmin);

            String token = ToolsUtil.makeToken();
            RedisUtil.set(SystemConfig.backstageTokenKey+token, sysAdmin.getId(), 7200);
            iSystemAdminService.cacheAdminUserByUid(sysAdmin.getId());

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("token", token);

            this.recordLoginLog(sysAdmin.getId(), systemLoginParam.getUsername(), "");

            return response;
        } catch (Exception e) {
            Integer adminId = StringUtil.isNotNull(sysAdmin.getId()) ? sysAdmin.getId() : 0;
            String error = StringUtil.isEmpty(e.getMessage()) ? "未知错误" : e.getMessage();
            this.recordLoginLog(adminId, systemLoginParam.getUsername(), error);
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

    /**
     * 记录登录日志
     */
    private void recordLoginLog(Integer adminId, String username, String error) {
        try {
            HttpServletRequest request = Objects.requireNonNull(HttpUtil.obj());
            final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

            SystemLogLogin model = new SystemLogLogin();
            model.setAdminId(adminId);
            model.setUsername(username);
            model.setIp(HttpUtil.ip());
            model.setAddress(IpUtil.getRealAddressByIP(HttpUtil.ip()));
            model.setOs(userAgent.getOperatingSystem().getName());
            model.setBrowser(userAgent.getBrowser().getName());
            model.setStatus(StringUtil.isEmpty(error) ? 1 : 0);
            model.setCreateTime(System.currentTimeMillis() / 1000);
            systemLogLoginMapper.insert(model);
        } catch (Exception e) {
            log.error("记录登录日志异常 {}" + e.getMessage());
        }
    }

}
