package org.jeecg.modules.category.controller;

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
import org.jeecg.modules.category.entity.WxCategory;
import org.jeecg.modules.category.service.IWxCategoryService;

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
 * @Description: 资料类别
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/category/wxCategory")
@Slf4j
public class WxCategoryController {
	@Autowired
	private IWxCategoryService wxCategoryService;
	
	/**
	  * 分页列表查询
	 * @param wxCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WxCategory>> queryPageList(WxCategory wxCategory,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxCategory>> result = new Result<IPage<WxCategory>>();
		QueryWrapper<WxCategory> queryWrapper = QueryGenerator.initQueryWrapper(wxCategory, req.getParameterMap());
		Page<WxCategory> page = new Page<WxCategory>(pageNo, pageSize);
		IPage<WxCategory> pageList = wxCategoryService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxCategory
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxCategory> add(@RequestBody WxCategory wxCategory) {
		Result<WxCategory> result = new Result<WxCategory>();
		try {
			wxCategoryService.save(wxCategory);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxCategory
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxCategory> edit(@RequestBody WxCategory wxCategory) {
		Result<WxCategory> result = new Result<WxCategory>();
		WxCategory wxCategoryEntity = wxCategoryService.getById(wxCategory.getId());
		if(wxCategoryEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxCategoryService.updateById(wxCategory);
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
			wxCategoryService.removeById(id);
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
	public Result<WxCategory> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxCategory> result = new Result<WxCategory>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxCategoryService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxCategory> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxCategory> result = new Result<WxCategory>();
		WxCategory wxCategory = wxCategoryService.getById(id);
		if(wxCategory==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxCategory);
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
  public ModelAndView exportXls(HttpServletRequest request, WxCategory wxCategory) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxCategory> queryWrapper = QueryGenerator.initQueryWrapper(wxCategory, request.getParameterMap());
      List<WxCategory> pageList = wxCategoryService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxCategory> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "资料类别列表");
      mv.addObject(NormalExcelConstants.CLASS, WxCategory.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("资料类别列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxCategory> listWxCategorys = ExcelImportUtil.importExcel(file.getInputStream(), WxCategory.class, params);
              wxCategoryService.saveBatch(listWxCategorys);
              return Result.ok("文件导入成功！数据行数:" + listWxCategorys.size());
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
