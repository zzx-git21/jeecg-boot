package org.jeecg.modules.wxBanner.controller;

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
import org.jeecg.modules.wxBanner.entity.WxBanner;
import org.jeecg.modules.wxBanner.service.IWxBannerService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
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
 * @Description: banner管理
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxBanner/wxBanner")
@Slf4j
@Api(tags = "首页banner接口")
public class WxBannerController {
	@Autowired
	private IWxBannerService wxBannerService;
	
	/**
	  * 分页列表查询
	 * @param wxBanner
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@ApiOperation(value = "查询 banner列表 APP")
	public Result<IPage<WxBanner>> queryPageList(WxBanner wxBanner,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxBanner>> result = new Result<IPage<WxBanner>>();
		QueryWrapper<WxBanner> queryWrapper = QueryGenerator.initQueryWrapper(wxBanner, req.getParameterMap());
		Page<WxBanner> page = new Page<WxBanner>(pageNo, pageSize);
		IPage<WxBanner> pageList = wxBannerService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxBanner
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxBanner> add(@RequestBody WxBanner wxBanner) {
		Result<WxBanner> result = new Result<WxBanner>();
		try {
			wxBannerService.save(wxBanner);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxBanner
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxBanner> edit(@RequestBody WxBanner wxBanner) {
		Result<WxBanner> result = new Result<WxBanner>();
		WxBanner wxBannerEntity = wxBannerService.getById(wxBanner.getId());
		if(wxBannerEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxBannerService.updateById(wxBanner);
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
			wxBannerService.removeById(id);
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
	public Result<WxBanner> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxBanner> result = new Result<WxBanner>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxBannerService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxBanner> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxBanner> result = new Result<WxBanner>();
		WxBanner wxBanner = wxBannerService.getById(id);
		if(wxBanner==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxBanner);
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
  public ModelAndView exportXls(HttpServletRequest request, WxBanner wxBanner) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxBanner> queryWrapper = QueryGenerator.initQueryWrapper(wxBanner, request.getParameterMap());
      List<WxBanner> pageList = wxBannerService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxBanner> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "banner管理列表");
      mv.addObject(NormalExcelConstants.CLASS, WxBanner.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("banner管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxBanner> listWxBanners = ExcelImportUtil.importExcel(file.getInputStream(), WxBanner.class, params);
              wxBannerService.saveBatch(listWxBanners);
              return Result.ok("文件导入成功！数据行数:" + listWxBanners.size());
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
