package com.mdd.admin.service.impl;

import com.mdd.admin.service.ISystemCacheService;
import com.mdd.common.cache.ConfigCache;
import org.springframework.stereotype.Service;

/**
 * 系统缓存实现类
 */
@Service
public class SystemCacheServiceImpl implements ISystemCacheService {

    /**
     * 清除系统缓存
     */
    @Override
    public void clear() {
        ConfigCache.clear();
    }


}
