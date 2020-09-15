package org.jeecg.modules.wxStory.entity;

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
 * @Description: 故事大会资料表
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Data
@TableName("wx_story")
public class WxStory implements Serializable {
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
	/**标题*/
	@Excel(name = "标题", width = 15)
	private java.lang.String title;
	/**类别*/
	@Excel(name = "类别", width = 15)
	private java.lang.String category;
	/**描述*/
	@Excel(name = "描述", width = 15)
	private java.lang.String storyDesc;
	/**资料正文*/
	@Excel(name = "资料正文", width = 15)
	private java.lang.String storyText;
	/**资料url*/
	@Excel(name = "资料url", width = 15)
	private java.lang.String storyUrl;
	
	@Excel(name = "图文url", width = 15)
	private java.lang.String imgUrl;
	
	@Excel(name = "是否允许下载", width = 15)
	private java.lang.String isDownload; 
	
	@Excel(name = "关键字", width = 15)
	private java.lang.String storyKey;
	
	
}
