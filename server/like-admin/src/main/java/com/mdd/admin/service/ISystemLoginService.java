package com.mdd.admin.service;

import com.mdd.admin.validate.system.SystemAdminLoginsValidate;
import com.mdd.admin.vo.system.SystemLoginVo;

import java.util.Map;

/**
 * 系统登录服务接口类
 */
public interface ISystemLoginService {

    /**
     * 登录
     *
     * @author fzr
     * @param loginsValidate 登录参数
     * @return SystemLoginVo
     */
    SystemLoginVo login(SystemAdminLoginsValidate loginsValidate);

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    void logout(String token);

}
