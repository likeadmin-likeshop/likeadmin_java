package com.hxkj.admin.service.setting;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.setting.DictTypeParam;
import com.hxkj.admin.vo.setting.DictTypeVo;
import com.hxkj.common.core.PageResult;

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
    List<DictTypeVo> all();

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    PageResult<DictTypeVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    DictTypeVo detail(Integer id);

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param dictTypeParam 参数
     */
    void add(DictTypeParam dictTypeParam);

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param dictTypeParam 参数
     */
    void edit(DictTypeParam dictTypeParam);

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
