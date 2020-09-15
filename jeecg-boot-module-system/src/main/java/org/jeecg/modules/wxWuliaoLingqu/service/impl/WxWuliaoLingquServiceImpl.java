package org.jeecg.modules.wxWuliaoLingqu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jeecg.modules.wxStory.entity.WxStory;
import org.jeecg.modules.wxStory.mapper.WxStoryMapper;
import org.jeecg.modules.wxWuliaoLingqu.entity.WxWuliaoLingqu;
import org.jeecg.modules.wxWuliaoLingqu.mapper.WxWuliaoLingquMapper;
import org.jeecg.modules.wxWuliaoLingqu.service.IWxWuliaoLingquService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 物料领取
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Service
public class WxWuliaoLingquServiceImpl extends ServiceImpl<WxWuliaoLingquMapper, WxWuliaoLingqu> implements IWxWuliaoLingquService {
	@Resource
	private WxWuliaoLingquMapper WxWuliaoLingquMapper;
	@Override
	public List<Map<String,Object>> queryKucunByBatchNum(HashMap<String,String> param) {
		List<Map<String,Object>> result=WxWuliaoLingquMapper.queryKucunByBatchNum(param);
		return result;
	}
}
