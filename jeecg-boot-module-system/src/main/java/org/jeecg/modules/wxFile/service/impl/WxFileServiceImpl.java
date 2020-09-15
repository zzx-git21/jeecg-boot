package org.jeecg.modules.wxFile.service.impl;

import java.util.List;

import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxFile.entity.WxFile;
import org.jeecg.modules.wxFile.mapper.WxFileMapper;
import org.jeecg.modules.system.model.TreeSelectModel;
import org.jeecg.modules.wxFile.service.IWxFileService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 分类字典
 * @Author: jeecg-boot
 * @Date:   2019-05-29
 * @Version: V1.0
 */
@Service
public class WxFileServiceImpl extends ServiceImpl<WxFileMapper, WxFile> implements IWxFileService {

	@Override
	public void addWxFile(WxFile wxFile) {
		if(oConvertUtils.isEmpty(wxFile.getPid())){
			wxFile.setPid(IWxFileService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChild 为1
			WxFile parent = baseMapper.selectById(wxFile.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(wxFile);
	}
	
	@Override
	public void updateWxFile(WxFile wxFile) {
		if(oConvertUtils.isEmpty(wxFile.getPid())){
			wxFile.setPid(IWxFileService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChild 为1
			WxFile parent = baseMapper.selectById(wxFile.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.updateById(wxFile);
	}

	@Override
	public List<TreeSelectModel> queryListByCode(String pcode) throws JeecgBootException{
		String pid = ROOT_PID_VALUE;
		if(oConvertUtils.isNotEmpty(pcode)) {
			List<WxFile> list = baseMapper.selectList(new LambdaQueryWrapper<WxFile>().eq(WxFile::getCode, pcode));
			if(list==null || list.size() ==0) {
				throw new JeecgBootException("该编码【"+pcode+"】不存在，请核实!");
			}
			if(list.size()>1) {
				throw new JeecgBootException("该编码【"+pcode+"】存在多个，请核实!");
			}
			pid = list.get(0).getId();
		}
		return baseMapper.queryListByPid(pid);
	}

	@Override
	public List<TreeSelectModel> queryListByPid(String pid) {
		if(oConvertUtils.isEmpty(pid)) {
			pid = ROOT_PID_VALUE;
		}
		return baseMapper.queryListByPid(pid);
	}

}
