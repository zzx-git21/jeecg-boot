package org.jeecg.modules.wxChatMsg.entity;

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
 * @Description: 皖新学生管理
 * @Author: jeecg-boot
 * @Date:   2019-10-23
 * @Version: V1.0
 */
@Data
@TableName("wx_studyNew")
public class WxChatDto implements Serializable {
    private static final long serialVersionUID = 1L;
 
	/**学生姓名*/
	@Excel(name = "学生姓名", width = 15)
	private java.lang.String studyName;
	/**学籍号*/
	@Excel(name = "学籍号", width = 15)
	private java.lang.String xuejiHao;
	/**登录名*/
	@Excel(name = "登录名", width = 15)
	private java.lang.String userName;
	/**性别*/
	@Excel(name = "性别", width = 15)
	@Dict(dicCode = "sex")
	private java.lang.Integer sex;
	
	@Excel(name = "用户表id", width = 15)
	private java.lang.String userId;
	@Excel(name = "班级id", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_banji",dicText = "banji_name")
	private java.lang.String banjiId;
	@Excel(name = "所在年级", width = 15)

	/**
	 * 是否有未读信息
	 */
	private java.lang.String isRead; 
}
