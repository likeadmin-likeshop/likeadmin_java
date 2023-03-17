package com.mdd.front.validate.users;

import com.mdd.common.validator.annotation.StringContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("新用户更新信息参数")
public class NewUserUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "昵称参数缺失")
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;

    @NotNull(message = "头像参数缺失")
    @ApiModelProperty(value = "头像", required = true)
    private String avatar;

}
