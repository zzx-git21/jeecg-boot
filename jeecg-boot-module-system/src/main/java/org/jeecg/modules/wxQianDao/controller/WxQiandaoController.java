package org.jeecg.modules.wxQianDao.controller;

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
import org.jeecg.modules.wxQianDao.entity.WxQiandao;
import org.jeecg.modules.wxQianDao.service.IWxQiandaoService;

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
 * @Description: 签到
 * @Author: jeecg-boot
 * @Date:   2020-06-19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxQianDao/wxQiandao")
@Slf4j
public class WxQiandaoController {
	@Autowired
	private IWxQiandaoService wxQiandaoService;
	
	/**
	  * 分页列表查询
	 * @param wxQiandao
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WxQiandao>> queryPageList(WxQiandao wxQiandao,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxQiandao>> result = new Result<IPage<WxQiandao>>();
		QueryWrapper<WxQiandao> queryWrapper = QueryGenerator.initQueryWrapper(wxQiandao, req.getParameterMap());
		Page<WxQiandao> page = new Page<WxQiandao>(pageNo, pageSize);
		IPage<WxQiandao> pageList = wxQiandaoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxQiandao
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxQiandao> add(@RequestBody WxQiandao wxQiandao) {
		Result<WxQiandao> result = new Result<WxQiandao>();
		try {
			wxQiandaoService.save(wxQiandao);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	

	/**
	  *   添加
	 * @param wxQiandao
	 * @return
	 */
	@PostMapping(value = "/addKaoqing")
	@ApiOperation(value = "添加考勤", notes = "author by skycc")
	public Result<WxQiandao> addKaoqing(@RequestBody WxQiandao wxQiandao) {
		Result<WxQiandao> result = new Result<WxQiandao>();
		try {
			wxQiandaoService.save(wxQiandao);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxQiandao
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxQiandao> edit(@RequestBody WxQiandao wxQiandao) {
		Result<WxQiandao> result = new Result<WxQiandao>();
		WxQiandao wxQiandaoEntity = wxQiandaoService.getById(wxQiandao.getId());
		if(wxQiandaoEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxQiandaoService.updateById(wxQiandao);
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
			wxQiandaoService.removeById(id);
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
	public Result<WxQiandao> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxQiandao> result = new Result<WxQiandao>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxQiandaoService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxQiandao> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxQiandao> result = new Result<WxQiandao>();
		WxQiandao wxQiandao = wxQiandaoService.getById(id);
		if(wxQiandao==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxQiandao);
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
  public ModelAndView exportXls(HttpServletRequest request, WxQiandao wxQiandao) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxQiandao> queryWrapper = QueryGenerator.initQueryWrapper(wxQiandao, request.getParameterMap());
      List<WxQiandao> pageList = wxQiandaoService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxQiandao> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "签到列表");
      mv.addObject(NormalExcelConstants.CLASS, WxQiandao.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("签到列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxQiandao> listWxQiandaos = ExcelImportUtil.importExcel(file.getInputStream(), WxQiandao.class, params);
              wxQiandaoService.saveBatch(listWxQiandaos);
              return Result.ok("文件导入成功！数据行数:" + listWxQiandaos.size());
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
