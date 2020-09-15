package org.jeecg.modules.wxBook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("借书")
public class BookVO {

    @ApiModelProperty(value = "图书ID", required = true)
    private String boolId;
    @ApiModelProperty(value = "图书数量", required = true)
    private Integer boolNum;
    @ApiModelProperty(value = "借书天数", required = true)
    private Integer borrowDay;
    @ApiModelProperty(value = "图书名称", required = true)
    private String bookName;
    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    public String getBoolId() {
        return boolId;
    }

    public void setBoolId(String boolId) {
        this.boolId = boolId;
    }

    public Integer getBoolNum() {
        return boolNum;
    }

    public void setBoolNum(Integer boolNum) {
        this.boolNum = boolNum;
    }

    public Integer getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(Integer borrowDay) {
        this.borrowDay = borrowDay;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
