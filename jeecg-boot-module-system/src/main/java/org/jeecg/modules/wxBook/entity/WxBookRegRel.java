package org.jeecg.modules.wxBook.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "借书记录表")
@Data
public class WxBookRegRel {

    private Long id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    @ApiModelProperty(value = "会员ID")
    private String bookRegId;

    @ApiModelProperty(value = "图书ID")
    private String bookId;

    @ApiModelProperty(value = "是否已借  0.等待审核 1.是  2.拒绝借阅")
    private Integer isBorrow;

    @ApiModelProperty(value = "是否已还  0.否 1.是 2等待审核")
    private Integer isReturned;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "借阅天数")
    private Integer borrowDay;

    @ApiModelProperty(value = "归还时间")
    private Date returnTime;

    @ApiModelProperty(value = "开始借阅时间")
    private Date startTime;

    @ApiModelProperty(value = "图书名称")
    private String bookName;
}