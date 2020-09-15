package org.jeecg.modules.wxFile.service;

import java.util.List;

import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.wxFile.entity.WxFile;
import org.jeecg.modules.system.model.TreeSelectModel;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 分类字典
 * @Author: jeecg-boot
 * @Date:   2019-05-29
 * @Version: V1.0
 */
public interface IWxFileService extends IService<WxFile> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";

	void addWxFile(WxFile sysFile);
	
	void updateWxFile(WxFile sysFile);
	
	/**
	  * 根据父级编码加载分类字典的数据
	 * @param pcode
	 * @return
	 */
	public List<TreeSelectModel> queryListByCode(String pcode) throws JeecgBootException;
	
	/**
	  * 根据pid查询子节点集合
	 * @param pid
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(String pid);
	
}
