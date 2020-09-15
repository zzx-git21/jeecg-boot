package org.jeecg.modules.wxBanji.entity;

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
 * @Description: 班级管理
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Data
@TableName("wx_banji")
public class WxBanji implements Serializable {
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
	/**所属学校*/
	@Excel(name = "所属学校", width = 15)
	private java.lang.String sysOrgCode;
	/**所属校区*/
	@Excel(name = "所属校区", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_xiaoqu",dicText = "xiaoqu_name")
	private java.lang.String owernShcool;
	/**招生季code*/
	@Excel(name = "招生季code", width = 15)
	private java.lang.String seasonCode;
	/**招生季名称*/
	@Excel(name = "招生季名称", width = 15)
	private java.lang.String seasonName;
	/**班级名称*/
	@Excel(name = "班级名称", width = 15)
	private java.lang.String banjiName;
	/**课程系列*/
	@Excel(name = "课程系列", width = 15)
	private java.lang.String courseCode;
	/**课程名称code*/
	@Excel(name = "课程名称code", width = 15)
	private java.lang.String courseNameCode;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date startTime;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date endTime;
	/**预计学生数*/
	@Excel(name = "预计学生数", width = 15)
	private java.lang.Integer studentsNum;
	/**开课时间*/
	@Excel(name = "开课时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date courseStartTime;
	/**课时数*/
	@Excel(name = "课次数", width = 15)
	private java.lang.Double courseTime;
	/**描述*/
	@Excel(name = "描述", width = 15)
	private java.lang.String mark;
	/**年级表ID*/
	@Excel(name = "年级表ID", width = 20)
	@Dict(dicCode = "id",dictTable = "wx_grade",dicText = "grade_name")
	private java.lang.String gradeId;
	
	@Excel(name = "班主任", width = 15)
	private java.lang.String bzr;
	@Excel(name = "班主任姓名", width = 15)
	private java.lang.String bzrName;
	
	@Excel(name = "周几上课", width = 15)
	private java.lang.String oneDayWeek;
	@Excel(name = "一天上课开始时间", width = 15)
	private java.lang.String oneDayStart;
	@Excel(name = "一天上课结束时间", width = 15)
	private java.lang.String oneDayEnd;
	@Excel(name = "一天上课课时", width = 15)
	private java.lang.String oneDayCourseNum;
	
	
	@Excel(name = "教师", width = 15)
	private java.lang.String teacher;
	@Excel(name = "教师名称", width = 15)
	private java.lang.String teacherName;
	@Excel(name = "课程阶段", width = 15)
	private java.lang.String courselevel;
}
