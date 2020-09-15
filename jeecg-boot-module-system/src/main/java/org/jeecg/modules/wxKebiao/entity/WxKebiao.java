package org.jeecg.modules.wxKebiao.entity;

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
 * @Description: 课表
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Data
@TableName("wx_kebiao")
public class WxKebiao implements Serializable {
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
	/**课时*/
	@Excel(name = "每次课时", width = 15)
	private java.lang.String courseTime;
	@Excel(name = "课次", width = 15)
	private java.lang.String courseTimes;
	@Excel(name = "总课时", width = 15)
	private java.lang.String totalCourseTime;
	/**周几*/
	@Excel(name = "周几", width = 15)
	private java.lang.String zhouji;
	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date startTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date endTime;
	/**上课开始时间*/
	@Excel(name = "上课开始时间", width = 15)
	/*@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")*/
	private java.lang.String courseStartTime;
	/**上课结束时间*/
	@Excel(name = "上课结束时间", width = 15)
	/*@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")*/
	private java.lang.String courseEndTime;
	/**课程状态*/
	@Excel(name = "课程状态", width = 15)
	@Dict(dicCode = "keshi_status")
	private java.lang.Integer keshiStatus;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private java.lang.String mark;
	/**考勤状态*/
	@Excel(name = "考勤状态", width = 15)
	@Dict(dicCode = "kaoqing_status")
	private java.lang.Integer kaoqingStatus;
	/**所属班级*/
	@Excel(name = "所属班级", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_banji",dicText = "banji_name")
	private java.lang.String banjiCode;
	/**所属班级*/
	@Excel(name = "老师", width = 15)
	private java.lang.String teacher;
	
	
	@Excel(name = "老师", width = 15)
	private java.lang.String teacherName;
	
	
	@Excel(name = "班主任", width = 15)
	private java.lang.String bzrName;
	
	@Excel(name = "课程名称", width = 15)
	private java.lang.String courseName;
	
	@Excel(name = "是否预约", width = 15)
	private java.lang.String ifyy;
}
