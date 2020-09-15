package org.jeecg.modules.wxPotentialCustomer.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxPotentialCustomer.entity.WxPotentialCustomer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 潜客,潜在客户
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
public interface IWxPotentialCustomerService extends IService<WxPotentialCustomer> {
	public Page<Map<String,Object>>  queryComList(Page<Map<String,Object>> page,String keyword,String type);
	public Page<Map<String,Object>>  queryComListNew(Page<Map<String,Object>> page,Map<String,Object> querymap,String type);
	public Page<Map<String,Object>>  tuifeiList(Page<Map<String,Object>> page,Map<String,Object> querymap);

	public Map<String,Object>  queryStudentCnt(String banjiId);
	public Map<String,Object>  queryXuefeiByBanjiid(String banjiId);
	
	public long queryQiandaoCnt(String studyId);
	public List<Map<String,Object>> queryJiaoFeiById(String id);

}
