package com.mdd.admin.validate;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 附件移动参数
 */
@Data
public class AlbumMoveValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ids参数缺失")
    private List<Integer> ids;

    @NotNull(message = "cid参数缺失")
    private Integer cid;

}
