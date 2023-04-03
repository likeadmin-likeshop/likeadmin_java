package com.mdd.admin.validate.album;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("附件移动参数")
public class AlbumMoveValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ids参数缺失")
    @ApiModelProperty(value = "附件ID", required = true)
    private List<Integer> ids;

    @NotNull(message = "cid参数缺失")
    @ApiModelProperty(value = "类目ID", required = true)
    private Integer cid;

}
