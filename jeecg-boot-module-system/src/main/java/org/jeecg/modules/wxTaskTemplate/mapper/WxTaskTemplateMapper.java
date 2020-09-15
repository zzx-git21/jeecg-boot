package org.jeecg.modules.wxTaskTemplate.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxTaskTemplate.entity.WxTaskTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 作业模板
 * @Author: jeecg-boot
 * @Date:   2020-06-05
 * @Version: V1.0
 */
public interface WxTaskTemplateMapper extends BaseMapper<WxTaskTemplate> {
	public List<Map<String,Object>> querykebiaoInfo(@Param("kebiaoid")String kebiaoid);

	public List<Map<String,Object>> queryTemplateList(@Param("kcName")String kcName,@Param("kcjbName")String kcjbName);

}
