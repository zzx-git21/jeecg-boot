package org.jeecg.modules.wxChatMsg.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxChatMsg.entity.WxChatMsg;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: 消息描述表
 * @Author: jeecg-boot
 * @Date:   2020-06-11
 * @Version: V1.0
 */
public interface WxChatMsgMapper extends BaseMapper<WxChatMsg> {
	List<WxChatMsg>queryList(Page<WxChatMsg> page, WxChatMsg wxChatMsg);
	public List<WxChatMsg> chatlist(@Param("wxChatMsg")WxChatMsg wxChatMsg);
	public void updateWxChatMsg(@Param("wxChatMsg")WxChatMsg wxChatMsg);

}
