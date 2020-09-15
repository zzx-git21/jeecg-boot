package org.jeecg.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageParam {

    @ApiModelProperty("页数")
    private Integer pageNo = 1;
    @ApiModelProperty("每页数")
    private Integer pageSize = 100;
}
