package org.jeecg.modules.wxTutorialData.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.wxTutorialData.entity.WxTutorialData;
import org.jeecg.modules.wxTutorialData.mapper.WxTutorialDataMapper;
import org.jeecg.modules.wxTutorialData.service.IWxTutorialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 活动用户辅导视频资料表
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
@Service
public class WxTutorialDataServiceImpl extends ServiceImpl<WxTutorialDataMapper, WxTutorialData> implements IWxTutorialDataService {

    @Resource
    private WxTutorialDataMapper dataMapper;

    @Override
    public Page<WxTutorialData> queryList(Page<WxTutorialData> page, Long gradeId, String dataName, Integer recommend) {
        Map<String, Object> map = new HashMap<>();
        map.put("gradeId", gradeId);
        map.put("dataName", dataName);
        map.put("recommoend", recommend);
        return page.setRecords(dataMapper.queryList(page, map));
    }
}
