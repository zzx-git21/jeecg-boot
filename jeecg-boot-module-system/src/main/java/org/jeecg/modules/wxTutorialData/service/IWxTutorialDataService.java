package org.jeecg.modules.wxTutorialData.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.wxBook.entity.WxBook;
import org.jeecg.modules.wxTutorialData.entity.WxTutorialData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 活动用户辅导视频资料表
 * @Author: jeecg-boot
 * @Date:   2019-12-20
 * @Version: V1.0
 */
public interface IWxTutorialDataService extends IService<WxTutorialData> {

    public Page<WxTutorialData> queryList(Page<WxTutorialData> page, Long gradeId, String dataName, Integer recommend);

}
