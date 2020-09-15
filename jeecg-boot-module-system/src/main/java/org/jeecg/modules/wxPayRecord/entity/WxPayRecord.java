package org.jeecg.modules.wxPayRecord.entity;

import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 缴费记录
 * @Author: jeecg-boot
 * @Date:   2020-03-18
 * @Version: V1.0
 */
@Data
@TableName("wx_pay_record")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_pay_record对象", description="缴费记录")
public class WxPayRecord {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**校区*/
	@Excel(name = "校区", width = 15)
    @ApiModelProperty(value = "校区")
	private java.lang.String xiaoqu;
	/**登录名*/
	@Excel(name = "登录名", width = 15)
    @ApiModelProperty(value = "登录名")
	private java.lang.String username;
	/**当前招生季*/
	@Excel(name = "当前招生季", width = 15)
    @ApiModelProperty(value = "当前招生季")
	private java.lang.String currentEnrollment;
	/**存取类型*/
	@Excel(name = "存取类型", width = 15)
    @ApiModelProperty(value = "存取类型")
	private java.lang.String cunquType;
	/**课程系列*/
	@Excel(name = "课程系列", width = 15)
    @ApiModelProperty(value = "课程系列")
	private java.lang.String courseSeries;
	/**课程名称*/
	@Excel(name = "课程名称", width = 15)
    @ApiModelProperty(value = "课程名称")
	private java.lang.String courseName;
	/**班级*/
	@Excel(name = "班级", width = 15)
    @ApiModelProperty(value = "班级")
	private java.lang.String banji;
	/**课程费用*/
	@Excel(name = "课程费用", width = 15)
    @ApiModelProperty(value = "课程费用")
	private java.lang.String courseCost;
	/**支付类型*/
	@Excel(name = "支付类型", width = 15)
    @ApiModelProperty(value = "支付类型")
	private java.lang.String payType;
	/**折扣*/
	@Excel(name = "折扣", width = 15)
    @ApiModelProperty(value = "折扣")
	@Dict(dicCode = "discount")
	private java.lang.String discount;
	/**礼品*/
	@Excel(name = "礼品", width = 15)
    @ApiModelProperty(value = "礼品")
	private java.lang.String gift;
	/**学费金额*/
	@Excel(name = "学费金额", width = 15)
    @ApiModelProperty(value = "学费金额")
	private java.lang.String xuefeiAmount;
	/**往届学费金额*/
	@Excel(name = "往届学费金额", width = 15)
    @ApiModelProperty(value = "往届学费金额")
	private java.lang.String beforeXuefeiAmount;
	@Excel(name = "潜客id", width = 15)
    @ApiModelProperty(value = "潜客id")
	private java.lang.String qiankeid;
	@Excel(name = "学费", width = 15)
	private java.lang.String xf;
	@Excel(name = "书本费", width = 15)
	private java.lang.String sbf;
	@Excel(name = "点读笔费", width = 15)
	private java.lang.String ddbf;
	@Excel(name = "其他费用", width = 15)
	private java.lang.String qtfy;
	@Excel(name = "优惠", width = 15)
	private java.lang.String youhui;
	@Excel(name = "学生id", width = 15)
	private java.lang.String studyId;
	@Excel(name = "课时", width = 15)
	private java.lang.String keshi;
	
	@Excel(name = "备注", width = 15)
	private java.lang.String remark;
	@Excel(name = "折扣条件", width = 15)
	private java.lang.String discountCondition;
}
