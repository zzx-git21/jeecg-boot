package org.jeecg.modules.wxFapiao.entity;

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
 * @Description: 发票
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Data
@TableName("wx_fapiao")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_fapiao对象", description="发票")
public class WxFapiao {
    
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
	/**updateTime*/
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private java.lang.String sysOrgCode;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private java.lang.String name;
	/**学籍号*/
	@Excel(name = "学籍号", width = 15)
    @ApiModelProperty(value = "学籍号")
	private java.lang.String xuejiNum;
	/**收据号*/
	@Excel(name = "收据号", width = 15)
    @ApiModelProperty(value = "收据号")
	private java.lang.String shoujuNum;
	/**当前招生季*/
	@Excel(name = "当前招生季", width = 15)
    @ApiModelProperty(value = "当前招生季")
	private java.lang.String currentZhaoshengji;
	/**发票抬头*/
	@Excel(name = "发票抬头", width = 15)
    @ApiModelProperty(value = "发票抬头")
	private java.lang.String fapiaoTaitou;
	/**发票金额*/
	@Excel(name = "发票金额", width = 15)
    @ApiModelProperty(value = "发票金额")
	private java.lang.String fapiaoAmount;
	/**发票状态*/
	@Excel(name = "发票状态", width = 15)
    @ApiModelProperty(value = "发票状态")
	private java.lang.String fapiaoStatus;
	/**操作人员*/
	@Excel(name = "操作人员", width = 15)
    @ApiModelProperty(value = "操作人员")
	private java.lang.String operatePerson;
	/**操作时间*/
	@Excel(name = "操作时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private java.util.Date operateTime;
	/**是否拒绝*/
	@Excel(name = "是否拒绝", width = 15)
    @ApiModelProperty(value = "是否拒绝")
	private java.lang.String ifRefuse;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String mark;
	/**确认人（分校）*/
	@Excel(name = "确认人（分校）", width = 15)
    @ApiModelProperty(value = "确认人（分校）")
	private java.lang.String querenPersonFenxiao;
	/**确认时间（分校）*/
	@Excel(name = "确认时间（分校）", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "确认时间（分校）")
	private java.util.Date querenTimeFenxiao;
	/**确认人（总校）*/
	@Excel(name = "确认人（总校）", width = 15)
    @ApiModelProperty(value = "确认人（总校）")
	private java.lang.String querenPersonZongxiao;
	/**确认时间（总校）*/
	@Excel(name = "确认时间（总校）", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "确认时间（总校）")
	private java.util.Date querenTimeZongxiao;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private java.lang.String businessType;
	/**业务id*/
	@Excel(name = "业务id", width = 15)
    @ApiModelProperty(value = "业务id")
	private java.lang.String businessId;
}
