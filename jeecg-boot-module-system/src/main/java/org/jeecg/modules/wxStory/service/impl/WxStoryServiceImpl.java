package org.jeecg.modules.wxStory.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jeecg.modules.wxStory.entity.WxStory;
import org.jeecg.modules.wxStory.mapper.WxStoryMapper;
import org.jeecg.modules.wxStory.service.IWxStoryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 故事大会资料表 
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Service
public class WxStoryServiceImpl extends ServiceImpl<WxStoryMapper, WxStory> implements IWxStoryService {

	@Resource
	private WxStoryMapper wxStoryMapper;
	@Override
	public Page<WxStory> queryList(Page<WxStory> page,WxStory wxStory,String gradeId) {

		List<WxStory> storyList=wxStoryMapper.queryList(page, wxStory,gradeId);
		/*for(WxStory ws:storyList){
			if(ws.getImgUrl()!=null){
				String img=ws.getImgUrl();
				ws.setImgUrl(CommonConstant.SHOW_IMG+img );
			}
			
		}*/
		return page.setRecords(storyList);
	}
	
	@Override
	public Page<Map<String,Object>> queryHaveTuijianStoryList(Page<Map<String,Object>> page,WxStory wxStory,String gradeId) {
		List<Map<String,Object>> storyList=wxStoryMapper.queryHaveTuijianStoryList(page, wxStory,gradeId);
		return page.setRecords(storyList);
	}
	@Override
	public Page<Map<String,Object>> queryNeedTuijianStoryList(Page<Map<String,Object>> page,WxStory wxStory,String gradeId) {
		List<Map<String,Object>> storyList=wxStoryMapper.queryNeedTuijianStoryList(page, wxStory,gradeId);
		return page.setRecords(storyList);
	}

	@Override
	public Page<WxStory> queryStoreyList(Page<WxStory> page, WxStory wxStory, String gradeId) {
		// TODO Auto-generated method stub

		List<WxStory> storyList=wxStoryMapper.queryStoreyList(page, wxStory,gradeId);
		/*for(WxStory ws:storyList){
			if(ws.getImgUrl()!=null){
				String img=ws.getImgUrl();
				ws.setImgUrl(CommonConstant.SHOW_IMG+img );
			}
			
		}*/
		return page.setRecords(storyList);
	}

	@Override
	public Page<WxStory> queryCateGroyList(Page<WxStory> page, WxStory wxStory, String gradeId) {
		// TODO Auto-generated method stub
		List<WxStory> storyList=wxStoryMapper.queryCateGroyList(page, wxStory,gradeId);
		return page.setRecords(storyList);
	}
}
