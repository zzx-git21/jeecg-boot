package org.jeecg.modules.wxBook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "借阅记录")
@Data
public class BookRegRelVO {

    @ApiModelProperty(value = "借阅ID")
    private String id;
    @ApiModelProperty(value = "图书ID")
    private String bookId;
    @ApiModelProperty(value = "图书名称")
    private String bookName;
    @ApiModelProperty(value = "开始借阅时间")
    private Date startTime;
    @ApiModelProperty(value = "归还时间")
    private Date returnTime;

    @ApiModelProperty(value = "状态 0.等待借阅审核 1.借阅中 2.等待还书审核 3.已还 4.借阅审核未通过")
    private Integer status;
    @ApiModelProperty(value = "学籍号")
    private String xuejihao;
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

}
