package org.jeecg.modules.wxStory.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxStory.entity.WxStory;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: 故事大会资料表
 * @Author: jeecg-boot
 * @Date:   2019-10-20 
 * @Version: V1.0
 */
public interface WxStoryMapper extends BaseMapper<WxStory> {
	List<WxStory> queryStoreyList(Page<WxStory> page,@Param("wxStory")WxStory wxStory,@Param("gradeId")String gradeId);
	List<WxStory> queryList(Page<WxStory> page,@Param("wxStory")WxStory wxStory,@Param("gradeId")String gradeId);
	List<WxStory> queryCateGroyList(Page<WxStory> page,@Param("wxStory")WxStory wxStory,@Param("gradeId")String gradeId);

	List<Map<String,Object>> queryHaveTuijianStoryList(Page<Map<String,Object>> page,@Param("wxStory")WxStory wxStory,@Param("gradeId")String gradeId);
	List<Map<String,Object>> queryNeedTuijianStoryList(Page<Map<String,Object>> page,@Param("wxStory")WxStory wxStory,@Param("gradeId")String gradeId);

}
