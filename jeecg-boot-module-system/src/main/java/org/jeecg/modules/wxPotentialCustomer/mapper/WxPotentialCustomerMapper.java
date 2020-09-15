package org.jeecg.modules.wxPotentialCustomer.mapper;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxPotentialCustomer.entity.WxPotentialCustomer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * @Description: 潜客,潜在客户
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
public interface WxPotentialCustomerMapper extends BaseMapper<WxPotentialCustomer> {
	public List<Map<String,Object>> queryComList(Page<Map<String,Object>> page,@Param("keyword")String keyword,@Param("type")String type);
	public List<Map<String,Object>> queryComListNew(Page<Map<String,Object>> page,@Param("querymap")Map<String,Object> querymap,@Param("type")String type);
	public List<Map<String,Object>> queryStudentCnt(@Param("banjiId")String banjiId);
	public List<Map<String,Object>> queryXuefeiByBanjiid(@Param("banjiId")String banjiId);
	public List<Map<String,Object>> tuifeiList(Page<Map<String,Object>> page,@Param("querymap")Map<String,Object> querymap);
	public List<Map<String,Object>> queryQiandaoCnt(@Param("studyId")String studyId);
	public List<Map<String,Object>> queryJiaoFeiById(@Param("qiankeId")String id);

}
