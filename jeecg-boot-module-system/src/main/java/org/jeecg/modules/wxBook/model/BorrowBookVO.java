package org.jeecg.modules.wxBook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@ApiModel("借书")
public class BorrowBookVO {


    @ApiModelProperty("借阅卡ID")
    private String bookRegId;

    @ApiModelProperty("图书列表")
    private List<BookVO> bookVOList;

    public String getBookRegId() {
        return bookRegId;
    }

    public void setBookRegId(String bookRegId) {
        this.bookRegId = bookRegId;
    }

    public List<BookVO> getBookVOList() {
        return bookVOList;
    }

    public void setBookVOList(List<BookVO> bookVOList) {
        this.bookVOList = bookVOList;
    }


}
