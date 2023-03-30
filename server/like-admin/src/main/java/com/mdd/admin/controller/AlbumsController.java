package com.mdd.admin.controller;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.aop.Log;
import com.mdd.admin.service.IAlbumsService;
import com.mdd.admin.validate.album.AlbumCateValidate;
import com.mdd.admin.validate.album.AlbumMoveValidate;
import com.mdd.admin.validate.album.AlbumRenameValidate;
import com.mdd.admin.validate.album.AlbumSearchValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.IdsValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.album.AlbumVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/albums")
@Api(tags = "相册数据管理")
public class AlbumsController {

    @Resource
    IAlbumsService iAlbumsService;

    @GetMapping("/albumList")
    @ApiOperation(value="相册文件列表")
    public AjaxResult<PageResult<AlbumVo>> albumList(@Validated PageValidate pageValidate,
                                                     @Validated AlbumSearchValidate searchValidate) {
        PageResult<AlbumVo> voPageResult = iAlbumsService.albumList(pageValidate, searchValidate);
        return AjaxResult.success(voPageResult);
    }

    @Log(title = "相册文件重命名")
    @PostMapping("/albumRename")
    @ApiOperation(value="相册文件重命名")
    public AjaxResult<Object> albumRename(@Validated @RequestBody AlbumRenameValidate renameValidate) {
        iAlbumsService.albumRename(renameValidate.getId(), renameValidate.getName());
        return AjaxResult.success();
    }

    @Log(title = "相册文件移动")
    @PostMapping("/albumMove")
    @ApiOperation(value="相册文件移动")
    public AjaxResult<Object> albumMove(@Validated @RequestBody AlbumMoveValidate moveValidate) {
        iAlbumsService.albumMove(moveValidate.getIds(), moveValidate.getCid());
        return AjaxResult.success();
    }

    @Log(title = "相册文件删除")
    @PostMapping("/albumDel")
    @ApiOperation(value="相册文件删除")
    public AjaxResult<Object> albumDel(@Validated @RequestBody IdsValidate idsValidate) {
        iAlbumsService.albumDel(idsValidate.getIds());
        return AjaxResult.success();
    }

    @GetMapping("/cateList")
    @ApiOperation(value="相册分类列表")
    public AjaxResult<JSONArray> cateList(@Validated AlbumSearchValidate searchValidate) {
        JSONArray jsonArray = iAlbumsService.cateList(searchValidate);
        return AjaxResult.success(jsonArray);
    }

    @Log(title = "相册分类新增")
    @PostMapping("/cateAdd")
    @ApiOperation(value="相册分类新增")
    public AjaxResult<Object> cateAdd(@Validated @RequestBody AlbumCateValidate cateValidate) {
        iAlbumsService.cateAdd(cateValidate);
        return AjaxResult.success();
    }

    @Log(title = "相册分类重命名")
    @PostMapping("/cateRename")
    @ApiOperation(value="相册分类重命名")
    public AjaxResult<Object> cateRename(@Validated @RequestBody AlbumRenameValidate renameValidate) {
        iAlbumsService.cateRename(renameValidate.getId(), renameValidate.getName());
        return AjaxResult.success();
    }

    @Log(title = "相册分类删除")
    @PostMapping("/cateDel")
    @ApiOperation(value="相册分类删除")
    public AjaxResult<Object> cateDel(@Validated @RequestBody IdValidate idValidate) {
        iAlbumsService.cateDel(idValidate.getId());
        return AjaxResult.success();
    }

}
