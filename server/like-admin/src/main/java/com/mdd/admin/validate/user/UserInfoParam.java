package com.mdd.admin.validate.user;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户信息参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "username参数缺失")
    @NotEmpty(message = "账号不能为空")
    private String username;

    @NotNull(message = "realName参数缺失")
    @NotEmpty(message = "真实名称不能为空")
    private String realName;

    @NotNull(message = "sex参数缺失")
    @IntegerContains(values = {0, 1, 2}, message = "请正确选择性别")
    private Integer sex;

    private String mobile;

}
