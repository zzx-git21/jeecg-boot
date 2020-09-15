package org.jeecg.modules.wxTask.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.service.ISysUserRoleService;
import org.jeecg.modules.wxBanji.entity.WxBanji;
import org.jeecg.modules.wxBanji.service.IWxBanjiService;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecg.modules.wxStudy.service.IWxStudyService;
import org.jeecg.modules.wxTask.entity.WxTask;
import org.jeecg.modules.wxTask.service.IWxTaskService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 作业
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxTask/wxTask")
@Slf4j
@Api(tags = "首页作业接口")
public class WxTaskController {
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private IWxStudyService wxStudyService;
	@Autowired
	private IWxBanjiService wxBanjiService;
	@Autowired
	private IWxTaskService wxTaskService;
	 @Autowired
	 private RedisUtil redisUtil;
	/**
	  * 分页列表查询
	 * @param wxTask
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@ApiOperation(value = "查询 作业列表")
	public Result<IPage<WxTask>> queryPageList(WxTask wxTask,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxTask>> result = new Result<IPage<WxTask>>();
		QueryWrapper<WxTask> queryWrapper = new QueryWrapper<WxTask> ();//QueryGenerator.initQueryWrapper(wxTask, req.getParameterMap());
		
		
		String homeWork = req.getParameter("homeWork");
		if(oConvertUtils.isNotEmpty(homeWork)) {
			queryWrapper.like("home_work", wxTask.getHomeWork());
		}
		String kebiaoId = req.getParameter("kebiaoId");
		if(oConvertUtils.isNotEmpty(kebiaoId)) {
			queryWrapper.eq("kebiao_id", wxTask.getKebiaoId());
		}
		Page<WxTask> page = new Page<WxTask>(pageNo, pageSize);
		IPage<WxTask> pageList = wxTaskService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@GetMapping(value = "/queryList") 
	@ApiOperation(value = "查询 作业列表APP")
	public Result<IPage<Map<String,Object>>> queryTaskList(WxTask wxTask,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="100") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
		//String banjiId=getBanjiId(req);
	//	QueryWrapper<WxBanji> queryWrapper1 = new QueryWrapper<>();
		List<WxBanji> banjiList =getUserBanjiInfoByReq(req);
		//String[] idList= new String[]();
		//ArrayList al=new ArrayList()
		List<String> ids = new ArrayList<String>();
       
		//String banjiId="''";//只设置个值，若无班级位空
		for(WxBanji banji :banjiList){
			 ids.add(banji.getId());
			//banjiId+=",'"+banji.getId()+"'";
			// queryWrapper1.lambda().or(obj -> obj.eq(WxBanji::getId, banji.getId()));
		}
		
		Page<Map<String,Object>> pageList =wxTaskService.queryWxTaskByBanJiId(page, wxTask, ids);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	public List<WxBanji> getUserBanjiInfoByReq( HttpServletRequest req){
		List<WxBanji> banjiList=new ArrayList<WxBanji>();
		String username = JwtUtil.getUserNameByToken(req);
		SysUser sysUser = (SysUser)redisUtil.get(CommonConstant.USER_INFO + username);//用户信息
		List<SysUserRole> userRoleList = sysUserRoleService
				.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, sysUser.getId()));// 获取角色信息		
		for (SysUserRole sysUserRole : userRoleList) {
			String roleId=sysUserRole.getRoleId();
			if(roleId.equals(CommonConstant.ROLE_STUDENT)){//学生
				WxStudy study = wxStudyService.getOne(new QueryWrapper<WxStudy>().lambda().eq(WxStudy::getUserId, sysUser.getId()));
				if(study!=null&&study.getBanjiId()!=null&&!("").equals(study.getBanjiId())){
				WxBanji wxBanjiStudent = wxBanjiService.getById(study.getBanjiId());
				if(wxBanjiStudent!=null){
					banjiList.add(wxBanjiStudent);
				}
				}
			}else if(roleId.equals(CommonConstant.ROLE_TEACHER)){//教师
				List<WxBanji> banjiListForTeacher = wxBanjiService.list(new QueryWrapper<WxBanji>().lambda().eq(WxBanji::getTeacher, sysUser.getId()));
				if(banjiListForTeacher.size()>0){
					banjiList.addAll(banjiListForTeacher);
				}
			}else if(roleId.equals(CommonConstant.ROLE_BZR)){//班主任
				List<WxBanji> banjiListForBZR = wxBanjiService.list(new QueryWrapper<WxBanji>().lambda().eq(WxBanji::getBzr, sysUser.getId()));
				if(banjiListForBZR.size()>0){
					banjiList.addAll(banjiListForBZR);
				}
			}
		}	
		return banjiList;
	}
	//获取班级Id
	public String getBanjiId(HttpServletRequest req){
		String gradeId="";
		String username = JwtUtil.getUserNameByToken(req);
		WxStudy study = (WxStudy)redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
		if(study!=null){
			gradeId=study.getBanjiId();
		}
		return gradeId;
	}
	
	/**
	  *   添加
	 * @param wxTask
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxTask> add(@RequestBody WxTask wxTask) {
		Result<WxTask> result = new Result<WxTask>();
		try {
			wxTaskService.save(wxTask);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxTask
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxTask> edit(@RequestBody WxTask wxTask) {
		Result<WxTask> result = new Result<WxTask>();
		WxTask wxTaskEntity = wxTaskService.getById(wxTask.getId());
		if(wxTaskEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxTaskService.updateById(wxTask);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxTaskService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxTask> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxTask> result = new Result<WxTask>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxTaskService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "通过id查询作业")
	@GetMapping(value = "/queryById")
	public Result<Map<String,Object>> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Map<String,Object>> result = new Result<Map<String,Object>>();
		//WxTask wxTask = wxTaskService.getById(id);
		WxTask wt = new WxTask();
		wt.setId(id);
		Map<String,Object> wxTask = wxTaskService.queryWxTaskByTaskId(wt);
		if(wxTask==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxTask);
			result.setSuccess(true);
		}
		return result;
	}
	@ApiOperation(value = "查询 课表作业")
	@GetMapping(value = "/queryBykebiaoId")
	public Result<WxTask> queryBykebiaoId(WxTask dto,HttpServletRequest req) {
		Result<WxTask> result = new Result<WxTask>();
		QueryWrapper<WxTask> queryWrapper = QueryGenerator.initQueryWrapper(dto, req.getParameterMap());
		WxTask wxTask = wxTaskService.getOne(queryWrapper);
		if(wxTask==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxTask);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, WxTask wxTask) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxTask> queryWrapper = QueryGenerator.initQueryWrapper(wxTask, request.getParameterMap());
      List<WxTask> pageList = wxTaskService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxTask> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "作业列表");
      mv.addObject(NormalExcelConstants.CLASS, WxTask.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("作业列表数据", "导出人:Jeecg", "导出信息"));
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<WxTask> listWxTasks = ExcelImportUtil.importExcel(file.getInputStream(), WxTask.class, params);
              wxTaskService.saveBatch(listWxTasks);
              return Result.ok("文件导入成功！数据行数:" + listWxTasks.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
