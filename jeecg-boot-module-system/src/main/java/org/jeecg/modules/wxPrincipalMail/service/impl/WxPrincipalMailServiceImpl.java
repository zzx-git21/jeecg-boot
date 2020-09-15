package org.jeecg.modules.wxPrincipalMail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecg.modules.wxChatMsg.mapper.WxChatMsgMapper;
import org.jeecg.modules.wxPrincipalMail.entity.WxPrincipalMail;
import org.jeecg.modules.wxPrincipalMail.mapper.WxPrincipalMailMapper;
import org.jeecg.modules.wxPrincipalMail.service.IWxPrincipalMailService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 校长信箱表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Service
public class WxPrincipalMailServiceImpl extends ServiceImpl<WxPrincipalMailMapper, WxPrincipalMail> implements IWxPrincipalMailService {
	@Resource
	private WxPrincipalMailMapper wxPrincipalMailMapper;
	@Override
	public List<WxPrincipalMail> maillist(WxPrincipalMail wxPrincipalMail) {
		// TODO Auto-generated method stub
		return wxPrincipalMailMapper.maillist(wxPrincipalMail);
	}

}
