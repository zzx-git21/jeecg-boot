package org.jeecg.modules.wxCourseLevel.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 课程级别
 * @Author: jeecg-boot
 * @Date:   2020-06-08
 * @Version: V1.0
 */
@Data
@TableName("wx_course_level")
public class WxCourseLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	/**年级id*/
	@Excel(name = "年级id", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_grade",dicText = "grade_name")
	private java.lang.String gradeId;
	/**课程级别名称*/
	@Excel(name = "课程级别名称", width = 15)
	private java.lang.String levelName;
	/**课次*/
	@Excel(name = "课次", width = 15)
	private java.lang.String kc;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private java.lang.String mark;

	@Excel(name = "学费", width = 15)
	private java.lang.String xuefei;
	
	@Excel(name = "课时", width = 15)
	private java.lang.String keshi;
	
	@Excel(name = "课时单价", width = 15)
	private java.lang.String courseUnitPrice;
	
}
