package com.mdd.common.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PageResult<T> {

    /** 总记录数 **/
    private Long count;

    /** 当前页码 **/
    private Integer pageNo;

    /** 每页条数 **/
    private Integer pageSize;

    /** 扩展字段 **/
    private Map<String, Object> extend;

    /** 数据列表 **/
    private List<T> lists;

    /**
     * PageHelper分页
     *
     * @author fzr
     * @param list (分页数据集)
     * @param <T>  (泛型)
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
     * @param list (分页数据集)
     * @param <T>  (泛型)
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
     * @param iPage (分页)
     * @param <T>   (泛型)
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
     * @param total   (总条数)
     * @param pageNo  (当前页码)
     * @param size    (每页条数)
     * @param list    (列表数据)
     * @param <T>     (泛型)
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

    /**
     * MyBatisPlus分页(数据额外处理)
     *
     * @param total   (总条数)
     * @param pageNo  (当前页码)
     * @param size    (每页条数)
     * @param list    (列表数据)
     * @param extend  (扩展字段)
     * @param <T>     (泛型)
     * @return PageResult<T>
     */
    public static <T> PageResult<T> iPageHandle(Long total, Long pageNo, Long size, List<T> list, Map<String,Object> extend) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCount(total);
        pageResult.setPageNo(Math.toIntExact(pageNo));
        pageResult.setPageSize(Math.toIntExact(size));
        pageResult.setLists(list);
        pageResult.setExtend(extend);
        return pageResult;
    }

}
