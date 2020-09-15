package org.jeecg.modules.wxPrincipalMail.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 校长信箱表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Data
@TableName("wx_principal_mail")
public class WxPrincipalMail implements Serializable {
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
	/**发件人*/
	@Excel(name = "发件人", width = 15)
	private java.lang.String fromUserId;
	/**邮件内容*/
	@Excel(name = "邮件内容", width = 15)
	private java.lang.String msgContent;
	/**是否回复*/
	@Excel(name = "是否回复", width = 15)
	private java.lang.String replayFlag;
	/**收件人id*/
	@Excel(name = "收件人id", width = 15)
	private java.lang.String toUserId;
	/**标题*/
	@Excel(name = "标题", width = 15)
	private java.lang.String msgTitle;
}
