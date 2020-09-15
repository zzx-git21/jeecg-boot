package org.jeecg.modules.wxTask.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxTask.entity.WxTask;
import org.jeecg.modules.wxTask.mapper.WxTaskMapper;
import org.jeecg.modules.wxTask.service.IWxTaskService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 作业
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Service
public class WxTaskServiceImpl extends ServiceImpl<WxTaskMapper, WxTask> implements IWxTaskService {
	@Resource
	private WxTaskMapper wxTaskMapper;
	
	@Override
	public Page<Map<String,Object>> queryWxTaskByBanJiId(Page<Map<String,Object>> page,WxTask wxTask,List<String> idList) {
		List<Map<String,Object>> lt=wxTaskMapper.queryWxTaskByBanJiId(page, wxTask,idList);
		for(Map<String,Object> m:lt){
			String taskId=m.get("id").toString();
		    List<Map<String,Object>> recordList=wxTaskMapper.getRecordList(taskId);
		    m.put("recordInfoList", recordList);
			//wt.setRecordInfoList(recordList);
		}
		return page.setRecords(lt);
	}
	@Override
	public Map<String,Object> queryWxTaskByTaskId(WxTask wxTask) {
		List<Map<String,Object>> lt=wxTaskMapper.queryWxTaskByBanJiId(null, wxTask,null);
		for(Map<String,Object> m:lt){
			String taskId=m.get("id").toString();
		    List<Map<String,Object>> recordList=wxTaskMapper.getRecordList(taskId);
		    m.put("recordInfoList", recordList);
			//wt.setRecordInfoList(recordList);
		}
		return lt.get(0);
	}

}
