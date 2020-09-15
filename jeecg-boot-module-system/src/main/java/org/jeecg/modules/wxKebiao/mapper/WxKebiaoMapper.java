package org.jeecg.modules.wxKebiao.mapper;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxKebiao.entity.WxKebiao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * @Description: 课表
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
public interface WxKebiaoMapper extends BaseMapper<WxKebiao> {
	   List<WxKebiao> querySysCementListByUserId(Page<WxKebiao> page, String userId,String msgCategory);
	   List<Map<String, Object>> getByMoth(@Param("banjiCode")String banjiCode, String studyId);
	   List<Map<String, Object>> getByDetailMoth(@Param("banjiCode")String banjiCode, @Param("months")String months);
	   List<Map<String, Object>> getListStudy(@Param("banjiCode")String banjiCode,@Param("courseId") String courseId);
}
