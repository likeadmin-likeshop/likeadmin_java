package com.mdd.common.plugin.notice;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NoticeParams {

    private Integer scene;
    private String mobile;
    private String[] params;

}
