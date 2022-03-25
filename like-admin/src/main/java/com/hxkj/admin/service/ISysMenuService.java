package com.hxkj.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxkj.admin.validate.SysMenuParam;
import com.hxkj.common.entity.system.SysMenu;

public interface ISysMenuService extends IService<SysMenu> {

    JSONArray lists();

    SysMenu detail(Integer id);

    void add(SysMenuParam sysMenuParam);

    void edit(SysMenuParam sysMenuParam);

    void del(Integer id);

}
