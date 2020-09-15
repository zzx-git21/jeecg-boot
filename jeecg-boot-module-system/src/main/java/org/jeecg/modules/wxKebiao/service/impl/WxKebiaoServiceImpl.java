package org.jeecg.modules.wxKebiao.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jeecg.modules.wxKebiao.entity.WxKebiao;
import org.jeecg.modules.wxKebiao.mapper.WxKebiaoMapper;
import org.jeecg.modules.wxKebiao.service.IWxKebiaoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 课表
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@Service
public class WxKebiaoServiceImpl extends ServiceImpl<WxKebiaoMapper, WxKebiao> implements IWxKebiaoService {
	@Resource
	private WxKebiaoMapper wxKebiwaoMapper;
	@Override
	public Page<WxKebiao> querySysCementPageByUserId(Page<WxKebiao> page, String userId,String msgCategory) {
		// TODO Auto-generated method stub
		  return page.setRecords(wxKebiwaoMapper.querySysCementListByUserId(page, userId, msgCategory));
	}
	@Override
	public List<Map<String, Object>> getByMoth(String banjicode, String studyId) {
		// TODO Auto-generated method stub
		return wxKebiwaoMapper.getByMoth(banjicode, studyId);
	}
	@Override
	public List<Map<String, Object>> getByDetailMoth(String banjicode, String moths) {
		// TODO Auto-generated method stub
		return wxKebiwaoMapper.getByDetailMoth(banjicode, moths);
	}
	@Override
	public List<Map<String, Object>> getListStudy(String banjicode, String courseId) {
		// TODO Auto-generated method stub
		return wxKebiwaoMapper.getListStudy(banjicode, courseId);
	}

}
