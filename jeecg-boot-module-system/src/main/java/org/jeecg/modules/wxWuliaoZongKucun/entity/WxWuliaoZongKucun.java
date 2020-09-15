package org.jeecg.modules.wxWuliaoZongKucun.entity;

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
 * @Description: 总校库存
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Data
@TableName("wx_wuliao_zong_kucun")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_wuliao_zong_kucun对象", description="总校库存")
public class WxWuliaoZongKucun {
    
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
	/**批号*/
	@Excel(name = "批号", width = 15)
    @ApiModelProperty(value = "批号")
	private java.lang.String batchNum;
	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_wuliao_category",dicText = "wuliao_name")
    @ApiModelProperty(value = "物料名称")
	private java.lang.String wuliaoId;
	/**总数量*/
	@Excel(name = "总数量", width = 15)
    @ApiModelProperty(value = "总数量")
	private java.lang.String totalNum;
	/**当前数量*/
	@Excel(name = "当前数量", width = 15)
    @ApiModelProperty(value = "当前数量")
	private java.lang.String currentNum;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.lang.String unitPrice;
	/**预计到货时间*/
	@Excel(name = "预计到货时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "预计到货时间")
	private java.util.Date yujiDaohuoTime;
	/**物料类型*/
	@Excel(name = "物料类型", width = 15)
	@Dict(dicCode = "wuliao_type")
    @ApiModelProperty(value = "物料类型")
	private java.lang.String wuliaoType;
	/**入库类型*/
	@Excel(name = "入库类型", width = 15)
	@Dict(dicCode = "ruku_type")
    @ApiModelProperty(value = "入库类型")
	private java.lang.String rukuType;
	/**预警值*/
	@Excel(name = "预警值", width = 15)
    @ApiModelProperty(value = "预警值")
	private java.lang.String yujingzhi;
	/**是否可领用*/
	@Excel(name = "是否可领用", width = 15)
	@Dict(dicCode = "if_can_dingyong")
    @ApiModelProperty(value = "是否可领用")
	private java.lang.String ifCanDingyong;
	/**物料存放地点*/
	@Excel(name = "物料存放地点", width = 15)
    @ApiModelProperty(value = "物料存放地点")
	private java.lang.String wuliaoCunfangAddr;
	/**包装说明*/
	@Excel(name = "包装说明", width = 15)
    @ApiModelProperty(value = "包装说明")
	private java.lang.String baozhuangDesc;
	/**物料参数*/
	@Excel(name = "物料参数", width = 15)
    @ApiModelProperty(value = "物料参数")
	private java.lang.String wuliaoParam;
	/**已上传图片*/
	@Excel(name = "已上传图片", width = 15)
    @ApiModelProperty(value = "已上传图片")
	private java.lang.String uploadPhone;
	/**入库时间*/
	@Excel(name = "入库时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入库时间")
	private java.util.Date rukuTime;
	/**入库人姓名*/
	@Excel(name = "入库人姓名", width = 15)
    @ApiModelProperty(value = "入库人姓名")
	private java.lang.String rukuMan;
}
