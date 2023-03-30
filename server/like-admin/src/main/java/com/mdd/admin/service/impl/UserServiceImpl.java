package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.IUserService;
import com.mdd.admin.validate.user.UserSearchValidate;
import com.mdd.admin.validate.user.UserUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.user.UserWalletValidate;
import com.mdd.admin.vo.user.UserVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.user.User;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.LogMoneyEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.log.LogMoneyMapper;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.UrlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @Resource
    LogMoneyMapper logMoneyMapper;

    /**
     * 用户列表
     *
     * @author fzr
     * @param pageValidate (分页参数)
     * @param searchValidate (搜索参数)
     * @return PageResult<UserVo>
     */
    @Override
    public PageResult<UserVo> list(PageValidate pageValidate, UserSearchValidate searchValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("id");
        queryWrapper.select(User.class, info->
                !info.getColumn().equals("is_delete") &&
                !info.getColumn().equals("delete_time") &&
                !info.getColumn().equals("update_time") &&
                !info.getColumn().equals("password") &&
                !info.getColumn().equals("salt")
        );

        if (StringUtils.isNotNull(searchValidate.getKeyword()) && StringUtils.isNotEmpty(searchValidate.getKeyword())) {
            String keyword = searchValidate.getKeyword();
            queryWrapper.nested(wq->wq
                    .like("sn", keyword).or()
                    .like("nickname", keyword).or()
                    .like("mobile", keyword));
        }

        userMapper.setSearch(queryWrapper, searchValidate, new String[]{
                "=:channel:int",
                "datetime:startTime-endTime@create_time:str"
        });

        IPage<User> iPage = userMapper.selectPage( new Page<>(pageNo, pageSize), queryWrapper);

        List<UserVo> list = new LinkedList<>();
        for (User user : iPage.getRecords()) {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(user, vo);

            vo.setSex(user.getSex());
            vo.setChannel(ClientEnum.getMsgByCode(user.getChannel()));
            vo.setAvatar(UrlUtils.toAbsoluteUrl(user.getAvatar()));
            vo.setLastLoginTime(TimeUtils.timestampToDate(user.getLastLoginTime()));
            vo.setCreateTime(TimeUtils.timestampToDate(user.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 用户详情
     *
     * @author fzr
     * @param id 主键
     * @return UserVo
     */
    @Override
    public UserVo detail(Integer id) {
        Assert.notNull(
                userMapper.selectOne(new QueryWrapper<User>()
                        .select("id")
                        .eq("id", id)
                        .eq("is_delete", 0)
                        .last("limit 1")
                ), "数据不存在!");


        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select(User.class, info->
                    !info.getColumn().equals("is_delete") &&
                    !info.getColumn().equals("delete_time") &&
                    !info.getColumn().equals("update_time") &&
                    !info.getColumn().equals("password") &&
                    !info.getColumn().equals("salt")
                )
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);

        vo.setSex(user.getSex());
        vo.setAvatar(UrlUtils.toAbsoluteUrl(user.getAvatar()));
        vo.setChannel(ClientEnum.getMsgByCode(user.getChannel()));
        vo.setCreateTime(TimeUtils.timestampToDate(user.getCreateTime()));
        if (user.getLastLoginTime() <= 0) {
            vo.setLastLoginTime("无");
        } else {
            vo.setLastLoginTime(TimeUtils.timestampToDate(user.getLastLoginTime()));
        }
        return vo;
    }

    /**
     * 用户编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    public void edit(UserUpdateValidate updateValidate) {
        Integer id = updateValidate.getId();
        String field = updateValidate.getField();
        String value = updateValidate.getValue();

        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user, "用户不存在!");

        switch (field) {
            case "username":
                if (!user.getUsername().equals(value)) {
                    User u = userMapper.selectOne(new QueryWrapper<User>()
                            .eq("username", value)
                            .eq("is_delete", 0)
                            .last("limit 1"));

                    if (StringUtils.isNotNull(u) && !u.getId().equals(id)) {
                        throw new OperateException("当前账号已存在!");
                    }
                }
                Assert.isTrue(value.length() <= 32,"账号不能超过32个字符");
                user.setUsername(value);
                break;
            case "realName":
                Assert.isTrue(value.length() <= 32,"真实姓名不能超过32个字符");
                user.setRealName(value);
                break;
            case "sex":
                user.setSex(Integer.parseInt(value));
                break;
            case "mobile":
                if (!Pattern.matches("^[1][3-9][0-9]{9}$", value)) {
                    throw new OperateException("手机号格式不正确!");
                }
                user.setMobile(value);
                break;
            default:
                throw new OperateException("不被支持的字段类型!");
        }

        user.setUpdateTime(System.currentTimeMillis() / 1000);
        userMapper.updateById(user);
    }

    /**
     * 余额调整
     *
     * @author cjh
     * @param userWalletValidate 余额
     */
    @Override
    @Transactional
    public void adjustWallet(UserWalletValidate userWalletValidate) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("id", userWalletValidate.getUserId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(user,"用户不存在!");

        BigDecimal userMoney = user.getMoney();
        BigDecimal amount = userWalletValidate.getAmount();
        BigDecimal surplusAmount;
        int changeType;

        if(userWalletValidate.getAction().equals(0) ){
            surplusAmount = userMoney.subtract(amount);
            if(surplusAmount.compareTo(BigDecimal.ZERO) < 0){
                throw new OperateException("用户余额仅剩："+ userMoney);
            }
            changeType = LogMoneyEnum.UM_DEC_ADMIN.getCode();
            logMoneyMapper.dec(user.getId(), changeType, amount, 0, "", userWalletValidate.getRemark(), null);
        }else{
            surplusAmount = userMoney.add(amount);
            changeType = LogMoneyEnum.UM_INC_ADMIN.getCode();
            logMoneyMapper.add(user.getId(), changeType, amount, 0, "", userWalletValidate.getRemark(), null);
        }

        user.setMoney(surplusAmount);
        userMapper.updateById(user);
    }

}
