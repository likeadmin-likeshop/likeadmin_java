package com.hxkj.common.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long count;       //总记录数
    private Integer pageNo;   //当前页码
    private Integer pageSize; //每页条数
    private List<T> lists;    //数据列表

    /**
     * PageHelper分页
     *
     * @author fzr
     * @param list 分页数据集
     * @param <T> 实体类型
     * @return PageList
     */
    public static <T> PageResult<T> pageHelper(List<T> list) {
        PageResult<T> pageResult = new PageResult<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageResult.setCount(pageInfo.getTotal());
        pageResult.setPageNo(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setLists(pageInfo.getList());
        return pageResult;
    }

    /**
     * PageHelper分页(数据额外处理)
     *
     * @author fzr
     * @param list 分页数据集
     * @param <T> 实体类型
     * @return PageList
     */
    public static <T> PageResult<T> pageHelper(List<T> list, List<T> data) {
        PageResult<T> pageResult = new PageResult<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        pageResult.setCount(pageInfo.getTotal());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setPageNo(pageInfo.getPageNum());
        pageResult.setLists(data);
        return pageResult;
    }

    /**
     * MyBatisPlus分页
     *
     * @author fzr
     * @param <T> 实体类型
     * @return PageList
     */
    public static <T> PageResult<T> iPageHandle(IPage<T> iPage) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCount(iPage.getTotal());
        pageResult.setPageNo((int) iPage.getCurrent());
        pageResult.setPageSize((int) iPage.getSize());
        pageResult.setLists(iPage.getRecords());
        return pageResult;
    }

    /**
     * MyBatisPlus分页(数据额外处理)
     *
     * @author fzr
     * @param <T> 实体类型
     * @return PageList
     */
    public static <T> PageResult<T> iPageHandle(Long total, Long pageNo, Long size, List<T> list) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCount(total);
        pageResult.setPageNo(Math.toIntExact(pageNo));
        pageResult.setPageSize(Math.toIntExact(size));
        pageResult.setLists(list);
        return pageResult;
    }

}
