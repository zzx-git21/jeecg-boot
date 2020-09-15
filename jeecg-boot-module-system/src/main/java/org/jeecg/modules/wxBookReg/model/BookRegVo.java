package org.jeecg.modules.wxBookReg.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.util.jsonschema.AmountSerializer;
import org.jeecg.modules.wxBook.model.BookRegRelVO;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookRegVo {

    @ApiModelProperty(value = "借阅卡ID")
    private String regId;
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    @ApiModelProperty(value = "性别(0-默认未知,1-男,2-女)")
    private Integer sex;
    @ApiModelProperty(value = "借阅卡状态")
    private String stripStatus;
    @ApiModelProperty(value = "会员等级")
    private String bookLevel;
    @ApiModelProperty(value = "头像")
    private String pic;
    @JsonSerialize(using = AmountSerializer.class)
    @ApiModelProperty(value = "押金")
    private BigDecimal desposit;
    @ApiModelProperty(value = "已借")
    private Integer borrowedCount;
    @ApiModelProperty(value = "借书记录")
    private List<BookRegRelVO> bookRegRelVos;
}
