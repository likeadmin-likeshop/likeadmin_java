package com.mdd.admin.service.decorate;

import com.mdd.admin.validate.decorate.DecoratePageParam;
import com.mdd.admin.vo.decorate.DecoratePageVo;

import java.util.Map;

public interface IDecoratePageService {

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return DecoratePageVo
     */
    DecoratePageVo detail(Integer id);

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageParam 参数
     */
    void save(DecoratePageParam decoratePageParam);
}
