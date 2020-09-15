package org.jeecg.modules.wxStudy.entity;

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
@Data
@TableName("wx_studyNew")
public class WxStudy implements Serializable {
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
	/**就读幼儿园*/
	@Excel(name = "就读幼儿园", width = 15)
	private java.lang.String kindergarten;
	/**就读学校*/
	@Excel(name = "就读学校", width = 15)
	private java.lang.String shcool;
	/**家庭常住地*/
	@Excel(name = "家庭常住地", width = 15)
	private java.lang.String famAddress;
	/**详细地址*/
	@Excel(name = "详细地址", width = 15)
	private java.lang.String detailAddress;
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
	@Excel(name = "正常余额", width = 15)
	private java.lang.String normalBalance;
	@Excel(name = "正常课时单价", width = 15)
	private java.lang.String normalUnitPrice;
	@Excel(name = "优惠余额", width = 15)
	private java.lang.String discountBalance;
	@Excel(name = "优惠课时单价", width = 15)
	private java.lang.String discountUnitPrice;
	@Excel(name = "折扣", width = 15)
	private java.lang.String zhekou;
	@Excel(name = "优惠券", width = 15)
	private java.lang.String youhui;
	@Excel(name = "消耗课时", width = 15)
	private java.lang.String xiaohaoKeshi;
	@Excel(name = "消耗正常学费", width = 15)
	private java.lang.String xiaohaoNomalXuefei;
	@Excel(name = "消耗优惠学费", width = 15)
	private java.lang.String xiaohaoDiscountXuefei;
	@Excel(name = "学费", width = 15)
	private java.lang.String xuefei;
	@Excel(name = "总课时", width = 15)
	private java.lang.String totalKeshi;
	@Excel(name = "实际缴纳学费", width = 15)
	private java.lang.String shijiXuefei;
	
	
	
}
