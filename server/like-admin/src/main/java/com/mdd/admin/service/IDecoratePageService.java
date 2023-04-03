package com.mdd.admin.service;

import com.mdd.admin.validate.decorate.DecoratePageValidate;
import com.mdd.admin.vo.decorate.DecoratePageVo;

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
     * @param decoratePageValidate 参数
     */
    void save(DecoratePageValidate decoratePageValidate);
}
