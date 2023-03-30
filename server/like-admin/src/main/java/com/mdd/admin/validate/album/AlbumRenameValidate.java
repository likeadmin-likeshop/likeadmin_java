package com.mdd.admin.validate.album;

import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@ApiModel("附件重命名参数")
public class AlbumRenameValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @ApiModelProperty(value = "附件ID", required = true)
    private Integer id;

    @NotEmpty(message = "名称不能为空")
    @Length(min = 1, max = 30, message = "名称不能大于30个字符")
    @ApiModelProperty(value = "附件名称", required = true)
    private String name;

}
