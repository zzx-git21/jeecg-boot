package org.jeecg.modules.wxKebiao.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxKebiao.entity.WxKebiao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 课表
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
public interface IWxKebiaoService extends IService<WxKebiao> {

	Page<WxKebiao> querySysCementPageByUserId(Page<WxKebiao> pageList, String string, String string2);

	List<Map<String,Object>> getByMoth(String banjicode,String studyId);
	List<Map<String,Object>> getByDetailMoth(String banjicode,String moths);
	List<Map<String,Object>> getListStudy(String banjicode,String courseId);

}
