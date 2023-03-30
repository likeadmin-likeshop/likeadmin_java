package com.mdd.admin.service;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.validate.album.AlbumCateValidate;
import com.mdd.admin.validate.album.AlbumSearchValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.album.AlbumVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 相册服务接口类
 */
public interface IAlbumsService {

    /**
     * 文件列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 其他搜索参数
     * @return PageResult<AlbumVo>
     */
    PageResult<AlbumVo> albumList(PageValidate pageValidate, AlbumSearchValidate searchValidate);

    /**
     * 文件重命名
     *
     * @param id 文件ID
     * @param name 文件名称
     */
    void albumRename(Integer id, String name);

    /**
     * 文件移动
     *
     * @author fzr
     * @param ids 文件ID
     * @param cid 类目ID
     */
    void albumMove(List<Integer> ids, Integer cid);

    /**
     * 文件新增
     *
     * @author fzr
     * @param params 文件信息参数
     */
    Integer albumAdd(Map<String, String> params);

    /**
     * 文件删除
     *
     * @author fzr
     * @param ids 文件ID
     */
    void albumDel(List<Integer> ids);

    /**
     * 分类列表
     *
     * @author fzr
     * @param searchValidate 搜索参数
     * @return JSONArray
     */
    JSONArray cateList(AlbumSearchValidate searchValidate);

    /**
     * 分类新增
     *
     * @author fzr
     * @param cateValidate 分类参数
     */
    void cateAdd(AlbumCateValidate cateValidate);

    /**
     * 分类编辑
     *
     * @author fzr
     * @param id 分类ID
     * @param name 分类名称
     */
    void cateRename(Integer id, String name);

    /**
     * 分类删除
     *
     * @author fzr
     * @param id 分类ID
     */
    void cateDel(Integer id);

}
