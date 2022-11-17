package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.setting.DictTypeCreateValidate;
import com.mdd.admin.validate.setting.DictTypeUpdateValidate;
import com.mdd.admin.vo.setting.SettingDictTypeVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 字典类型服务接口类
 */
public interface ISettingDictTypeService {

    /**
     * 字典类型所有
     *
     * @author fzr
     * @return List<DictTypeVo>
     */
    List<SettingDictTypeVo> all();

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    PageResult<SettingDictTypeVo> list(PageValidate pageValidate, Map<String, String> params);

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    SettingDictTypeVo detail(Integer id);

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(DictTypeCreateValidate createValidate);

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(DictTypeUpdateValidate updateValidate);

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param ids 主键
     */
    void del(List<Integer> ids);

}
