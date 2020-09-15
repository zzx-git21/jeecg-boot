package org.jeecg.modules.wxTutorialData.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 活动用户辅导视频资料表
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
@ApiModel(description = "活动用户辅导视频资料表")
@Data
@TableName("wx_tutorial_data")
public class WxTutorialData implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@ApiModelProperty(value = "主键")
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
	/**资料标题*/
	@ApiModelProperty(value = "资料标题")
	@Excel(name = "资料标题", width = 15)
	private java.lang.String dataName;
	/**资料描述*/
	@ApiModelProperty(value = "资料描述")
	@Excel(name = "资料描述", width = 15)
	private java.lang.String dataDesc;
	/**资料正文*/
	@ApiModelProperty(value = "资料正文")
	@Excel(name = "资料正文", width = 15)
	private java.lang.String dataText;
	/**资料地址*/
	@ApiModelProperty(value = "资料地址")
	@Excel(name = "资料地址", width = 15)
	private java.lang.String dataUrl;
	/**年级ID*/
	@ApiModelProperty(value = "年级ID")
	@Excel(name = "年级ID", width = 15)
	private java.lang.Integer gradeId;
	/**是否推荐*/
	@ApiModelProperty(value = "是否推荐 0.否 1.是")
	@Excel(name = "是否推荐 0.否 1.是", width = 15)
	private java.lang.Integer isRecommend;

	@ApiModelProperty(value = "资料展示图片URL")
	@Excel(name = "资料展示图片URL", width = 15)
	private java.lang.String dataPic;
}
