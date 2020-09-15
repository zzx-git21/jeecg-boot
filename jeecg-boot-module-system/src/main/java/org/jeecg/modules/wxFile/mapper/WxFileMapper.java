package org.jeecg.modules.wxFile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxFile.entity.WxFile;
import org.jeecg.modules.system.model.TreeSelectModel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 分类字典
 * @Author: jeecg-boot
 * @Date:   2019-05-29
 * @Version: V1.0
 */
public interface WxFileMapper extends BaseMapper<WxFile> {
	
	/**
	  *  根据父级ID查询树节点数据
	 * @param pid
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(@Param("pid")  String pid);
	

}
