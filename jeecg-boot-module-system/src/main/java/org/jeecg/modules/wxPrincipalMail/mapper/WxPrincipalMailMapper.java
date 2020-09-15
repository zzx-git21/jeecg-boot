package org.jeecg.modules.wxPrincipalMail.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxPrincipalMail.entity.WxPrincipalMail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 校长信箱表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface WxPrincipalMailMapper extends BaseMapper<WxPrincipalMail> {
	public List<WxPrincipalMail> maillist(@Param("wxPrincipalMail")WxPrincipalMail wxPrincipalMail);
}
