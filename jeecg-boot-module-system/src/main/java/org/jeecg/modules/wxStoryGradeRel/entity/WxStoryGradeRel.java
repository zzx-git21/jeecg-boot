package org.jeecg.modules.wxStoryGradeRel.entity;

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
 * @Description: 故事和年级关系
 * @Author: jeecg-boot
 * @Date:   2019-12-15
 * @Version: V1.0
 */
@Data
@TableName("wx_story_grade_rel")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_story_grade_rel对象", description="故事和年级关系")
public class WxStoryGradeRel {
    
	/**主键*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**故事id*/
	@Excel(name = "故事id", width = 15)
    @ApiModelProperty(value = "故事id")
	private java.lang.String storyId;
	/**年级id*/
	@Excel(name = "年级id", width = 15)
    @ApiModelProperty(value = "年级id")
	private java.lang.String gradeId;
}
