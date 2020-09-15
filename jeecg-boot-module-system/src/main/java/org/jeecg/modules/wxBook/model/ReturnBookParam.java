package org.jeecg.modules.wxBook.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReturnBookParam {

    @ApiModelProperty("借阅卡ID")
    private String bookRegId;

    @ApiModelProperty("借阅记录ID")
    private List<String> bookRegRelIds;

}
