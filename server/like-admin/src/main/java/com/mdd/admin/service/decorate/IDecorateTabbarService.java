package com.mdd.admin.service.decorate;

import java.util.List;
import java.util.Map;

/**
 * 底部导航服务接口类
 */
public interface IDecorateTabbarService {

    /**
     * 底部导航详情
     *
     * @author fzr
     * @return  Map<String, Object>
     */
    Map<String, Object> detail();

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, Object> params);

}
