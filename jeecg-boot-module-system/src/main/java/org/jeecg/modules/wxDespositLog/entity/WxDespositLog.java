package org.jeecg.modules.wxDespositLog.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.common.util.jsonschema.AmountSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 押金记录
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
@Data
@TableName("wx_desposit_log")
public class WxDespositLog implements Serializable {
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
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	private java.lang.String userId;
	/**学生姓名*/
	@Excel(name = "学生姓名", width = 15)
	private java.lang.String studyName;
	/**发生时间*/
	@Excel(name = "发生时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date paymentTime;
	/**缴费类型  0.押金  1.罚款*/
	@Excel(name = "缴费类型  0.押金  1.罚款", width = 15)
	@Dict(dicCode = "payment_type")
	private java.lang.Integer paymentType;
	/**原押金*/
	@JsonSerialize(using = AmountSerializer.class)
	@Excel(name = "原押金", width = 15)
	private java.math.BigDecimal originalDesposit;
	/**本次所缴押金*/
	@JsonSerialize(using = AmountSerializer.class)
	@Excel(name = "本次所缴押金", width = 15)
	private java.math.BigDecimal paymentDesposit;
	/**应缴罚款*/
	@JsonSerialize(using = AmountSerializer.class)
	@Excel(name = "应缴罚款", width = 15)
	private java.math.BigDecimal shouldPenalty;
	/**实缴罚款*/
	@JsonSerialize(using = AmountSerializer.class)
	@Excel(name = "实缴罚款", width = 15)
	private java.math.BigDecimal paidPenalty;
	/**押金金额*/
	@JsonSerialize(using = AmountSerializer.class)
	@Excel(name = "押金金额", width = 15)
	private java.math.BigDecimal balanceDesposit;
	/**退费金额*/
	@JsonSerialize(using = AmountSerializer.class)
	@Excel(name = "退费金额", width = 15)
	private java.math.BigDecimal returnDesposit;


}
