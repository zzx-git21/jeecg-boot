package org.jeecg.modules.wxTaskTemplate.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxTaskTemplate.entity.WxTaskTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 作业模板
 * @Author: jeecg-boot
 * @Date:   2020-06-05
 * @Version: V1.0
 */
public interface IWxTaskTemplateService extends IService<WxTaskTemplate> {
	public List<Map<String,Object>>  querykebiaoInfo(String kebiaoid);
	public List<Map<String,Object>>  queryTemplateList(String kcName,String kcjbName);

}
