package org.jeecg.modules.wxStudy.dto;

import java.io.Serializable;
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

public class WxStudyDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
	
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
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
	/**英文名*/
	@Excel(name = "英文名", width = 15)
	private java.lang.String englishName;
	
	/**联系人*/
	@Excel(name = "联系人", width = 15)
	private java.lang.String linkPerson;
	/**联系电话*/
	@Excel(name = "联系电话", width = 20)
	private java.lang.String linkMobile;
	/**用户表id*/
	@Excel(name = "用户表id", width = 15)
	private java.lang.String userId;
	@Excel(name = "班级id", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_banji",dicText = "banji_name")
	private java.lang.String banjiId;
	@Excel(name = "所在年级", width = 15)
	private java.lang.Long nianji;
	
	@Excel(name = "是否考勤", width = 15)
	private java.lang.Long isKaoqing;
	
}
