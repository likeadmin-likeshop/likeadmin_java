package com.mdd.admin.service;

import com.mdd.admin.vo.decorate.DecorateDataArticleVo;

import java.util.List;

/**
 * 装修数据服务接口类
 */
public interface IDecorateDataService {

    /**
     * 获取文章数据
     *
     * @author fzr
     * @param limit 条数
     * @return List<DecorateArticleDataVo>
     */
    List<DecorateDataArticleVo> article(Integer limit);

}
