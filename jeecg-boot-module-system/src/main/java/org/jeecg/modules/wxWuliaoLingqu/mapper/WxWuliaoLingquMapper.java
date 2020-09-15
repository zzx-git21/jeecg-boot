package org.jeecg.modules.wxWuliaoLingqu.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxStory.entity.WxStory;
import org.jeecg.modules.wxWuliaoLingqu.entity.WxWuliaoLingqu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: 物料领取
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
public interface WxWuliaoLingquMapper extends BaseMapper<WxWuliaoLingqu> {
	List<Map<String,Object>> queryKucunByBatchNum(@Param("param")HashMap<String,String> param);

}
