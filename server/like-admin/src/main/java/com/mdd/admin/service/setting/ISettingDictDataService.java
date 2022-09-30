package com.mdd.admin.service.setting;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.setting.DictDataParam;
import com.mdd.admin.vo.setting.SettingDictDataVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 字典数据服务接口类
 */
public interface ISettingDictDataService {

    /**
     * 字典数据所有
     *
     * @author fzr
     * @return List<DictDataVo>
     */
    List<SettingDictDataVo> all(Map<String, String> params);

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    PageResult<SettingDictDataVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 字典数据详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    SettingDictDataVo detail(Integer id);

    /**
     * 字典数据新增
     *
     * @author fzr
     * @param dictDataParam 参数
     */
    void add(DictDataParam dictDataParam);

    /**
     * 字典数据编辑
     *
     * @author fzr
     * @param dictDataParam 参数
     */
    void edit(DictDataParam dictDataParam);

    /**
     * 字典数据删除
     *
     * @author fzr
     * @param ids 主键
     */
    void del(List<Integer> ids);

}
