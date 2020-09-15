package org.jeecg.modules.wxTaskFileRel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysRolePermission;
import org.jeecg.modules.wxTaskFileRel.entity.WxTaskFileRel;
import org.jeecg.modules.wxTaskFileRel.service.IWxTaskFileRelService;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 作业文件关联
 * @Author: jeecg-boot
 * @Date:   2019-12-21
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/wxTaskFileRel/wxTaskFileRel")
public class WxTaskFileRelController {
	@Autowired
	private IWxTaskFileRelService wxTaskFileRelService;
	
	/**
	  * 分页列表查询
	 * @param wxTaskFileRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "作业文件关联-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxTaskFileRel>> queryPageList(WxTaskFileRel wxTaskFileRel,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxTaskFileRel>> result = new Result<IPage<WxTaskFileRel>>();
		QueryWrapper<WxTaskFileRel> queryWrapper = QueryGenerator.initQueryWrapper(wxTaskFileRel, req.getParameterMap());
		Page<WxTaskFileRel> page = new Page<WxTaskFileRel>(pageNo, pageSize);
		IPage<WxTaskFileRel> pageList = wxTaskFileRelService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxTaskFileRel
	 * @return
	 */
	@AutoLog(value = "作业文件关联-添加")
	@PostMapping(value = "/add")
	public Result<WxTaskFileRel> add(@RequestBody WxTaskFileRel wxTaskFileRel) {
		Result<WxTaskFileRel> result = new Result<WxTaskFileRel>();
		try {
			wxTaskFileRelService.save(wxTaskFileRel);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	@RequestMapping(value = "/queryFileByTaskId", method = RequestMethod.GET)
	public Result<List<String>> queryFileByTaskId(@RequestParam(name = "taskId", required = true) String taskId) {
		Result<List<String>> result = new Result<>();
		try {
			List<WxTaskFileRel> list = wxTaskFileRelService.list(new QueryWrapper<WxTaskFileRel>().lambda().eq(WxTaskFileRel::getTaskId, taskId));
			result.setResult(list.stream().map(WxTaskFileRel -> String.valueOf(WxTaskFileRel.getFileId())).collect(Collectors.toList()));
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	/**
	  *   添加s
	 * @param wxTaskFileRel
	 * @return
	 */
	@RequestMapping(value = "/saveTaskFileRel", method = RequestMethod.POST)
	public Result<String> saveTaskFileRel(@RequestBody JSONObject json) {
		long start = System.currentTimeMillis();
		Result<String> result = new Result<>();
		try {
			String roleId = json.getString("roleId");
			String permissionIds = json.getString("permissionIds");
			String lastPermissionIds = json.getString("lastpermissionIds");
			LambdaQueryWrapper<WxTaskFileRel> query = new QueryWrapper<WxTaskFileRel>().lambda().eq(WxTaskFileRel::getTaskId, roleId);
			wxTaskFileRelService.remove(query);
			
			List<WxTaskFileRel> list = new ArrayList<WxTaskFileRel>();
	        String[] arr = permissionIds.split(",");
			for (String p : arr) {
				if(oConvertUtils.isNotEmpty(p)) {
					WxTaskFileRel rolepms = new WxTaskFileRel(roleId, p);
					list.add(rolepms);
				}
			}
			wxTaskFileRelService.saveBatch(list);
			//this.sysRolePermissionService.saveRolePermission(roleId, permissionIds, lastPermissionIds);
			result.success("保存成功！");
		} catch (Exception e) {
			result.error500("授权失败！");
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxTaskFileRel
	 * @return
	 */
	@AutoLog(value = "作业文件关联-编辑")
	@PutMapping(value = "/edit")
	public Result<WxTaskFileRel> edit(@RequestBody WxTaskFileRel wxTaskFileRel) {
		Result<WxTaskFileRel> result = new Result<WxTaskFileRel>();
		WxTaskFileRel wxTaskFileRelEntity = wxTaskFileRelService.getById(wxTaskFileRel.getId());
		if(wxTaskFileRelEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxTaskFileRelService.updateById(wxTaskFileRel);
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
	@AutoLog(value = "作业文件关联-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxTaskFileRelService.removeById(id);
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
	@AutoLog(value = "作业文件关联-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxTaskFileRel> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxTaskFileRel> result = new Result<WxTaskFileRel>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxTaskFileRelService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "作业文件关联-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxTaskFileRel> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxTaskFileRel> result = new Result<WxTaskFileRel>();
		WxTaskFileRel wxTaskFileRel = wxTaskFileRelService.getById(id);
		if(wxTaskFileRel==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxTaskFileRel);
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
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<WxTaskFileRel> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxTaskFileRel wxTaskFileRel = JSON.parseObject(deString, WxTaskFileRel.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxTaskFileRel, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxTaskFileRel> pageList = wxTaskFileRelService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "作业文件关联列表");
      mv.addObject(NormalExcelConstants.CLASS, WxTaskFileRel.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("作业文件关联列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
              List<WxTaskFileRel> listWxTaskFileRels = ExcelImportUtil.importExcel(file.getInputStream(), WxTaskFileRel.class, params);
              wxTaskFileRelService.saveBatch(listWxTaskFileRels);
              return Result.ok("文件导入成功！数据行数:" + listWxTaskFileRels.size());
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
