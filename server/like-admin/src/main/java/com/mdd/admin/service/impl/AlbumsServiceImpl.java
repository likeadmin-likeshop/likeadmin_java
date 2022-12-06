package com.mdd.admin.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.IAlbumsService;
import com.mdd.admin.validate.AlbumCateValidate;
import com.mdd.admin.validate.AlbumSearchValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.album.AlbumCateVo;
import com.mdd.admin.vo.album.AlbumVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.album.Album;
import com.mdd.common.entity.album.AlbumCate;
import com.mdd.common.mapper.album.AlbumCateMapper;
import com.mdd.common.mapper.album.AlbumMapper;
import com.mdd.common.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 相册服务实现类
 */
@Service
public class AlbumsServiceImpl implements IAlbumsService {

    @Resource
    AlbumMapper albumMapper;

    @Resource
    AlbumCateMapper albumCateMapper;

    /**
     * 相册文件列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<AlbumVo>
     */
    @Override
    public PageResult<AlbumVo> albumList(PageValidate pageValidate, AlbumSearchValidate searchValidate) {
        Integer page  = pageValidate.getPageNo();
        Integer limit = pageValidate.getPageSize();

        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(Album.class, info->
                    !info.getColumn().equals("type") &&
                    !info.getColumn().equals("aid") &&
                    !info.getColumn().equals("uid") &&
                    !info.getColumn().equals("is_delete") &&
                    !info.getColumn().equals("delete_time"))
                .eq("is_delete", 0)
                .orderByDesc("id");

        if (StringUtils.isNotNull(searchValidate.getCid())) {
            queryWrapper.eq("cid", searchValidate.getCid());
        }

        albumMapper.setSearch(queryWrapper, searchValidate, new String[]{
                "=:type:int",
                "like:keyword@name:str"
        });

        IPage<Album> iPage = albumMapper.selectPage(new Page<>(page, limit), queryWrapper);

        String engine = ConfigUtils.get("storage", "default", "local");
        engine = engine.equals("") ? "local" : engine;

        List<AlbumVo> list = new ArrayList<>();
        for (Album album : iPage.getRecords()) {
            AlbumVo vo = new AlbumVo();
            BeanUtils.copyProperties(album, vo);

            if (engine.equals("local")) {
                 vo.setPath(GlobalConfig.publicPrefix + "/" + album.getUri());
            } else {
                vo.setPath(album.getUri());
            }
            vo.setUri(UrlUtils.toAbsoluteUrl(album.getUri()));
            vo.setSize(ToolsUtils.storageUnit(album.getSize()));
            vo.setCreateTime(TimeUtils.timestampToDate(album.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(album.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 相册文件重命名
     *
     * @author fzr
     * @param id 文件ID
     * @param name 文件名称
     */
    @Override
    public void albumRename(Integer id, String name) {
        Album album = albumMapper.selectOne(new QueryWrapper<Album>()
                .select("id", "name")
                .eq("id", id)
                .eq("is_delete", 0));

        Assert.notNull(album, "文件丢失！");

        album.setName(name);
        album.setUpdateTime(System.currentTimeMillis() / 1000);
        albumMapper.updateById(album);
    }

    /**
     * 相册文件移动
     *
     * @author fzr
     * @param ids 文件ID
     * @param cid 类目ID
     */
    @Override
    public void albumMove(List<Integer> ids, Integer cid) {
        List<Album> albums = albumMapper.selectList(new QueryWrapper<Album>()
                .select("id", "name")
                .in("id", ids)
                .eq("is_delete", 0));

        Assert.notNull(albums, "文件丢失！");

        if (cid > 0) {
            Assert.notNull(albumCateMapper.selectOne(
                    new QueryWrapper<AlbumCate>()
                            .eq("id", cid)
                            .eq("is_delete", 0)
            ), "类目已不存在！");
        }

        for (Album album : albums) {
            album.setCid(cid);
            album.setUpdateTime(System.currentTimeMillis() / 1000);
            albumMapper.updateById(album);
        }
    }

    /**
     * 相册文件新增
     *
     * @author fzr
     * @param params 文件信息参数
     */
    @Override
    public Integer albumAdd(Map<String, String> params) {
        Album album = new Album();
        album.setCid(Integer.parseInt(params.get("cid") == null ? "0" : params.get("cid")));
        album.setAid(Integer.parseInt(params.get("aid") == null ? "0" : params.get("aid")));
        album.setUid(Integer.parseInt(params.get("uid") == null ? "0" : params.get("uid")));
        album.setType(Integer.parseInt(params.get("type")));
        album.setName(params.get("name").substring(0, 99));
        album.setExt(params.get("ext"));
        album.setUri(params.get("url"));
        album.setSize(Long.parseLong(params.get("size")));
        album.setCreateTime(System.currentTimeMillis() / 1000);
        album.setUpdateTime(System.currentTimeMillis() / 1000);
        albumMapper.insert(album);
        return album.getId();
    }

    /**
     * 相册文件删除
     *
     * @author fzr
     * @param ids 文件ID
     */
    @Override
    public void albumDel(List<Integer> ids) {
        List<Album> albums = albumMapper.selectList(new QueryWrapper<Album>()
                .select("id", "name")
                .in("id", ids)
                .eq("is_delete", 0));

        Assert.notNull(albums, "文件丢失！");

        for (Album album : albums) {
            album.setIsDelete(1);
            album.setDeleteTime(System.currentTimeMillis() / 1000);
            albumMapper.updateById(album);
        }
    }

    /**
     * 相册分类列表
     *
     * @param searchValidate 搜索参数
     * @return JSONArray
     */
    @Override
    public JSONArray cateList(AlbumSearchValidate searchValidate) {
        QueryWrapper<AlbumCate> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(AlbumCate.class, info->
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"))
                .eq("is_delete", 0)
                .orderByDesc("id");

        if (StringUtils.isNotNull(searchValidate.getType()) && searchValidate.getType() > 0) {
            queryWrapper.eq("type", searchValidate.getType());
        }

        if (StringUtils.isNotNull(searchValidate.getKeyword()) && StringUtils.isNotEmpty(searchValidate.getKeyword())) {
            queryWrapper.like("name", searchValidate.getKeyword());
        }

        List<AlbumCate> albumCateList = albumCateMapper.selectList(queryWrapper);

        List<AlbumCateVo> lists = new LinkedList<>();
        for (AlbumCate albumCate : albumCateList) {
            AlbumCateVo vo = new AlbumCateVo();
            BeanUtils.copyProperties(albumCate, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(albumCate.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(albumCate.getUpdateTime()));
            lists.add(vo);
        }

        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(lists));
        return ArrayUtils.listToTree(jsonArray, "id", "pid", "children");
    }

    /**
     * 分类新增
     *
     * @author fzr
     * @param cateValidate 分类参数
     */
    @Override
    public void cateAdd(AlbumCateValidate cateValidate) {
        AlbumCate albumCate = new AlbumCate();
        albumCate.setType(cateValidate.getType());
        albumCate.setPid(cateValidate.getPid());
        albumCate.setName(cateValidate.getName());
        albumCate.setCreateTime(System.currentTimeMillis() / 1000);
        albumCate.setUpdateTime(System.currentTimeMillis() / 1000);
        albumCateMapper.insert(albumCate);
    }

    /**
     * 分类重命名
     *
     * @author fzr
     * @param id 分类ID
     * @param name 分类名称
     */
    @Override
    public void cateRename(Integer id, String name) {
        AlbumCate albumCate = albumCateMapper.selectOne(
                new QueryWrapper<AlbumCate>()
                        .select("id", "name")
                        .eq("id", id)
                        .eq("is_delete", 0));

        Assert.notNull(albumCate, "分类已不存在！");

        albumCate.setName(name);
        albumCate.setUpdateTime(System.currentTimeMillis() / 1000);
        albumCateMapper.updateById(albumCate);
    }

    /**
     * 分类删除
     *
     * @author fzr
     * @param id 分类ID
     */
    @Override
    public void cateDel(Integer id) {
        AlbumCate albumCate = albumCateMapper.selectOne(
                new QueryWrapper<AlbumCate>()
                        .select("id", "name")
                        .eq("id", id)
                        .eq("is_delete", 0));

        Assert.notNull(albumCate, "分类已不存在！");

        Assert.isNull(albumMapper.selectOne(new QueryWrapper<Album>()
                .select("id", "cid", "name")
                .eq("cid", id)
                .eq("is_delete", 0)
                .last("limit 1")
            ), "当前分类正被使用中,不能删除！");

        albumCate.setIsDelete(1);
        albumCate.setDeleteTime(System.currentTimeMillis() / 1000);
        albumCateMapper.updateById(albumCate);
    }

}
