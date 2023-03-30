package com.mdd.admin.service;

import com.mdd.admin.validate.user.UserSearchValidate;
import com.mdd.admin.validate.user.UserUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.user.UserWalletValidate;
import com.mdd.admin.vo.user.UserVo;
import com.mdd.common.core.PageResult;

/**
 * 用户服务接口类
 */
public interface IUserService {

    /**
     * 用户列表
     *
     * @author fzr
     * @param pageValidate (分页参数)
     * @param searchValidate (搜索参数)
     * @return PageResult<UserVo>
     */
    PageResult<UserVo> list(PageValidate pageValidate, UserSearchValidate searchValidate);

    /**
     * 用户详情
     *
     * @author fzr
     * @param id 主键
     * @return UserVo
     */
    UserVo detail(Integer id);

    /**
     * 用户编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(UserUpdateValidate updateValidate);

    /**
     * 余额调整
     *
     * @author fzr
     * @param userWalletValidate 余额
     */
    void adjustWallet(UserWalletValidate userWalletValidate);

}
