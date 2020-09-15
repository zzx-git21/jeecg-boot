package org.jeecg.modules.wxStory.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

import org.jeecg.modules.wxStory.entity.WxStory;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 故事大会资料表
 * @Author: jeecg-boot
 * @Date:   2019-10-20 
 * @Version: V1.0
 */
public interface IWxStoryService extends IService<WxStory> {
	public Page<WxStory> queryStoreyList(Page<WxStory> page,WxStory wxStory,String gradeId);

	public Page<WxStory> queryList(Page<WxStory> page,WxStory wxStory,String gradeId);
	public Page<Map<String,Object>> queryHaveTuijianStoryList(Page<Map<String,Object>> page,WxStory wxStory,String gradeId);
	public Page<Map<String,Object>> queryNeedTuijianStoryList(Page<Map<String,Object>> page,WxStory wxStory,String gradeId);
	public Page<WxStory> queryCateGroyList(Page<WxStory> page,WxStory wxStory,String gradeId);

	
}
