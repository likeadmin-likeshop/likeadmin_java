package com.mdd.admin.service.setting.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.setting.ISettingDictDataService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.setting.DictDataParam;
import com.mdd.admin.vo.setting.DictDataVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.setting.DictData;
import com.mdd.common.entity.setting.DictType;
import com.mdd.common.mapper.setting.DictDataMapper;
import com.mdd.common.mapper.setting.DictTypeMapper;
import com.mdd.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字典数据服务实现类
 */
@Service
public class SettingDictDataServiceImpl implements ISettingDictDataService {

    @Resource
    DictDataMapper dictDataMapper;

    @Resource
    DictTypeMapper dictTypeMapper;

    /**
     * 字典数据所有
     *
     * @author fzr
     * @return List<DictDataVo>
     */
    @Override
    public List<DictDataVo> all(Map<String, String> params) {
        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .eq("dict_type", params.get("dictType"))
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(dictType, "该字典类型不存在!");

        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,type_id,name,value,remark,sort,status,create_time,update_time");
        queryWrapper.eq("is_delete", 0);
        queryWrapper.eq("type_id", dictType.getId());
        queryWrapper.orderByDesc("id");

        dictDataMapper.setSearch(queryWrapper, params, new String[]{
                "like:name:str",
                "like:value:str",
                "=:status:int",
        });

        List<DictData> dictDataList = dictDataMapper.selectList(queryWrapper);

        List<DictDataVo> list = new LinkedList<>();
        for (DictData dictData : dictDataList) {
            DictDataVo vo = new DictDataVo();
            BeanUtils.copyProperties(dictData, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(dictData.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(dictData.getUpdateTime()));
            list.add(vo);
        }

        return list;
    }

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    @Override
    public PageResult<DictDataVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        DictType dictType = dictTypeMapper.selectOne(new QueryWrapper<DictType>()
                .eq("is_delete", 0)
                .eq("dict_type", params.get("dictType"))
                .last("limit 1"));

        Assert.notNull(dictType, "该字典类型不存在!");

        QueryWrapper<DictData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,type_id,name,value,remark,sort,status,create_time,update_time");
        queryWrapper.eq("type_id", dictType.getId());
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByDesc("id");

        dictDataMapper.setSearch(queryWrapper, params, new String[]{
                "like:name:str",
                "like:value:str",
                "=:status:int",
        });

        IPage<DictData> iPage = dictDataMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<DictDataVo> list = new LinkedList<>();
        for (DictData dictData : iPage.getRecords()) {
            DictDataVo vo = new DictDataVo();
            BeanUtils.copyProperties(dictData, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(dictData.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(dictData.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 字典数据详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    @Override
    public DictDataVo detail(Integer id) {
        DictData dictData = dictDataMapper.selectOne(new QueryWrapper<DictData>()
                .select("id,type_id,name,value,remark,sort,status,create_time,update_time")
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(dictData, "字典数据不存在！");

        DictDataVo vo = new DictDataVo();
        BeanUtils.copyProperties(dictData, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(dictData.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(dictData.getUpdateTime()));
        return vo;
    }

    /**
     * 字典数据新增
     *
     * @author fzr
     * @param dictDataParam 参数
     */
    @Override
    public void add(DictDataParam dictDataParam) {
        Assert.isNull(dictDataMapper.selectOne(new QueryWrapper<DictData>()
                .select("id")
                .eq("name", dictDataParam.getName())
                .eq("is_delete", 0)
                .last("limit 1")), "字典数据已存在！");

        DictData model = new DictData();
        model.setTypeId(dictDataParam.getTypeId());
        model.setName(dictDataParam.getName());
        model.setValue(dictDataParam.getValue());
        model.setRemark(dictDataParam.getRemark());
        model.setSort(dictDataParam.getSort());
        model.setStatus(dictDataParam.getStatus());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictDataMapper.insert(model);
    }

    /**
     * 字典数据编辑
     *
     * @author fzr
     * @param dictDataParam 参数
     */
    @Override
    public void edit(DictDataParam dictDataParam) {
        DictData model = dictDataMapper.selectOne(new QueryWrapper<DictData>()
                .eq("id", dictDataParam.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "字典数据不存在！");

        Assert.isNull(dictDataMapper.selectOne(new QueryWrapper<DictData>()
                .select("id")
                .ne("id", dictDataParam.getId())
                .eq("name", dictDataParam.getName())
                .eq("is_delete", 0)
                .last("limit 1")), "字典数据已存在！");

        model.setName(dictDataParam.getName());
        model.setValue(dictDataParam.getValue());
        model.setRemark(dictDataParam.getRemark());
        model.setSort(dictDataParam.getSort());
        model.setStatus(dictDataParam.getStatus());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        dictDataMapper.updateById(model);
    }

    /**
     * 字典数据删除
     *
     * @author fzr
     * @param ids 主键
     */
    @Override
    public void del(List<Integer> ids) {
        for (Integer id : ids) {
            DictData model = new DictData();
            model.setId(id);
            model.setIsDelete(1);
            model.setDeleteTime(System.currentTimeMillis() / 1000);
            dictDataMapper.updateById(model);
        }
    }

}
