package org.jeecg.modules.wxTaskTemplate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jeecg.modules.wxPotentialCustomer.mapper.WxPotentialCustomerMapper;
import org.jeecg.modules.wxTaskTemplate.entity.WxTaskTemplate;
import org.jeecg.modules.wxTaskTemplate.mapper.WxTaskTemplateMapper;
import org.jeecg.modules.wxTaskTemplate.service.IWxTaskTemplateService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 作业模板
 * @Author: jeecg-boot
 * @Date:   2020-06-05
 * @Version: V1.0
 */
@Service
public class WxTaskTemplateServiceImpl extends ServiceImpl<WxTaskTemplateMapper, WxTaskTemplate> implements IWxTaskTemplateService {
	@Resource
	private WxTaskTemplateMapper wxTaskTemplateMapper;
	@Override
	public List<Map<String,Object>>   querykebiaoInfo(String kebiaoid){
		return wxTaskTemplateMapper.querykebiaoInfo(kebiaoid);		
	}
	@Override
	public List<Map<String,Object>>   queryTemplateList(String kcName,String kcjbName){
		return wxTaskTemplateMapper.queryTemplateList(kcName,kcjbName);		
	}
}
