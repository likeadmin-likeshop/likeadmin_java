package com.mdd.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.ISystemAuthAdminService;
import com.mdd.admin.service.ISystemLoginService;
import com.mdd.admin.validate.system.SystemAdminLoginsValidate;
import com.mdd.admin.vo.system.SystemLoginVo;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.entity.system.SystemLogLogin;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.LoginException;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.system.SystemAuthAdminMapper;
import com.mdd.common.mapper.system.SystemLogLoginMapper;
import com.mdd.common.util.*;
import nl.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统登录服务实现类
 */
@Service
public class SystemLoginServiceImpl implements ISystemLoginService {

    @Resource
    SystemLogLoginMapper systemLogLoginMapper;

    @Resource
    SystemAuthAdminMapper systemAuthAdminMapper;

    @Resource
    ISystemAuthAdminService iSystemAuthAdminService;


    private static final Logger log = LoggerFactory.getLogger(SystemLoginServiceImpl.class);

    /**
     * 登录
     *
     * @author fzr
     * @param loginsValidate 登录参数
     * @return SystemLoginVo
     */
    @Override
    public SystemLoginVo login(SystemAdminLoginsValidate loginsValidate) {
        String username = loginsValidate.getUsername();
        String password = loginsValidate.getPassword();

        SystemAuthAdmin sysAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .eq("username", username)
                .last("limit 1"));

        if (StringUtils.isNull(sysAdmin) || sysAdmin.getIsDelete().equals(1)) {
            this.recordLoginLog(0, loginsValidate.getUsername(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        if (sysAdmin.getIsDisable().equals(1)) {
            this.recordLoginLog(sysAdmin.getId(), loginsValidate.getUsername(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
        }

        String newPWd = password + sysAdmin.getSalt();
        String md5Pwd = ToolsUtils.makeMd5(newPWd);
        if (!md5Pwd.equals(sysAdmin.getPassword())) {
            this.recordLoginLog(sysAdmin.getId(), loginsValidate.getUsername(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        try {
            // 禁止多处登录
            if (sysAdmin.getIsMultipoint().equals(0)) {
                StpUtil.logout(sysAdmin.getId());
            }

            // 实现账号登录
            StpUtil.login(sysAdmin.getId());

            // 更新登录信息
            sysAdmin.setLastLoginIp(IpUtils.getIpAddress());
            sysAdmin.setLastLoginTime(System.currentTimeMillis() / 1000);
            systemAuthAdminMapper.updateById(sysAdmin);

            // 记录登录日志
            this.recordLoginLog(sysAdmin.getId(), loginsValidate.getUsername(), "");
            iSystemAuthAdminService.cacheAdminUserByUid(sysAdmin.getId());

            // 响应登录信息
            SystemLoginVo vo = new SystemLoginVo();
            vo.setId(sysAdmin.getId());
            vo.setToken(StpUtil.getTokenValue());
            return vo;
        } catch (Exception e) {
            Integer adminId = StringUtils.isNotNull(sysAdmin.getId()) ? sysAdmin.getId() : 0;
            String error = StringUtils.isEmpty(e.getMessage()) ? "未知错误" : e.getMessage();
            this.recordLoginLog(adminId, loginsValidate.getUsername(), error);
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
        //RedisUtil.del(AdminConfig.backstageTokenKey + token);
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(Integer adminId, String username, String error) {
        try {
            HttpServletRequest request = Objects.requireNonNull(RequestUtils.handler());
            final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

            SystemLogLogin model = new SystemLogLogin();
            model.setAdminId(adminId);
            model.setUsername(username);
            model.setIp(IpUtils.getIpAddress());
            model.setOs(userAgent.getOperatingSystem().getName());
            model.setBrowser(userAgent.getBrowser().getName());
            model.setStatus(StringUtils.isEmpty(error) ? 1 : 0);
            model.setCreateTime(System.currentTimeMillis() / 1000);
            systemLogLoginMapper.insert(model);
        } catch (Exception e) {
            log.error("记录登录日志异常 {}" + e.getMessage());
        }
    }

}
