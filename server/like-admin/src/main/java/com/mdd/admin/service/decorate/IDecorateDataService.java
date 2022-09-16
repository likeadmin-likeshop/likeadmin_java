package com.mdd.admin.service.decorate;

import java.util.List;
import java.util.Map;

/**
 * 装修数据服务接口类
 */
public interface IDecorateDataService {

    /**
     * 获取文章数据
     *
     * @author fzr
     * @param limit 条数
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> article(Integer limit);

}
