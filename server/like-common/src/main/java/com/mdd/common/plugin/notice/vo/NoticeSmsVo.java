package com.mdd.common.plugin.notice.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NoticeSmsVo {

    private Integer scene;
    private String mobile;
    private Integer expire;
    private String[] params;

}
