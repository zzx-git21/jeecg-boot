package org.jeecg.modules.common.service.impl;


import java.util.List;

import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.common.service.CommonService;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


@Service
public class CommonImpl  implements CommonService {
	
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	 private RedisUtil redisUtil;
	
	  public boolean checkRole(String username,String roleid)  {
		  boolean roleFlag=false;
		  SysUser sysUser = (SysUser)redisUtil.get(CommonConstant.USER_INFO + username);//用户信息
			List<SysUserRole> userRoleList = sysUserRoleService
					.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, sysUser.getId()));// 获取角色信息		
			for (SysUserRole sysUserRole : userRoleList) {
				String roleId=sysUserRole.getRoleId();
				if(roleId.equals(roleid)){
					roleFlag=true;
				}
			}	
		  return roleFlag;
	  }
}
