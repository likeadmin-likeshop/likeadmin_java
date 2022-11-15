package com.mdd.admin.controller;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.IAlbumsService;
import com.mdd.admin.validate.AlbumCateValidate;
import com.mdd.admin.validate.AlbumMoveValidate;
import com.mdd.admin.validate.AlbumRenameValidate;
import com.mdd.admin.validate.AlbumSearchValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.IdsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.album.AlbumVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 相册管理
 */
@RestController
@RequestMapping("api/common/album")
public class AlbumsController {

    @Resource
    IAlbumsService iAlbumsService;

    /**
     * 相册文件列表
     *
     * @author fzr
     * @return AjaxResult<PageResult<AlbumVo>>
     */
    @GetMapping("/albumList")
    public AjaxResult<PageResult<AlbumVo>> albumList(@Validated PageValidate pageValidate,
                                                     @Validated AlbumSearchValidate searchValidate) {
        PageResult<AlbumVo> voPageResult = iAlbumsService.albumList(pageValidate, searchValidate);
        return AjaxResult.success(voPageResult);
    }

    /**
     * 相册文件重命名
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "相册文件重命名")
    @PostMapping("/albumRename")
    public AjaxResult<Object> albumRename(@Validated @RequestBody AlbumRenameValidate renameValidate) {
        iAlbumsService.albumRename(renameValidate.getId(), renameValidate.getName());
        return AjaxResult.success();
    }

    /**
     * 相册文件移动
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "相册文件移动")
    @PostMapping("/albumMove")
    public AjaxResult<Object> albumMove(@Validated @RequestBody AlbumMoveValidate moveValidate) {
        iAlbumsService.albumMove(moveValidate.getIds(), moveValidate.getCid());
        return AjaxResult.success();
    }

    /**
     * 相册文件删除
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "相册文件删除")
    @PostMapping("/albumDel")
    public AjaxResult<Object> albumDel(@Validated @RequestBody IdsValidate idsValidate) {
        iAlbumsService.albumDel(idsValidate.getIds());
        return AjaxResult.success();
    }

    /**
     * 相册分类列表
     *
     * @author fzr
     * @param searchValidate 搜索参数
     * @return AjaxResult<JSONArray>
     */
    @GetMapping("/cateList")
    public AjaxResult<JSONArray> cateList(@Validated AlbumSearchValidate searchValidate) {
        JSONArray jsonArray = iAlbumsService.cateList(searchValidate);
        return AjaxResult.success(jsonArray);
    }

    /**
     * 相册分类新增
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "相册分类新增")
    @PostMapping("/cateAdd")
    public AjaxResult<Object> cateAdd(@Validated @RequestBody AlbumCateValidate cateValidate) {
        iAlbumsService.cateAdd(cateValidate);
        return AjaxResult.success();
    }

    /**
     * 相册分类重命名
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "相册分类重命名")
    @PostMapping("/cateRename")
    public AjaxResult<Object> cateRename(@Validated @RequestBody AlbumRenameValidate renameValidate) {
        iAlbumsService.cateRename(renameValidate.getId(), renameValidate.getName());
        return AjaxResult.success();
    }

    /**
     * 相册分类删除
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "相册分类删除")
    @PostMapping("/cateDel")
    public AjaxResult<Object> cateDel(@Validated @RequestBody IdValidate idValidate) {
        iAlbumsService.cateDel(idValidate.getId());
        return AjaxResult.success();
    }

}
