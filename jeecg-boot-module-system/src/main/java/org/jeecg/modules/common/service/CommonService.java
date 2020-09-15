package org.jeecg.modules.common.service;


import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxStudy.entity.WxStudy;

import com.baomidou.mybatisplus.extension.service.IService;


public interface CommonService {
	public boolean checkRole(String username,String roleid);
}
