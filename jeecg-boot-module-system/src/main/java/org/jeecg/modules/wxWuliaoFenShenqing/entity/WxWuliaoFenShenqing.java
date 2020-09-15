package org.jeecg.modules.wxWuliaoFenShenqing.entity;

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
 * @Description: 分校物料申请
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Data
@TableName("wx_wuliao_fen_shenqing")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wx_wuliao_fen_shenqing对象", description="分校物料申请")
public class WxWuliaoFenShenqing {
    
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
	/**总校库存Id*/
	@Excel(name = "总校库存Id", width = 15)
    @ApiModelProperty(value = "总校库存Id")
	private java.lang.String zongKucunId;
	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_wuliao_category",dicText = "wuliao_name")
    @ApiModelProperty(value = "物料名称")
	private java.lang.String wuliaoId;
	/**申请单位*/
	@Excel(name = "申请单位", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_xiaoqu",dicText = "xiaoqu_name")
    @ApiModelProperty(value = "申请单位")
	private java.lang.String shenqingDanwei;
	/**申请数量*/
	@Excel(name = "申请数量", width = 15)
    @ApiModelProperty(value = "申请数量")
	private java.lang.String shenqingNum;
	/**申请用途*/
	@Excel(name = "申请用途", width = 15)
    @ApiModelProperty(value = "申请用途")
	private java.lang.String shenqingYongtu;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String shenqingDesc;
	/**申请类型*/
	@Excel(name = "申请类型", width = 15)
	@Dict(dicCode = "shenqing_type")
    @ApiModelProperty(value = "申请类型")
	private java.lang.String shenqingType;
	/**申请日期*/
	@Excel(name = "申请日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "申请日期")
	private java.util.Date shenqingTime;
	/**申请人*/
	@Excel(name = "申请人", width = 15)
    @ApiModelProperty(value = "申请人")
	private java.lang.String shenqingMan;
	/**分校审核状态*/
	@Excel(name = "分校审核状态", width = 15)
	@Dict(dicCode = "fen_check_status")
    @ApiModelProperty(value = "分校审核状态")
	private java.lang.String fenCheckStatus;
	/**分校审核备注*/
	@Excel(name = "分校审核备注", width = 15)
    @ApiModelProperty(value = "分校审核备注")
	private java.lang.String fenCheckMark;
	/**总校审核状态*/
	@Excel(name = "总校审核状态", width = 15)
	@Dict(dicCode = "zong_check_status")
    @ApiModelProperty(value = "总校审核状态")
	private java.lang.String zongCheckStatus;
	/**总校审核状态*/
	@Excel(name = "总校审核备注", width = 15)
    @ApiModelProperty(value = "总校审核备注")
	private java.lang.String zongCheckMark;
	/**申请单状态*/
	@Excel(name = "申请单状态", width = 15)
	@Dict(dicCode = "shenqing_status")
    @ApiModelProperty(value = "申请单状态")
	private java.lang.String shenqingStatus;
	/**发货日期*/
	@Excel(name = "发货日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发货日期")
	private java.util.Date fahuoTime;
	
	/**发货人*/
	@Excel(name = "发货人", width = 15)
    @ApiModelProperty(value = "发货人")
	private java.lang.String fahuoMan;
	
	/**发货订单号*/
	@Excel(name = "发货订单号", width = 15)
    @ApiModelProperty(value = "发货订单号")
	private java.lang.String fahuoNum;
	
	/**是否入库*/
	@Excel(name = "是否入库", width = 15)
    @ApiModelProperty(value = "是否入库")
	private java.lang.String ifRuku;
	
	/**发货方式*/
	@Excel(name = "发货方式", width = 15)
    @ApiModelProperty(value = "发货方式")
	private java.lang.String fahuoFangshi;
	/**发货费用*/
	@Excel(name = "发货费用", width = 15)
    @ApiModelProperty(value = "发货费用")
	private java.lang.String fahuoFeiyong;
	/**发货备注*/
	@Excel(name = "发货备注", width = 15)
    @ApiModelProperty(value = "发货备注")
	private java.lang.String fahuoMark;
}
