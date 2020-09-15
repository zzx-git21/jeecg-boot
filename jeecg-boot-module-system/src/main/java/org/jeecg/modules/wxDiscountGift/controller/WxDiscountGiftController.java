package org.jeecg.modules.wxDiscountGift.controller;

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
import org.jeecg.modules.wxDiscountGift.entity.WxDiscountGift;
import org.jeecg.modules.wxDiscountGift.service.IWxDiscountGiftService;
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
 * @Description: 优惠折扣礼品
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="优惠折扣礼品")
@RestController
@RequestMapping("/wxDiscountGift/wxDiscountGift")
public class WxDiscountGiftController {
	@Autowired
	private IWxDiscountGiftService wxDiscountGiftService;
	
	/**
	  * 分页列表查询
	 * @param wxDiscountGift
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "优惠折扣礼品-分页列表查询")
	@ApiOperation(value="优惠折扣礼品-分页列表查询WEB", notes="优惠折扣礼品-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxDiscountGift>> queryPageList(WxDiscountGift wxDiscountGift,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxDiscountGift>> result = new Result<IPage<WxDiscountGift>>();
		QueryWrapper<WxDiscountGift> queryWrapper = QueryGenerator.initQueryWrapper(wxDiscountGift, req.getParameterMap());
		Page<WxDiscountGift> page = new Page<WxDiscountGift>(pageNo, pageSize);
		IPage<WxDiscountGift> pageList = wxDiscountGiftService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxDiscountGift
	 * @return
	 */
	@AutoLog(value = "优惠折扣礼品-添加")
	@ApiOperation(value="优惠折扣礼品-添加WEB", notes="优惠折扣礼品-添加")
	@PostMapping(value = "/add")
	public Result<WxDiscountGift> add(@RequestBody WxDiscountGift wxDiscountGift) {
		Result<WxDiscountGift> result = new Result<WxDiscountGift>();
		try {
			wxDiscountGiftService.save(wxDiscountGift);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxDiscountGift
	 * @return
	 */
	@AutoLog(value = "优惠折扣礼品-编辑")
	@ApiOperation(value="优惠折扣礼品-编辑WEB", notes="优惠折扣礼品-编辑")
	@PutMapping(value = "/edit")
	public Result<WxDiscountGift> edit(@RequestBody WxDiscountGift wxDiscountGift) {
		Result<WxDiscountGift> result = new Result<WxDiscountGift>();
		WxDiscountGift wxDiscountGiftEntity = wxDiscountGiftService.getById(wxDiscountGift.getId());
		if(wxDiscountGiftEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxDiscountGiftService.updateById(wxDiscountGift);
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
	@AutoLog(value = "优惠折扣礼品-通过id删除")
	@ApiOperation(value="优惠折扣礼品-通过id删除WEB", notes="优惠折扣礼品-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxDiscountGiftService.removeById(id);
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
	@AutoLog(value = "优惠折扣礼品-批量删除")
	@ApiOperation(value="优惠折扣礼品-批量删除WEB", notes="优惠折扣礼品-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxDiscountGift> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxDiscountGift> result = new Result<WxDiscountGift>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxDiscountGiftService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "优惠折扣礼品-通过id查询")
	@ApiOperation(value="优惠折扣礼品-通过id查询WEB", notes="优惠折扣礼品-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxDiscountGift> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxDiscountGift> result = new Result<WxDiscountGift>();
		WxDiscountGift wxDiscountGift = wxDiscountGiftService.getById(id);
		if(wxDiscountGift==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxDiscountGift);
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
      QueryWrapper<WxDiscountGift> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxDiscountGift wxDiscountGift = JSON.parseObject(deString, WxDiscountGift.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxDiscountGift, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxDiscountGift> pageList = wxDiscountGiftService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "优惠折扣礼品列表");
      mv.addObject(NormalExcelConstants.CLASS, WxDiscountGift.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("优惠折扣礼品列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxDiscountGift> listWxDiscountGifts = ExcelImportUtil.importExcel(file.getInputStream(), WxDiscountGift.class, params);
              wxDiscountGiftService.saveBatch(listWxDiscountGifts);
              return Result.ok("文件导入成功！数据行数:" + listWxDiscountGifts.size());
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
