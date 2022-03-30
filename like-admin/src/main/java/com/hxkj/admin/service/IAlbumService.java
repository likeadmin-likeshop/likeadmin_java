package com.hxkj.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.validate.AlbumParam;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.vo.album.AlbumVo;
import com.hxkj.common.basics.BaseService;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.Album;

import java.util.Map;

/**
 * 相册服务类
 */
public interface IAlbumService extends BaseService<Album> {

    /**
     * 文件列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 其他搜索参数
     * @return PageResult<AlbumVo>
     */
    PageResult<AlbumVo> albumList(PageParam pageParam, Map<String, String> params);

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
     * @param id 文件ID
     * @param cid 类目ID
     */
    void albumMove(Integer id, Integer cid);

    /**
     * 文件新增
     *
     * @author fzr
     * @param params 文件信息参数
     */
    void albumAdd(Map<String, String> params);

    /**
     * 文件删除
     *
     * @author fzr
     * @param id 文件ID
     */
    void albumDel(Integer id);

    /**
     * 分类列表
     *
     * @author fzr
     * @param params 搜索参数
     * @return JSONArray
     */
    JSONArray cateList(Map<String, String> params);

    /**
     * 分类新增
     */
    void cateAdd(AlbumParam albumParam);

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
