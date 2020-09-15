package org.jeecg.modules.wxTutorialData.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxTutorialData.entity.WxTutorialData;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 活动用户辅导视频资料表
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
public interface WxTutorialDataMapper extends BaseMapper<WxTutorialData> {

    List<WxTutorialData> queryList(Page<WxTutorialData> page, @Param("map") Map<String, Object> map);

}
