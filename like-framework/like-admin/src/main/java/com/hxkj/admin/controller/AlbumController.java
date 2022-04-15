package com.hxkj.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.IAlbumService;
import com.hxkj.admin.validate.AlbumParam;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.vo.album.AlbumVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 相册管理
 */
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Resource
    IAlbumService iAlbumService;

    /**
     * 相册列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/albumList")
    public Object albumList(@Validated PageParam pageParam,
                            @RequestParam Map<String, String> params) {
        PageResult<AlbumVo> voPageResult = iAlbumService.albumList(pageParam, params);
        return AjaxResult.success(voPageResult);
    }

    /**
     * 相册重命名
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册重命名")
    @PostMapping("/albumRename")
    public Object albumRename(@Validated(value = AlbumParam.rename.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.albumRename(albumParam.getId(), albumParam.getName());
        return AjaxResult.success();
    }

    /**
     * 相册移动
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册移动")
    @PostMapping("/albumMove")
    public Object albumMove(@Validated(value = AlbumParam.albumMove.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.albumMove(albumParam.getId(), albumParam.getCid());
        return AjaxResult.success();
    }

    /**
     * 相册删除
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册删除")
    @PostMapping("/albumDel")
    public Object albumDel(@Validated(value = AlbumParam.delete.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.albumDel(albumParam.getId());
        return AjaxResult.success();
    }

    /**
     * 分类列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/cateList")
    public Object cateList(@RequestParam Map<String, String> params) {
        JSONArray jsonArray = iAlbumService.cateList(params);
        return AjaxResult.success(jsonArray);
    }

    /**
     * 分类新增
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册分类新增")
    @PostMapping("/cateAdd")
    public Object cateAdd(@Validated(value = AlbumParam.cateAdd.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.cateAdd(albumParam);
        return AjaxResult.success();
    }

    /**
     * 分类重命名
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册分类重命名")
    @PostMapping("/cateRename")
    public Object cateRename(@Validated(value = AlbumParam.rename.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.cateRename(albumParam.getId(), albumParam.getName());
        return AjaxResult.success();
    }

    /**
     * 分类删除
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册分类删除")
    @PostMapping("/cateDel")
    public Object cateDel(@Validated(value = AlbumParam.delete.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.cateDel(albumParam.getId());
        return AjaxResult.success();
    }

}
