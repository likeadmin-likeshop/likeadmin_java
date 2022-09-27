package com.mdd.admin.controller.common;

import com.alibaba.fastjson.JSONArray;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.common.IAlbumService;
import com.mdd.admin.validate.common.AlbumParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.album.AlbumVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.utils.ArrayUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 相册管理
 */
@RestController
@RequestMapping("api/common/album")
public class AlbumController {

    @Resource
    IAlbumService iAlbumService;

    /**
     * 相册文件列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/albumList")
    public AjaxResult albumList(@Validated PageParam pageParam,
                            @RequestParam Map<String, String> params) {
        PageResult<AlbumVo> voPageResult = iAlbumService.albumList(pageParam, params);
        return AjaxResult.success(voPageResult);
    }

    /**
     * 相册文件重命名
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "相册文件重命名")
    @PostMapping("/albumRename")
    public AjaxResult albumRename(@Validated(value = AlbumParam.rename.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.albumRename(albumParam.getId(), albumParam.getName());
        return AjaxResult.success();
    }

    /**
     * 相册文件移动
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册文件移动")
    @PostMapping("/albumMove")
    public AjaxResult albumMove(@RequestBody Map<String, Object> params) {
        if (params.get("ids") == null) {
            return AjaxResult.failed("缺少ids参数");
        }

        if (params.get("cid") == null) {
            return AjaxResult.failed("缺少cid参数");
        }

        List<Integer> ids = ArrayUtil.objectToListAsInt(params.get("ids"));
        if (ids.size() <= 0) {
            return AjaxResult.failed("请至少选择一个文件");
        }

        iAlbumService.albumMove(ids, Integer.parseInt(params.get("cid").toString()));
        return AjaxResult.success();
    }

    /**
     * 相册文件删除
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "相册文件删除")
    @PostMapping("/albumDel")
    public AjaxResult albumDel(@RequestBody Map<String, List<Integer>> params) {
        if (params.get("ids") == null) {
            return AjaxResult.failed("缺少ids参数");
        }

        iAlbumService.albumDel(params.get("ids"));
        return AjaxResult.success();
    }

    /**
     * 相册分类列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/cateList")
    public AjaxResult cateList(@RequestParam Map<String, String> params) {
        JSONArray jsonArray = iAlbumService.cateList(params);
        return AjaxResult.success(jsonArray);
    }

    /**
     * 相册分类新增
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册分类新增")
    @PostMapping("/cateAdd")
    public AjaxResult cateAdd(@Validated(value = AlbumParam.cateAdd.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.cateAdd(albumParam);
        return AjaxResult.success();
    }

    /**
     * 相册分类重命名
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "相册分类重命名")
    @PostMapping("/cateRename")
    public AjaxResult cateRename(@Validated(value = AlbumParam.rename.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.cateRename(albumParam.getId(), albumParam.getName());
        return AjaxResult.success();
    }

    /**
     * 相册分类删除
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "相册分类删除")
    @PostMapping("/cateDel")
    public AjaxResult cateDel(@Validated(value = AlbumParam.delete.class) @RequestBody AlbumParam albumParam) {
        iAlbumService.cateDel(albumParam.getId());
        return AjaxResult.success();
    }

}
