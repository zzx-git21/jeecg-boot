package org.jeecg.modules.wxBookReg.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.util.jsonschema.AmountSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 图书会员注册
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Data
@TableName("wx_book_reg")
public class WxBookReg implements Serializable {
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
	/**条形码*/
	@Excel(name = "条形码", width = 15)
	private java.lang.String stripMa;
	/**借阅卡状态*/
	@Excel(name = "借阅卡状态", width = 15)
	@Dict(dicCode = "strip_status")
	private java.lang.String stripStatus;
	/**会员等级*/
	@Excel(name = "会员等级", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_book_level",dicText = "level_name")
	private java.lang.String bookLevel;
	/**会员id*/
	@Excel(name = "会员id", width = 15)
	private java.lang.String userId;
	/**会员姓名*/
	@Excel(name = "会员姓名", width = 15)
	private java.lang.String userName;
	/**押金*/
	@Excel(name = "押金", width = 15)
	private java.math.BigDecimal desposit;

}
