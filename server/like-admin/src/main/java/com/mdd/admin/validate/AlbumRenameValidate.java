package com.mdd.admin.validate;

import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 附件重命名参数
 */
@Data
public class AlbumRenameValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotEmpty(message = "名称不能为空")
    @Length(min = 1, max = 30, message = "名称不能大于30个字符")
    private String name;

}
