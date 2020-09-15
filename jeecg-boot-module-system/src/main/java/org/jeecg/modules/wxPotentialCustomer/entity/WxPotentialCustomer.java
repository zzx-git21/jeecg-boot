package org.jeecg.modules.wxPotentialCustomer.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 潜客,潜在客户
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
@Data
@TableName("wx_potential_customer")
public class WxPotentialCustomer implements Serializable {
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
	/**呼叫类型 1.呼入渠道 2.呼出渠道*/
	@Dict(dicCode = "call_type")
	@Excel(name = "呼叫类型 1.呼入渠道 2.呼出渠道", width = 15)
	private java.lang.Integer callType;
	/**报读校区*/
	@Excel(name = "报读校区", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_xiaoqu",dicText = "xiaoqu_name")
	private java.lang.String signupSchool;
	/**学生姓名*/
	@Excel(name = "学生姓名", width = 15)
	private java.lang.String studentName;
	/**学生年龄*/
	@Excel(name = "学生年龄", width = 15)
	private java.lang.Integer studentAge;
	/**父亲姓名*/
	@Excel(name = "父亲姓名", width = 15)
	private java.lang.String fatherName;
	/**母亲姓名*/
	@Excel(name = "母亲姓名", width = 15)
	private java.lang.String motherName;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
	private java.lang.String telephone;
	/**联系电话2*/
	@Excel(name = "联系电话2", width = 15)
	private java.lang.String telephoneTwo;
	/**家长邮箱*/
	@Excel(name = "家长邮箱", width = 15)
	private java.lang.String parentEmail;
	/**家庭住址*/
	@Excel(name = "家庭住址", width = 15)
	private java.lang.String familyAddress;
	/**现就读学校*/
	@Excel(name = "现就读学校", width = 15)
	private java.lang.String currentSchool;
	/**现就读年级*/
	@Excel(name = "现就读年级", width = 15)
	private java.lang.String currentGrade;
	/**信息渠道*/
	@Excel(name = "信息渠道", width = 15)
	private java.lang.String channelInformation;
	/**是否有效 0.待审核  1.有效 2.无效*/
	@Dict(dicCode = "is_vaild")
	@Excel(name = "是否有效 0.待审核  1.有效 2.无效", width = 15)
	private java.lang.String isValid;
	/**是否报读 0.待考虑 1.是 2.否*/
	@Dict(dicCode = "is_signup")
	@Excel(name = "是否报读 0.待考虑 1.是 2.否", width = 15)
	private java.lang.String isSignup;
	/**归属人*/
	@Dict(dicCode = "id",dictTable = "sys_user",dicText = "realname")
	@Excel(name = "归属人", width = 15)
	private java.lang.String belonger;
	/**收集人*/
	@Dict(dicCode = "id",dictTable = "sys_user",dicText = "realname")
	@Excel(name = "收集人", width = 15)
	private java.lang.String collecter;
	/**学生英文名*/
	@Excel(name = "学生英文名", width = 15)
	private java.lang.String studentEnglishname;
	/**测试结果*/
	@Excel(name = "测试结果", width = 15)
	private java.lang.String testResult;
	/**教师推荐*/
	@Excel(name = "教师推荐", width = 15)
	private java.lang.String recommendTeacher;
	/**报读倾向*/
	@Excel(name = "报读倾向", width = 15)
	private java.lang.String studyTendency;
	/**咨询课程  多个课程用英文逗号分隔","*/
	@Excel(name = "咨询课程  多个课程用英文逗号分隔\",\"", width = 15)
	private java.lang.String counselingCourse;
	
	
	
	@Excel(name = "跟进时间", width = 20, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date gjTime;
	
	@Excel(name = "二次预约时间", width = 20, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date ecyyTime;
	
	@Excel(name = "预约试听课", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_kebiao",dicText = "course_name")
	private java.lang.String yystk;
	
	@Excel(name = "最近沟通时间", width = 20, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date zjgtTime;
	@Excel(name = "预约状态", width = 15)
	@Dict(dicCode = "yyzt")
	private java.lang.String yyzt;
	
	@Excel(name = "预约结果", width = 15)
	@Dict(dicCode = "yyjg")
	private java.lang.String yyjg;
	
	@Excel(name = "预约校区", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_kebiao",dicText = "course_name")
	private java.lang.String yyxq;
	
	@Excel(name = "派单顾问", width = 15)
	private java.lang.String pdgw;
	
	@Excel(name = "预约到访时间", width = 15)
	private java.lang.String yydfsj;
	
	@Excel(name = "到访状态", width = 15)
	@Dict(dicCode = "dfzt")
	private java.lang.String dfzt;
	
	@Excel(name = "报读概率", width = 15)
	@Dict(dicCode = "bdgl")
	private java.lang.String bdgl;
	
	@Excel(name = "预约类型", width = 15)
	@Dict(dicCode = "yytype")
	private java.lang.String yytype;
	
	@Excel(name = "陪同人员", width = 15)
	@Dict(dicCode = "ptry")
	private java.lang.String ptry;
	
	
	@Excel(name = "数据来源", width = 15)
	@Dict(dicCode = "sjly")
	private java.lang.String sjly;
	
	@Excel(name = "数据批号", width = 15)
	private java.lang.String sjph;
	@Excel(name = "沟通内容", width = 15)
	private java.lang.String gtdesc;


}
