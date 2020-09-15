package org.jeecg.modules.wxTaskTemplate.controller;

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
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxTaskTemplate.entity.WxTaskTemplate;
import org.jeecg.modules.wxTaskTemplate.service.IWxTaskTemplateService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @Description: 作业模板
 * @Author: jeecg-boot
 * @Date:   2020-06-05
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxTaskTemplate/wxTaskTemplate")
@Slf4j
public class WxTaskTemplateController {
	@Autowired
	private IWxTaskTemplateService wxTaskTemplateService;
	
	
	@GetMapping(value = "/querykebiaoInfo") 
	public Result<List<Map<String,Object>>> querykebiaoInfo(@RequestParam(name="kebiaoid" ,required=false) String kebiaoid,
									  HttpServletRequest req) {
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
		List<Map<String,Object>> lm=wxTaskTemplateService.querykebiaoInfo(kebiaoid);
		result.setSuccess(true);
		result.setResult(lm);
		return result;
	}
	
	/**
	  * 分页列表查询
	 * @param wxTaskTemplate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WxTaskTemplate>> queryPageList(WxTaskTemplate wxTaskTemplate,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxTaskTemplate>> result = new Result<IPage<WxTaskTemplate>>();
		QueryWrapper<WxTaskTemplate> queryWrapper = QueryGenerator.initQueryWrapper(wxTaskTemplate, req.getParameterMap());
		Page<WxTaskTemplate> page = new Page<WxTaskTemplate>(pageNo, pageSize);
		IPage<WxTaskTemplate> pageList = wxTaskTemplateService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	
	@GetMapping(value = "/queryWxTaskTemplate")
	public Result<List<Map<String,Object>>> queryWxTaskTemplate(WxTaskTemplate wxTaskTemplate,	 
									  HttpServletRequest req) {
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
		/*QueryWrapper<WxTaskTemplate> queryWrapper = QueryGenerator.initQueryWrapper(wxTaskTemplate, req.getParameterMap());
		List<WxTaskTemplate> taskTemplate = wxTaskTemplateService.list(queryWrapper);*/
		List<Map<String,Object>> lm=wxTaskTemplateService.queryTemplateList(wxTaskTemplate.getKcName(),wxTaskTemplate.getKcjbName());
		if(lm.size()>0){
		result.setSuccess(true);
		result.setResult(lm);
		}else{
			result.error500("该课程未设模板");
		}
		return result;
	}
	
	
	/**
	  *   添加
	 * @param wxTaskTemplate
	 * @return
	 */
	@GetMapping(value = "/addTemplate")
	public Result<WxTaskTemplate> addTemplate( WxTaskTemplate wxTaskTemplate,	 
			  HttpServletRequest req) {
		Result<WxTaskTemplate> result = new Result<WxTaskTemplate>();
		try {
			if(null==wxTaskTemplate.getKcName()||"".equals(wxTaskTemplate.getKcName())){
				return 			result.error500("课程名称为空，生成模板失败");
			}
			if(null==wxTaskTemplate.getKcjbName()||"".equals(wxTaskTemplate.getKcjbName())){
				return 			result.error500("课程级别名称为空，生成模板失败");
			}
			List<Map<String,Object>> lm=wxTaskTemplateService.queryTemplateList(wxTaskTemplate.getKcName(),wxTaskTemplate.getKcjbName());
			if(lm.size()>0){
				wxTaskTemplate.setId(lm.get(0).get("id").toString());
				boolean ok = wxTaskTemplateService.updateById(wxTaskTemplate);
			}else{
		
			wxTaskTemplateService.save(wxTaskTemplate);
			}
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	
	/**
	  *   添加
	 * @param wxTaskTemplate
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxTaskTemplate> add(@RequestBody WxTaskTemplate wxTaskTemplate) {
		Result<WxTaskTemplate> result = new Result<WxTaskTemplate>();
		try {
			wxTaskTemplateService.save(wxTaskTemplate);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxTaskTemplate
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxTaskTemplate> edit(@RequestBody WxTaskTemplate wxTaskTemplate) {
		Result<WxTaskTemplate> result = new Result<WxTaskTemplate>();
		WxTaskTemplate wxTaskTemplateEntity = wxTaskTemplateService.getById(wxTaskTemplate.getId());
		if(wxTaskTemplateEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxTaskTemplateService.updateById(wxTaskTemplate);
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
			wxTaskTemplateService.removeById(id);
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
	public Result<WxTaskTemplate> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxTaskTemplate> result = new Result<WxTaskTemplate>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxTaskTemplateService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxTaskTemplate> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxTaskTemplate> result = new Result<WxTaskTemplate>();
		WxTaskTemplate wxTaskTemplate = wxTaskTemplateService.getById(id);
		if(wxTaskTemplate==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxTaskTemplate);
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
  public ModelAndView exportXls(HttpServletRequest request, WxTaskTemplate wxTaskTemplate) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxTaskTemplate> queryWrapper = QueryGenerator.initQueryWrapper(wxTaskTemplate, request.getParameterMap());
      List<WxTaskTemplate> pageList = wxTaskTemplateService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxTaskTemplate> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "作业模板列表");
      mv.addObject(NormalExcelConstants.CLASS, WxTaskTemplate.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("作业模板列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxTaskTemplate> listWxTaskTemplates = ExcelImportUtil.importExcel(file.getInputStream(), WxTaskTemplate.class, params);
              wxTaskTemplateService.saveBatch(listWxTaskTemplates);
              return Result.ok("文件导入成功！数据行数:" + listWxTaskTemplates.size());
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
