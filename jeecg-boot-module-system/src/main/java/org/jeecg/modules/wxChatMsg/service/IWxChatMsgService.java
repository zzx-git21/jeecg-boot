package org.jeecg.modules.wxChatMsg.service;

import java.util.List;

import org.jeecg.modules.wxChatMsg.entity.WxChatMsg;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 消息描述表
 * @Author: jeecg-boot
 * @Date:   2020-06-11
 * @Version: V1.0
 */
public interface IWxChatMsgService extends IService<WxChatMsg> {
	public Page<WxChatMsg> queryList(Page<WxChatMsg> page,WxChatMsg wxChatMsg);
	
	public List<WxChatMsg> chatlist(WxChatMsg wxChatMsg);
	
	public void updateWxChatMsg(WxChatMsg wxChatMsg);


}
