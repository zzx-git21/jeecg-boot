package org.jeecg.modules.wxTaskFileRel.entity;

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
 * @Description: 作业文件关联
 * @Author: jeecg-boot
 * @Date:   2019-12-21
 * @Version: V1.0
 */
@Data
@TableName("wx_task_file_rel")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_task_file_rel对象", description="作业文件关联")
public class WxTaskFileRel {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**作业id*/
	@Excel(name = "作业id", width = 15)
    @ApiModelProperty(value = "作业id")
	private java.lang.String taskId;
	/**文件id*/
	@Excel(name = "文件id", width = 15)
    @ApiModelProperty(value = "文件id")
	private java.lang.String fileId;
	public WxTaskFileRel(String taskId, String fileId) {
   		this.taskId = taskId;
   		this.fileId = fileId;
   	}
}
