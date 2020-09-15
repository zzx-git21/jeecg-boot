package org.jeecg.modules.appicon.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.appicon.entity.WxAppicon;
import org.jeecg.modules.appicon.service.IWxAppiconService;
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
 * @Description: appicon管理
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/appicon/wxAppicon")
@Slf4j
@Api(tags = "首页icon接口")
public class WxAppiconController {
	@Autowired
	private IWxAppiconService wxAppiconService;
	
	/**
	  * 分页列表查询
	 * @param wxAppicon
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@ApiOperation(value = "查询首页icon APP")
	public Result<IPage<WxAppicon>> queryPageList(WxAppicon wxAppicon,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxAppicon>> result = new Result<IPage<WxAppicon>>();
		QueryWrapper<WxAppicon> queryWrapper = QueryGenerator.initQueryWrapper(wxAppicon, req.getParameterMap());
		Page<WxAppicon> page = new Page<WxAppicon>(pageNo, pageSize);
		IPage<WxAppicon> pageList = wxAppiconService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxAppicon
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxAppicon> add(@RequestBody WxAppicon wxAppicon) {
		Result<WxAppicon> result = new Result<WxAppicon>();
		try {
			wxAppiconService.save(wxAppicon);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxAppicon
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxAppicon> edit(@RequestBody WxAppicon wxAppicon) {
		Result<WxAppicon> result = new Result<WxAppicon>();
		WxAppicon wxAppiconEntity = wxAppiconService.getById(wxAppicon.getId());
		if(wxAppiconEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxAppiconService.updateById(wxAppicon);
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
			wxAppiconService.removeById(id);
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
	public Result<WxAppicon> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxAppicon> result = new Result<WxAppicon>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxAppiconService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxAppicon> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxAppicon> result = new Result<WxAppicon>();
		WxAppicon wxAppicon = wxAppiconService.getById(id);
		if(wxAppicon==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxAppicon);
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
  public ModelAndView exportXls(HttpServletRequest request, WxAppicon wxAppicon) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxAppicon> queryWrapper = QueryGenerator.initQueryWrapper(wxAppicon, request.getParameterMap());
      List<WxAppicon> pageList = wxAppiconService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxAppicon> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "appicon管理列表");
      mv.addObject(NormalExcelConstants.CLASS, WxAppicon.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("appicon管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxAppicon> listWxAppicons = ExcelImportUtil.importExcel(file.getInputStream(), WxAppicon.class, params);
              wxAppiconService.saveBatch(listWxAppicons);
              return Result.ok("文件导入成功！数据行数:" + listWxAppicons.size());
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
