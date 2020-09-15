package org.jeecg.modules.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("修改密码")
@Data
public class PwChangeVo {

    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPw;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPw;
    @ApiModelProperty(value = "二次输入新密码", required = true)
    private String twiceNewPw;

}
