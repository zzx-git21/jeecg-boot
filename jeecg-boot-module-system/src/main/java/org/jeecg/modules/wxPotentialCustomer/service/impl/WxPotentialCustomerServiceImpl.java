package org.jeecg.modules.wxPotentialCustomer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jeecg.modules.wxPotentialCustomer.entity.WxPotentialCustomer;
import org.jeecg.modules.wxPotentialCustomer.mapper.WxPotentialCustomerMapper;
import org.jeecg.modules.wxPotentialCustomer.service.IWxPotentialCustomerService;
import org.jeecg.modules.wxStory.mapper.WxStoryMapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 潜客,潜在客户
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
@Service
public class WxPotentialCustomerServiceImpl extends ServiceImpl<WxPotentialCustomerMapper, WxPotentialCustomer> implements IWxPotentialCustomerService {
	@Resource
	private WxPotentialCustomerMapper wxPotentialCustomerMapper;
	//公用处理
	@Override
	public Page<Map<String,Object>>  queryComList(Page<Map<String,Object>> page,String keyword,String type){
			return page.setRecords(wxPotentialCustomerMapper.queryComList(page, keyword,type));
	
	}
	public Page<Map<String,Object>>  queryComListNew(Page<Map<String,Object>> page,Map<String,Object> querymap,String type){
			return page.setRecords(wxPotentialCustomerMapper.queryComListNew(page, querymap,type));
	}
	public Page<Map<String,Object>>  tuifeiList(Page<Map<String,Object>> page,Map<String,Object> querymap){
		return page.setRecords(wxPotentialCustomerMapper.tuifeiList(page, querymap));
}
	
	@Override
	public Map<String,Object>  queryStudentCnt(String banjiId){
		List<Map<String,Object>> lm=wxPotentialCustomerMapper.queryStudentCnt(banjiId);
		long totalNum= (long) lm.get(0).get("totalnum");
		long nownum=(long) lm.get(0).get("nownum");
		long qknum=(long) lm.get(0).get("qknum");
		Map<String,Object> m=new HashMap<String,Object>();
		if(totalNum>0){	
			m.put("totalStudent", totalNum);//总数
			m.put("nowStudent", nownum+qknum);//已报
			m.put("remainingStudent", totalNum-nownum-qknum);//剩余
		}else{
			m.put("totalStudent", "-");//总数
			m.put("nowStudent", nownum+qknum);//已报
			m.put("remainingStudent", "-");//剩余
		}
		return m;
	}
	
	
	@Override
	public Map<String,Object>  queryXuefeiByBanjiid(String banjiId){
		List<Map<String,Object>> lm=wxPotentialCustomerMapper.queryXuefeiByBanjiid(banjiId);
		
		Map<String,Object> m=new HashMap<String,Object>();
		if(lm.size()>0){
			m.put("xuefei", lm.get(0).get("xuefei"));
			m.put("kc", lm.get(0).get("kc"));
			m.put("ks", lm.get(0).get("ks"));
			m.put("courseUnitPrice", lm.get(0).get("courseUnitPrice"));
		}else{
			m.put("xuefei", 0);
		}
		return m;
	}
	
	
	@Override
	public long queryQiandaoCnt(String studyId){
		List<Map<String,Object>> lm=wxPotentialCustomerMapper.queryQiandaoCnt(studyId);
		return  (long) lm.get(0).get("cnt");
	}
	
	@Override
	public List<Map<String,Object>> queryJiaoFeiById(String id){
		return wxPotentialCustomerMapper.queryJiaoFeiById(id);
	}
}
