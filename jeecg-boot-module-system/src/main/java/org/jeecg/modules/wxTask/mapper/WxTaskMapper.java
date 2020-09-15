package org.jeecg.modules.wxTask.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxTask.entity.WxTask;
 
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: 作业
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
public interface WxTaskMapper extends BaseMapper<WxTask> {
	List<Map<String,Object>> queryWxTaskByBanJiId(Page<Map<String,Object>> page,@Param("wxTask")WxTask wxTask,@Param("idList")List<String> idList);
    List<Map<String,Object>> getRecordList(@Param("taskId") String taskId);
}
