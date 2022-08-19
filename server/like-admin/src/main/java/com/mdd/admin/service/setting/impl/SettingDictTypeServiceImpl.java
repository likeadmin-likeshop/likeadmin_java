package com.mdd.admin.service.setting.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.setting.ISettingDictTypeService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.setting.DictTypeParam;
import com.mdd.admin.vo.setting.DictTypeVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.setting.DictType;
import com.mdd.common.mapper.setting.DictTypeMapper;
import com.mdd.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字典类型服务实现类
 */
@Service
public class SettingDictTypeServiceImpl implements ISettingDictTypeService {

    @Resource
    DictTypeMapper dictTypeMapper;

    /**
     * 字典类型所有
     *
     * @author fzr
     * @return List<DictTypeVo>
     */
    @Override
    public List<DictTypeVo> all() {
        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,dict_name,dict_type,dict_remark,dict_status,create_time,update_time");
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("id");

        List<DictType> dictTypeList = dictTypeMapper.selectList(queryWrapper);

        List<DictTypeVo> list = new LinkedList<>();
        for (DictType dictType : dictTypeList) {
            DictTypeVo vo = new DictTypeVo();
            BeanUtils.copyProperties(dictType, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(dictType.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(dictType.getUpdateTime()));
            list.add(vo);
        }

        return list;
    }

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    @Override
    public PageResult<DictTypeVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,dict_name,dict_type,dict_remark,dict_status,create_time,update_time");
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("id");

        dictTypeMapper.setSearch(queryWrapper, params, new String[]{
                "like:dictName@dict_name:str",
                "like:dictType@dict_type:str",
                "=:dictStatus@dict_status:int",
        });

        IPage<DictType> iPage = dictTypeMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<DictTypeVo> list = new LinkedList<>();
        for (DictType dictType : iPage.getRecords()) {
            DictTypeVo vo = new DictTypeVo();
            BeanUtils.copyProperties(dictType, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(dictType.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(dictType.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    @Override
    public DictTypeVo detail(Integer id) {
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id,dict_name,dict_type,dict_remark,dict_status,create_time,update_time")
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(dictType, "字典类型不存在！");

        DictTypeVo vo = new DictTypeVo();
        BeanUtils.copyProperties(dictType, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(dictType.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(dictType.getUpdateTime()));
        return vo;
    }

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param dictTypeParam 参数
     */
    @Override
    public void add(DictTypeParam dictTypeParam) {
        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id")
                .eq("dict_name", dictTypeParam.getDictName())
                .eq("is_delete", 0)
                .last("limit 1")), "字典名称已存在！");

       Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id")
                .eq("dict_type", dictTypeParam.getDictType())
                .eq("is_delete", 0)
                .last("limit 1")), "字典类型已存在！");

        DictType model = new DictType();
        model.setDictName(dictTypeParam.getDictName());
        model.setDictType(dictTypeParam.getDictType());
        model.setDictRemark(dictTypeParam.getDictRemark());
        model.setDictStatus(dictTypeParam.getDictStatus());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictTypeMapper.insert(model);
    }

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param dictTypeParam 参数
     */
    @Override
    public void edit(DictTypeParam dictTypeParam) {
        DictType model = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                        .eq("id", dictTypeParam.getId())
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(model, "字典类型不存在！");

        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .ne("id", dictTypeParam.getId())
                .eq("dict_name", dictTypeParam.getDictName())
                .eq("is_delete", 0)
                .last("limit 1")), "字典类型已存在！");

        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .ne("id", dictTypeParam.getId())
                .eq("dict_type", dictTypeParam.getDictType())
                .eq("is_delete", 0)
                .last("limit 1")), "字典类型已存在！");

        model.setDictName(dictTypeParam.getDictName());
        model.setDictType(dictTypeParam.getDictType());
        model.setDictRemark(dictTypeParam.getDictRemark());
        model.setDictStatus(dictTypeParam.getDictStatus());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictTypeMapper.updateById(model);
    }

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param ids 主键
     */
    @Override
    public void del(List<Integer> ids) {
        for(Integer id : ids) {
            DictType model = new DictType();
            model.setId(id);
            model.setIsDelete(1);
            model.setDeleteTime(System.currentTimeMillis() / 1000);
            dictTypeMapper.updateById(model);
        }
    }

}
