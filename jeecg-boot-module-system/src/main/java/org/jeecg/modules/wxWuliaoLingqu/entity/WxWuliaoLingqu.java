package org.jeecg.modules.wxWuliaoLingqu.entity;

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
 * @Description: 物料领取
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Data
@TableName("wx_wuliao_lingqu")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_wuliao_lingqu对象", description="物料领取")
public class WxWuliaoLingqu {
    
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
	/**库存Id*/
	@Excel(name = "库存Id", width = 15)
    @ApiModelProperty(value = "库存Id")
	private java.lang.String kucunId;
	/**物料*/
	@Excel(name = "物料", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_wuliao_category",dicText = "wuliao_name")
    @ApiModelProperty(value = "物料")
	private java.lang.String wuliaoId;
	/**批号*/
	@Excel(name = "批号", width = 15)
    @ApiModelProperty(value = "批号")
	private java.lang.String batchNum;
	/**领取数量*/
	@Excel(name = "领取数量", width = 15)
    @ApiModelProperty(value = "领取数量")
	private java.lang.String lingquNum;
	/**领取人*/
	@Excel(name = "领取人", width = 15)
    @ApiModelProperty(value = "领取人")
	private java.lang.String lingquMan;
	/**领取日期*/
	@Excel(name = "领取日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "领取日期")
	private java.util.Date lingquTime;
	/** 领取部门*/
	@Excel(name = " 领取部门", width = 15)
	@Dict(dicCode = "lingqu_dep")
    @ApiModelProperty(value = " 领取部门")
	private java.lang.String lingquDep;
	/**领用说明*/
	@Excel(name = "领用说明", width = 15)
    @ApiModelProperty(value = "领用说明")
	private java.lang.String lingquDesc;
	/**领用单位*/
	@Excel(name = "领用单位", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_xiaoqu",dicText = "xiaoqu_name")
    @ApiModelProperty(value = "领用单位")
	private java.lang.String lingquDanwei; 
	
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.lang.String danjia;
	
	/**领取状态*/
	@Excel(name = "领取状态", width = 15)
    @ApiModelProperty(value = "领取状态")
	private java.lang.String lingquStatus;
	
	/**物料类型*/
	@Excel(name = "物料类型", width = 15)
	@Dict(dicCode = "wuliao_type")
    @ApiModelProperty(value = "物料类型")
	private java.lang.String wuliaoType;
	
	/**确认出库时间*/
	@Excel(name = "确认出库时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "确认出库时间")
	private java.util.Date sureChukuTime;
	
	
	/**领取类型*/
	@Excel(name = "领取类型", width = 15)
    @ApiModelProperty(value = "领取类型")
	private java.lang.String lingquType;
}
