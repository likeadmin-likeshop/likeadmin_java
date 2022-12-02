package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.ISettingDictTypeService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.setting.DictTypeCreateValidate;
import com.mdd.admin.validate.setting.DictTypeUpdateValidate;
import com.mdd.admin.vo.setting.SettingDictTypeVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.setting.DictType;
import com.mdd.common.mapper.setting.DictTypeMapper;
import com.mdd.common.util.TimeUtils;
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
    public List<SettingDictTypeVo> all() {
        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,dict_name,dict_type,dict_remark,dict_status,create_time,update_time");
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("id");

        List<DictType> dictTypeList = dictTypeMapper.selectList(queryWrapper);

        List<SettingDictTypeVo> list = new LinkedList<>();
        for (DictType dictType : dictTypeList) {
            SettingDictTypeVo vo = new SettingDictTypeVo();
            BeanUtils.copyProperties(dictType, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(dictType.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(dictType.getUpdateTime()));
            list.add(vo);
        }

        return list;
    }

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    @Override
    public PageResult<SettingDictTypeVo> list(PageValidate pageValidate, Map<String, String> params) {
        Integer page  = pageValidate.getPageNo();
        Integer limit = pageValidate.getPageSize();

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

        List<SettingDictTypeVo> list = new LinkedList<>();
        for (DictType dictType : iPage.getRecords()) {
            SettingDictTypeVo vo = new SettingDictTypeVo();
            BeanUtils.copyProperties(dictType, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(dictType.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(dictType.getUpdateTime()));
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
    public SettingDictTypeVo detail(Integer id) {
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id,dict_name,dict_type,dict_remark,dict_status,create_time,update_time")
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(dictType, "字典类型不存在！");

        SettingDictTypeVo vo = new SettingDictTypeVo();
        BeanUtils.copyProperties(dictType, vo);
        vo.setCreateTime(TimeUtils.timestampToDate(dictType.getCreateTime()));
        vo.setUpdateTime(TimeUtils.timestampToDate(dictType.getUpdateTime()));
        return vo;
    }

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    @Override
    public void add(DictTypeCreateValidate createValidate) {
        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id")
                .eq("dict_name", createValidate.getDictName())
                .eq("is_delete", 0)
                .last("limit 1")), "字典名称已存在！");

       Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .select("id")
                .eq("dict_type", createValidate.getDictType())
                .eq("is_delete", 0)
                .last("limit 1")), "字典类型已存在！");

        DictType model = new DictType();
        model.setDictName(createValidate.getDictName());
        model.setDictType(createValidate.getDictType());
        model.setDictRemark(createValidate.getDictRemark());
        model.setDictStatus(createValidate.getDictStatus());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictTypeMapper.insert(model);
    }

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    public void edit(DictTypeUpdateValidate updateValidate) {
        DictType model = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                        .eq("id", updateValidate.getId())
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(model, "字典类型不存在！");

        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .ne("id", updateValidate.getId())
                .eq("dict_name", updateValidate.getDictName())
                .eq("is_delete", 0)
                .last("limit 1")), "字典类型已存在！");

        Assert.isNull(dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .ne("id", updateValidate.getId())
                .eq("dict_type", updateValidate.getDictType())
                .eq("is_delete", 0)
                .last("limit 1")), "字典类型已存在！");

        model.setDictName(updateValidate.getDictName());
        model.setDictType(updateValidate.getDictType());
        model.setDictRemark(updateValidate.getDictRemark());
        model.setDictStatus(updateValidate.getDictStatus());
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
