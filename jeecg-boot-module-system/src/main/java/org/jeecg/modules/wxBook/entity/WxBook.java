package org.jeecg.modules.wxBook.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 图书管理
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Data
@TableName("wx_book")
public class WxBook implements Serializable {
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
	/**书籍编号*/
	@ApiModelProperty(value = "书籍编号")
	@Excel(name = "书籍编号", width = 15)
	private java.lang.String bookIsbn;
	/**数据名称*/
	@Excel(name = "数据名称", width = 15)
	@ApiModelProperty(value = "数据名称")
	private java.lang.String bookName;
	/**作者姓名*/
	@ApiModelProperty(value = "作者姓名")
	@Excel(name = "作者姓名", width = 15)
	private java.lang.String bookAuthor;
	/**出版时间*/
	@ApiModelProperty(value = "出版时间")
	@Excel(name = "出版时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date publishTime;
	/**出版社*/
	@ApiModelProperty(value = "出版社")
	@Excel(name = "出版社", width = 15)
	private java.lang.String publishId;
	/**出版社姓名*/
	@ApiModelProperty(value = "出版社姓名")
	@Excel(name = "出版社姓名", width = 15)
	private java.lang.String publishName;
	/**类别id*/
	@ApiModelProperty(value = "类别id")
	@Excel(name = "类别id", width = 15)
	private java.lang.String classId;
	/**类别名称*/
	@ApiModelProperty(value = "类别名称")
	@Excel(name = "类别名称", width = 15)
	private java.lang.String className;
	/**书本价格*/
	@ApiModelProperty(value = "书本价格")
	@Excel(name = "书本价格", width = 15)
	private java.lang.Double bookPrice;
	/**书本页数*/
	@ApiModelProperty(value = "书本页数")
	@Excel(name = "书本页数", width = 15)
	private java.lang.Integer bookPage;
	/**书籍开本*/
	@ApiModelProperty(value = "书籍开本")
	@Excel(name = "书籍开本", width = 15)
	private java.lang.String bookOpen;
	/**所属语言*/
	@ApiModelProperty(value = "所属语言")
	@Excel(name = "所属语言", width = 15)
	private java.lang.String ownLanague;
	/**印刷版面*/
	@ApiModelProperty(value = "印刷版面")
	@Excel(name = "印刷版面", width = 15)
	private java.lang.String bookLayout;
	/**配给物品*/
	@ApiModelProperty(value = "配给物品")
	@Excel(name = "配给物品", width = 15)
	private java.lang.String bookRate;
	/**备注信息*/
	@ApiModelProperty(value = "备注信息")
	@Excel(name = "备注信息", width = 15)
	private java.lang.String mark;
	/**条形码*/
	@ApiModelProperty(value = "条形码")
	@Excel(name = "条形码", width = 15)
	private java.lang.String stripMa;
	/**所属学校*/
	@ApiModelProperty(value = "所属学校")
	@Excel(name = "所属学校", width = 15)
	private java.lang.String ownShcool;
	/**书架位置*/
	@ApiModelProperty(value = "书架位置")
	@Excel(name = "书架位置", width = 15)
	private java.lang.String bookselfLocation;
	/**获得方式*/
	@ApiModelProperty(value = "获得方式")
	@Excel(name = "获得方式", width = 15)
	private java.lang.String bookGain;
	/**总藏书量*/
	@ApiModelProperty(value = "总藏书量")
	@Excel(name = "总藏书量", width = 15)
	private java.lang.Integer bookTotal;
	/**图书剩余*/
	@ApiModelProperty(value = "图书剩余")
	@Excel(name = "图书剩余", width = 15)
	private java.lang.Integer bookSurplus;
	/**是否可以点读*/
	@ApiModelProperty(value = "是否可以点读")
	@Excel(name = "是否可以点读", width = 15)
	private java.lang.Integer isPoint;
	/**图书图片*/
	@ApiModelProperty(value = "图书图片")
	@Excel(name = "图书图片", width = 15)
	private java.lang.String bookImg;
	/**是否推荐  0否 1是*/
	@ApiModelProperty(value = "是否推荐  0否 1是")
	@Excel(name = "是否推荐", width = 15)
	private java.lang.Integer isRecommend;



}
