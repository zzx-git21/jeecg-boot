package org.jeecg.modules.wxWuliaoFenKucun.entity;

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
 * @Description: 分校库存
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Data
@TableName("wx_wuliao_fen_kucun")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_wuliao_fen_kucun对象", description="分校库存")
public class WxWuliaoFenKucun {
    
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
	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_wuliao_category",dicText = "wuliao_name")
    @ApiModelProperty(value = "物料名称")
	private java.lang.String wuliaoId;
	/**申请总数量*/
	@Excel(name = "申请总数量", width = 15)
    @ApiModelProperty(value = "申请总数量")
	private java.lang.String shenqingNum;
	/**实际发货数量*/
	@Excel(name = "实际发货数量", width = 15)
    @ApiModelProperty(value = "实际发货数量")
	private java.lang.String fahuoNum;
	/**申请单位*/
	@Excel(name = "申请单位", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_xiaoqu",dicText = "xiaoqu_name")

    @ApiModelProperty(value = "申请单位")
	private java.lang.String shenqingDanwei;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
	private java.lang.String danjia;
	/**包装说明*/
	@Excel(name = "包装说明", width = 15)
    @ApiModelProperty(value = "包装说明")
	private java.lang.String baozhuangDesc;
	/**物料参数*/
	@Excel(name = "物料参数", width = 15)
    @ApiModelProperty(value = "物料参数")
	private java.lang.String wuliaoParam;
	/**是否可领用*/
	@Excel(name = "是否可领用", width = 15)
    @ApiModelProperty(value = "是否可领用")
	private java.lang.String ifCanDingyong;
	/**物料存放地点*/
	@Excel(name = "物料存放地点", width = 15)
    @ApiModelProperty(value = "物料存放地点")
	private java.lang.String wuliaoCunfangAddr;
	
	
	/**批号*/
	@Excel(name = "批号", width = 15)
    @ApiModelProperty(value = "批号")
	private java.lang.String batchNum;
	
	/**物料状态*/
	@Excel(name = "物料状态", width = 15)
	@Dict(dicCode = "wuliao_status")
    @ApiModelProperty(value = "物料状态")
	private java.lang.String wuliaoStatus;
	
	
	/**申请单状态*/
	@Excel(name = "申请单状态", width = 15)
	@Dict(dicCode = "shenqing_status")
    @ApiModelProperty(value = "申请单状态")
	private java.lang.String shenqingStatus;
	
	/**当前数量*/
	@Excel(name = "当前数量", width = 15)
    @ApiModelProperty(value = "当前数量")
	private java.lang.String currentNum;
	
	/**申请日期*/
	@Excel(name = "shenqingTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "shenqingTime")
	private java.util.Date shenqingTime;
	
	/**入库日期*/
	@Excel(name = "rukuTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "rukuTime")
	private java.util.Date rukuTime;
	
}
