package org.jeecg.modules.wxFapiao.controller;

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
import org.jeecg.modules.wxFapiao.entity.WxFapiao;
import org.jeecg.modules.wxFapiao.service.IWxFapiaoService;
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
 * @Description: 发票
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="发票")
@RestController
@RequestMapping("/wxFapiao/wxFapiao")
public class WxFapiaoController {
	@Autowired
	private IWxFapiaoService wxFapiaoService;
	
	/**
	  * 分页列表查询
	 * @param wxFapiao
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "发票-分页列表查询")
	@ApiOperation(value="发票-分页列表查询WEB", notes="发票-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxFapiao>> queryPageList(WxFapiao wxFapiao,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxFapiao>> result = new Result<IPage<WxFapiao>>();
		QueryWrapper<WxFapiao> queryWrapper = QueryGenerator.initQueryWrapper(wxFapiao, req.getParameterMap());
		Page<WxFapiao> page = new Page<WxFapiao>(pageNo, pageSize);
		IPage<WxFapiao> pageList = wxFapiaoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxFapiao
	 * @return
	 */
	@AutoLog(value = "发票-添加")
	@ApiOperation(value="发票-添加WEB", notes="发票-添加")
	@PostMapping(value = "/add")
	public Result<WxFapiao> add(@RequestBody WxFapiao wxFapiao) {
		Result<WxFapiao> result = new Result<WxFapiao>();
		try {
			wxFapiaoService.save(wxFapiao);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxFapiao
	 * @return
	 */
	@AutoLog(value = "发票-编辑")
	@ApiOperation(value="发票-编辑WEB", notes="发票-编辑")
	@PutMapping(value = "/edit")
	public Result<WxFapiao> edit(@RequestBody WxFapiao wxFapiao) {
		Result<WxFapiao> result = new Result<WxFapiao>();
		WxFapiao wxFapiaoEntity = wxFapiaoService.getById(wxFapiao.getId());
		if(wxFapiaoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxFapiaoService.updateById(wxFapiao);
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
	@AutoLog(value = "发票-通过id删除")
	@ApiOperation(value="发票-通过id删除WEB", notes="发票-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxFapiaoService.removeById(id);
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
	@AutoLog(value = "发票-批量删除")
	@ApiOperation(value="发票-批量删除WEB", notes="发票-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxFapiao> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxFapiao> result = new Result<WxFapiao>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxFapiaoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "发票-通过id查询")
	@ApiOperation(value="发票-通过id查询WEB", notes="发票-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxFapiao> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxFapiao> result = new Result<WxFapiao>();
		WxFapiao wxFapiao = wxFapiaoService.getById(id);
		if(wxFapiao==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxFapiao);
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
      QueryWrapper<WxFapiao> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxFapiao wxFapiao = JSON.parseObject(deString, WxFapiao.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxFapiao, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxFapiao> pageList = wxFapiaoService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "发票列表");
      mv.addObject(NormalExcelConstants.CLASS, WxFapiao.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("发票列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxFapiao> listWxFapiaos = ExcelImportUtil.importExcel(file.getInputStream(), WxFapiao.class, params);
              wxFapiaoService.saveBatch(listWxFapiaos);
              return Result.ok("文件导入成功！数据行数:" + listWxFapiaos.size());
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
