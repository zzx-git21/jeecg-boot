package org.jeecg.modules.wxBook.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.jeecg.common.PageParam;

@Data
public class BookListParam extends PageParam {

    @ApiModelProperty(value = "图书名", required = false)
    private String bookName = "";
}
