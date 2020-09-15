package org.jeecg.modules.wxBookLevel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;


/**
 * @Description: 图书会员等级
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Data
@TableName("wx_book_level")
public class WxBookLevel implements Serializable {
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
	/**等级名称*/
	@Excel(name = "等级名称", width = 15)
	private java.lang.String levelName;
	/**等级编号*/
	@Excel(name = "等级编号", width = 15)
	private java.lang.String levelCode;
	/**是否限制*/
	@Excel(name = "是否限制", width = 15)
	private java.lang.String isLimit;
	/**借出次数*/
	@Excel(name = "借出次数", width = 15)
	private java.lang.Integer borrowFrequency;
	/**借出数量*/
	@Excel(name = "借出数量", width = 15)
	private java.lang.Integer borrowCount;
	/**借出天数*/
	@Excel(name = "借出天数", width = 15)
	private java.lang.Double borrowTime;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private java.lang.String marks;
}
