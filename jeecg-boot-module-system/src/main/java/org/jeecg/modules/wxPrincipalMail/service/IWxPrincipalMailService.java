package org.jeecg.modules.wxPrincipalMail.service;

import java.util.List;

import org.jeecg.modules.wxChatMsg.entity.WxChatMsg;
import org.jeecg.modules.wxPrincipalMail.entity.WxPrincipalMail;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 校长信箱表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface IWxPrincipalMailService extends IService<WxPrincipalMail> {
	public List<WxPrincipalMail> maillist(WxPrincipalMail wxChatMsg);//获取邮箱列表
}
