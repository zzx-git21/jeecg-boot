package org.jeecg.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class IDParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通用id", required = true)
    private String id;
}
