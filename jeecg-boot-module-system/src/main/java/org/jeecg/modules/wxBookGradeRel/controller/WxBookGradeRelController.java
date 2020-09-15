package org.jeecg.modules.wxBookGradeRel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxBookGradeRel.entity.WxBookGradeRel;
import org.jeecg.modules.wxBookGradeRel.service.IWxBookGradeRelService;
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 图书和年级关系
 * @Author: jeecg-boot
 * @Date:   2019-12-15
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/wxBookGradeRel/wxBookGradeRel")
public class WxBookGradeRelController {
	@Autowired
	private IWxBookGradeRelService wxBookGradeRelService;
	
	/**
	  * 分页列表查询
	 * @param wxBookGradeRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "图书和年级关系-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxBookGradeRel>> queryPageList(WxBookGradeRel wxBookGradeRel,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxBookGradeRel>> result = new Result<IPage<WxBookGradeRel>>();
		QueryWrapper<WxBookGradeRel> queryWrapper = QueryGenerator.initQueryWrapper(wxBookGradeRel, req.getParameterMap());
		Page<WxBookGradeRel> page = new Page<WxBookGradeRel>(pageNo, pageSize);
		IPage<WxBookGradeRel> pageList = wxBookGradeRelService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result; 
	}
	
	/**
	  *   添加
	 * @param wxBookGradeRel
	 * @return
	 */
	@AutoLog(value = "图书和年级关系-添加")
	@PostMapping(value = "/add")
	public Result<WxBookGradeRel> add(@RequestBody WxBookGradeRel wxBookGradeRel) {
		Result<WxBookGradeRel> result = new Result<WxBookGradeRel>();
		try {
			wxBookGradeRelService.save(wxBookGradeRel);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxBookGradeRel
	 * @return
	 */
	@AutoLog(value = "图书和年级关系-编辑")
	@PutMapping(value = "/edit")
	public Result<WxBookGradeRel> edit(@RequestBody WxBookGradeRel wxBookGradeRel) {
		Result<WxBookGradeRel> result = new Result<WxBookGradeRel>();
		WxBookGradeRel wxBookGradeRelEntity = wxBookGradeRelService.getById(wxBookGradeRel.getId());
		if(wxBookGradeRelEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxBookGradeRelService.updateById(wxBookGradeRel);
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
	@AutoLog(value = "图书和年级关系-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxBookGradeRelService.removeById(id);
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
	@AutoLog(value = "图书和年级关系-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxBookGradeRel> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxBookGradeRel> result = new Result<WxBookGradeRel>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxBookGradeRelService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "图书和年级关系-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxBookGradeRel> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxBookGradeRel> result = new Result<WxBookGradeRel>();
		WxBookGradeRel wxBookGradeRel = wxBookGradeRelService.getById(id);
		if(wxBookGradeRel==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxBookGradeRel);
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
      QueryWrapper<WxBookGradeRel> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxBookGradeRel wxBookGradeRel = JSON.parseObject(deString, WxBookGradeRel.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxBookGradeRel, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxBookGradeRel> pageList = wxBookGradeRelService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "图书和年级关系列表");
      mv.addObject(NormalExcelConstants.CLASS, WxBookGradeRel.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("图书和年级关系列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxBookGradeRel> listWxBookGradeRels = ExcelImportUtil.importExcel(file.getInputStream(), WxBookGradeRel.class, params);
              wxBookGradeRelService.saveBatch(listWxBookGradeRels);
              return Result.ok("文件导入成功！数据行数:" + listWxBookGradeRels.size());
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
