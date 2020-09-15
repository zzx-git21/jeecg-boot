package org.jeecg.modules.wxStudy.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecg.modules.wxStudy.service.IWxStudyService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 皖新学生管理
 * @Author: jeecg-boot
 * @Date:   2019-10-23
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxStudy/wxStudy")
@Slf4j
public class WxStudyController {
	@Autowired
	private IWxStudyService wxStudyService;
	
	/**
	  * 分页列表查询
	 * @param wxStudy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WxStudy>> queryPageList(WxStudy wxStudy,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxStudy>> result = new Result<IPage<WxStudy>>();
		//QueryWrapper<WxStudy> queryWrapper = QueryGenerator.initQueryWrapper(wxStudy, req.getParameterMap());
		QueryWrapper<WxStudy> queryWrapper =new QueryWrapper<WxStudy>();
		String userName = req.getParameter("userName");
		if(oConvertUtils.isNotEmpty(userName)) {
			queryWrapper.like("user_name", wxStudy.getUserName());
		}
		String studyName = req.getParameter("studyName");
		if(oConvertUtils.isNotEmpty(studyName)) {
			queryWrapper.like("study_name",studyName);
		}
		String banjiId = req.getParameter("banjiId");
		if(oConvertUtils.isNotEmpty(banjiId)) {
			queryWrapper.eq("banji_id",banjiId);
		}
		Page<WxStudy> page = new Page<WxStudy>(pageNo, pageSize);
		IPage<WxStudy> pageList = wxStudyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxStudy
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxStudy> add(@RequestBody WxStudy wxStudy) {
		Result<WxStudy> result = new Result<WxStudy>();
		try {
			wxStudy.setNianji((long) 0);
			wxStudyService.save(wxStudy);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxStudy
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxStudy> edit(@RequestBody WxStudy wxStudy) {
		Result<WxStudy> result = new Result<WxStudy>();
		WxStudy wxStudyEntity = wxStudyService.getById(wxStudy.getId());
		if(wxStudyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxStudyService.updateById(wxStudy);
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
			wxStudyService.removeById(id);
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
	public Result<WxStudy> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxStudy> result = new Result<WxStudy>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxStudyService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<WxStudy> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxStudy> result = new Result<WxStudy>();
		WxStudy wxStudy = wxStudyService.getById(id);
		if(wxStudy==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxStudy);
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
  public ModelAndView exportXls(HttpServletRequest request, WxStudy wxStudy) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxStudy> queryWrapper = QueryGenerator.initQueryWrapper(wxStudy, request.getParameterMap());
      List<WxStudy> pageList = wxStudyService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxStudy> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "皖新学生管理列表");
      mv.addObject(NormalExcelConstants.CLASS, WxStudy.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("皖新学生管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxStudy> listWxStudys = ExcelImportUtil.importExcel(file.getInputStream(), WxStudy.class, params);
              wxStudyService.saveBatch(listWxStudys);
              return Result.ok("文件导入成功！数据行数:" + listWxStudys.size());
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
  

	/**
	  * 通过banjiId查询同班同学  APP
	 * @param id
	 * @return
	 */
  @ApiOperation(value = "查询同班同学列表APP", notes = "author by skycc")
  @GetMapping(value = "/queryBybanjiId")
	public Result<List<WxStudy>> queryBybanjiId(@RequestParam(name="banjiId",required=true) String banjiId) {
		Result<List<WxStudy>> result = new Result<List<WxStudy>>();
		QueryWrapper<WxStudy> queryWrapper =new QueryWrapper<WxStudy>();
		queryWrapper.eq("banji_id",banjiId);
		List<WxStudy> studyList=wxStudyService.list(queryWrapper);
		result.setResult(studyList);
		return result;
	}

  
  @ApiOperation(value = "学生信息提交接口", notes = "author by skycc")
  /**
   * 
   * @param studyId 学生id
   * @param toId 接受信息id
   * @param messgae 消息内容
   * @return
   */
  @GetMapping(value = "/addStudyMsg")
	public Result<List<WxStudy>> addStudyMsg(@RequestParam(name="studyId",required=true) String studyId,@RequestParam(name="toId",required=true) String toId,String messgae) {
		Result<List<WxStudy>> result = new Result<List<WxStudy>>();
		result.success("消息发送成功!");
		return result;
	}



}
