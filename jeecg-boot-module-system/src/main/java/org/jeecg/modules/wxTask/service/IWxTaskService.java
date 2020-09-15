package org.jeecg.modules.wxTask.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.system.entity.SysAnnouncement;
import org.jeecg.modules.wxTask.entity.WxTask;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 作业
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
public interface IWxTaskService extends IService<WxTask> {
	public Page<Map<String,Object>> queryWxTaskByBanJiId(Page<Map<String,Object>> page,WxTask wxTask,List<String> idList);
	public Map<String,Object> queryWxTaskByTaskId(WxTask wxTask);

}
