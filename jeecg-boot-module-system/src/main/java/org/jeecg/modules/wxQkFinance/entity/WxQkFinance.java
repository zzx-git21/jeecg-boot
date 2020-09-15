package org.jeecg.modules.wxQkFinance.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 潜客缴费
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Data
@TableName("wx_qk_finance")
public class WxQkFinance implements Serializable {
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
	/**潜客姓名*/
	@Excel(name = "潜客姓名", width = 15)
	private java.lang.String qkName;
	/**报读学校*/
	@Excel(name = "报读学校", width = 15)
	private java.lang.String schoolName;
	/**所属学校id*/
	@Excel(name = "所属学校id", width = 15)
	private java.lang.String schoolId;
	/**登录名*/
	@Excel(name = "登录名", width = 15)
	private java.lang.String userName;
	/**招生季*/
	@Excel(name = "招生季", width = 15)
	private java.lang.String zhaoshengji;
	/**缴费类型*/
	@Excel(name = "缴费类型", width = 15)
	private java.lang.String jiaofeiType;
	/**课程系列*/
	@Excel(name = "课程系列", width = 15)
	private java.lang.String kechengCate;
	/**课程名称*/
	@Excel(name = "课程名称", width = 15)
	private java.lang.String kechengName;
	/**所属班级*/
	@Excel(name = "所属班级", width = 15)
	private java.lang.String kechengBanji;
	/**支付类型*/
	@Excel(name = "支付类型", width = 15)
	private java.lang.String payType;
	/**票据编号*/
	@Excel(name = "票据编号", width = 15)
	private java.lang.String piaoju;
	/**实际缴纳*/
	@Excel(name = "实际缴纳", width = 15)
	private java.lang.String shijifeiyong;
	/**折扣*/
	@Excel(name = "折扣", width = 15)
	private java.lang.String zhekou;
	/**liping*/
	@Excel(name = "liping", width = 15)
	private java.lang.String liping;
	/**缴费类型*/
	@Excel(name = "缴费类型", width = 15)
	private java.lang.String addType;
	/**发票开头*/
	@Excel(name = "发票开头", width = 15)
	private java.lang.String fapiaoTitle;
	/**发票金额*/
	@Excel(name = "发票金额", width = 15)
	private java.lang.String fapiaoJine;
	/**已上课时*/
	@Excel(name = "已上课时", width = 15)
	private java.lang.String hasKeshi;
	/**本次消费*/
	@Excel(name = "本次消费", width = 15)
	private java.lang.String benciXiaofei;
	/**剩余金额*/
	@Excel(name = "剩余金额", width = 15)
	private java.lang.String shengyuJine;
	/**应缴金额*/
	@Excel(name = "应缴金额", width = 15)
	private java.lang.String yinjiaoJine;
	/**备注*/
	@Excel(name = "备注", width = 15)
	private java.lang.String mark;
}
