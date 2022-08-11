package com.hxkj.admin.service.setting;

import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.setting.DictDataParam;
import com.hxkj.admin.vo.setting.DictDataVo;
import com.hxkj.common.core.PageResult;

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
    List<DictDataVo> all();

    /**
     * 字典数据列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<DictDataVo>
     */
    PageResult<DictDataVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 字典数据详情
     *
     * @author fzr
     * @param id 主键
     * @return DictDataVo
     */
    DictDataVo detail(Integer id);

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
     * @param id 主键
     */
    void del(Integer id);

}
