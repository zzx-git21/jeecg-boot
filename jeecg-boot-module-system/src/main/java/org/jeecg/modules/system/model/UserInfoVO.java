package org.jeecg.modules.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("更新UserInfo")
@Data
public class UserInfoVO {

    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "性别(0-默认未知,1-男,2-女)")
    private Integer sex;
    @ApiModelProperty(value = "生日 e:20191212")
    private String birthday;
}
