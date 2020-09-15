package org.jeecg.modules.wxWuliaoLingqu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxStory.entity.WxStory;
import org.jeecg.modules.wxWuliaoLingqu.entity.WxWuliaoLingqu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 物料领取
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
public interface IWxWuliaoLingquService extends IService<WxWuliaoLingqu> {
	public List<Map<String,Object>> queryKucunByBatchNum(HashMap<String,String> param);
}
