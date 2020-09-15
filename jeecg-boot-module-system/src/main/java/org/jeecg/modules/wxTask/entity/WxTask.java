package org.jeecg.modules.wxTask.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 作业
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Data
@TableName("wx_task")
public class WxTask implements Serializable {
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
	/**课程目标*/
	@Excel(name = "课程目标", width = 15)
	private java.lang.String courseTask;
	/**教材*/
	@Excel(name = "教材", width = 15)
	private java.lang.String teachingMaterial;
	/**家庭作业*/
	@Excel(name = "家庭作业", width = 15)
	private java.lang.String homeWork;
	/**复习语言*/
	@Excel(name = "复习语言", width = 15)
	private java.lang.String reviewLanguage;
	/**重点语言*/
	@Excel(name = "重点语言", width = 15)
	private java.lang.String keyLanguage;
	/**补充语言*/
	@Excel(name = "补充语言", width = 15)
	private java.lang.String suppLanguage;
	
	/**补充语言*/
	@Excel(name = "课表id", width = 15)
	@Dict(dicCode = "id",dictTable = "wx_kebiao",dicText = "zhouji")
	private java.lang.String kebiaoId;
	//private List<Map<String,Object>> recordInfoList;//录音列表
	//private java.lang.String teacher;//老师

	/**本月故事*/
	@Excel(name = "本月故事", width = 15)
	private java.lang.String thisMonthStory;
	
	@Excel(name = "课后建议", width = 15)
	private java.lang.String courseAfterRecommend;
}
