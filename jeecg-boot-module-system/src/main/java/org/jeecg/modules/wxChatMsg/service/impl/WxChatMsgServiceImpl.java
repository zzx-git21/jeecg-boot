package org.jeecg.modules.wxChatMsg.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.jeecg.modules.wxChatMsg.entity.WxChatDto;
import org.jeecg.modules.wxChatMsg.entity.WxChatMsg;
import org.jeecg.modules.wxChatMsg.mapper.WxChatMsgMapper;
import org.jeecg.modules.wxChatMsg.service.IWxChatMsgService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 消息描述表
 * @Author: jeecg-boot
 * @Date:   2020-06-11
 * @Version: V1.0
 */
@Service
public class WxChatMsgServiceImpl extends ServiceImpl<WxChatMsgMapper, WxChatMsg> implements IWxChatMsgService {
	@Resource
	private WxChatMsgMapper wxChatMsgMapper;
	@Override
	public Page<WxChatMsg> queryList(Page<WxChatMsg> page, WxChatMsg wxChatMsg) {
		// TODO Auto-generated method stub
		return  page.setRecords(wxChatMsgMapper.queryList(page, wxChatMsg));
	}
	@Override
	public List<WxChatMsg> chatlist(WxChatMsg wxChatMsg) {
		// TODO Auto-generated method stub
		return wxChatMsgMapper.chatlist(wxChatMsg);
	}
	@Override
	public void updateWxChatMsg(WxChatMsg wxChatMsg) {
		// TODO Auto-generated method stub
		wxChatMsgMapper.updateWxChatMsg(wxChatMsg);
	
	}

}
