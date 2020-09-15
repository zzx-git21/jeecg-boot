package org.jeecg.modules.wxBookGradeRel.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 图书和年级关系
 * @Author: jeecg-boot
 * @Date:   2019-12-15
 * @Version: V1.0
 */
@Data
@TableName("wx_book_grade_rel")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_book_grade_rel对象", description="图书和年级关系")
public class WxBookGradeRel {
    
	/**主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**图书id*/
	@Excel(name = "图书id", width = 15)
    @ApiModelProperty(value = "图书id")
	private java.lang.String bookId;
	/**年级id*/
	@Excel(name = "年级id", width = 15)
    @ApiModelProperty(value = "年级id")
	private java.lang.String gradeId; 
}
