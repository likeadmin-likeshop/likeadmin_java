package com.mdd.admin.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.user.IUserService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.user.UserInfoParam;
import com.mdd.admin.vo.article.ArticleListVo;
import com.mdd.admin.vo.user.UserVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.user.User;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.user.UserMapper;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    /**
     * 用户列表
     *
     * @author fzr
     * @param pageParam (分页参数)
     * @param params (搜索参数)
     * @return PageResult<UserVo>
     */
    @Override
    public PageResult<UserVo> list(PageParam pageParam, Map<String, String> params) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

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

        if (StringUtil.isNotEmpty(params.get("keyword"))) {
            String keyword = params.get("keyword");
            queryWrapper.nested(wq->wq
                    .like("sn", keyword).or()
                    .like("nickname", keyword).or()
                    .like("mobile", keyword));
        }

        userMapper.setSearch(queryWrapper, params, new String[]{
                "=:channel:int",
                "datetime:startTime-endTime@t.create_time:str"
        });

        IPage<User> iPage = userMapper.selectPage( new Page<>(pageNo, pageSize), queryWrapper);

        List<UserVo> list = new LinkedList<>();
        for (User user : iPage.getRecords()) {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(user, vo);

            vo.setSex(user.getSex());
            vo.setChannel(ClientEnum.getMsgByCode(user.getChannel()));
            vo.setAvatar(UrlUtil.toAbsoluteUrl(user.getAvatar()));
            vo.setLastLoginTime(TimeUtil.timestampToDate(user.getLastLoginTime()));
            vo.setCreateTime(TimeUtil.timestampToDate(user.getCreateTime()));
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
        vo.setAvatar(UrlUtil.toAbsoluteUrl(user.getAvatar()));
        vo.setChannel(ClientEnum.getMsgByCode(user.getChannel()));
        vo.setLastLoginTime(TimeUtil.timestampToDate(user.getLastLoginTime()));
        vo.setCreateTime(TimeUtil.timestampToDate(user.getCreateTime()));
        return vo;
    }

    /**
     * 用户编辑
     *
     * @author fzr
     * @param userInfoParam 参数
     */
    @Override
    public void edit(UserInfoParam userInfoParam) {
            User user = userMapper.selectOne(new QueryWrapper<User>()
                    .eq("id", userInfoParam.getId())
                    .eq("is_delete", 0)
                    .last("limit 1"));

            Assert.notNull(user, "用户不存在!");

            if (!user.getUsername().equals(userInfoParam.getUsername())) {
                User u = userMapper.selectOne(new QueryWrapper<User>()
                        .eq("username", userInfoParam.getUsername())
                        .eq("is_delete", 0)
                        .last("limit 1"));
                System.out.println(u);
                if (StringUtil.isNotNull(u) && !u.getId().equals(userInfoParam.getId())) {
                    throw new OperateException("当前账号已存在!");
                }
            }

            if (!userInfoParam.getMobile().equals("")) {
                if(!Pattern.matches("^[1][3,4,5,6,7,8,9][0-9]{9}$", userInfoParam.getMobile())){
                    throw new OperateException("手机号格式不正确!");
                }
            }

            user.setUsername(userInfoParam.getUsername());
            user.setRealName(userInfoParam.getRealName());
            user.setSex(userInfoParam.getSex());
            user.setMobile(userInfoParam.getMobile());
            userMapper.updateById(user);
    }

}
