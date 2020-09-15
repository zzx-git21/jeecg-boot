package org.jeecg.modules.wxWuliaoFenKucun.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxWuliaoFenKucun.entity.WxWuliaoFenKucun;
import org.jeecg.modules.wxWuliaoFenKucun.service.IWxWuliaoFenKucunService;
import org.jeecg.modules.wxWuliaoFenShenqing.entity.WxWuliaoFenShenqing;
import org.jeecg.modules.wxWuliaoFenShenqing.service.IWxWuliaoFenShenqingService;
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
 * @Description: 分校库存
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="分校库存")
@RestController
@RequestMapping("/wxWuliaoFenKucun/wxWuliaoFenKucun")
public class WxWuliaoFenKucunController {
	@Autowired
	private IWxWuliaoFenKucunService wxWuliaoFenKucunService;
	@Autowired
	private IWxWuliaoFenShenqingService wxWuliaoFenShenqingService;
	/**
	  * 分页列表查询
	 * @param wxWuliaoFenKucun
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "分校库存-分页列表查询")
	@ApiOperation(value="分校库存-分页列表查询", notes="分校库存-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxWuliaoFenKucun>> queryPageList(WxWuliaoFenKucun wxWuliaoFenKucun,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxWuliaoFenKucun>> result = new Result<IPage<WxWuliaoFenKucun>>();
		QueryWrapper<WxWuliaoFenKucun> queryWrapper = QueryGenerator.initQueryWrapper(wxWuliaoFenKucun, req.getParameterMap());
		Page<WxWuliaoFenKucun> page = new Page<WxWuliaoFenKucun>(pageNo, pageSize);
		IPage<WxWuliaoFenKucun> pageList = wxWuliaoFenKucunService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@AutoLog(value = "分校库存-入库")
	@ApiOperation(value="分校库存-入库", notes="分校库存-入库")
	@GetMapping(value = "/ruku")
	public Result<WxWuliaoFenKucun> ruku(
			@RequestParam(name="shengqingDanId") String shengqingDanId,
			@RequestParam(name="ifCanDingyong") String ifCanDingyong,
			@RequestParam(name="wuliaoCunfangAddr") String wuliaoCunfangAddr,
									  HttpServletRequest req) {
		Result<WxWuliaoFenKucun> result = new Result<WxWuliaoFenKucun>();
		try {
			WxWuliaoFenShenqing wxWuliaoFenShenqingEntity = wxWuliaoFenShenqingService.getById(shengqingDanId);
			
			WxWuliaoFenKucun wxWuliaoFenKucun=new WxWuliaoFenKucun();
			wxWuliaoFenKucun.setWuliaoId(wxWuliaoFenShenqingEntity.getWuliaoId());
			wxWuliaoFenKucun.setIfCanDingyong(ifCanDingyong);
			wxWuliaoFenKucun.setWuliaoCunfangAddr(wuliaoCunfangAddr);
			wxWuliaoFenKucun.setShenqingDanwei(wxWuliaoFenShenqingEntity.getShenqingDanwei());
			wxWuliaoFenKucun.setCurrentNum(wxWuliaoFenShenqingEntity.getShenqingNum());
			wxWuliaoFenKucun.setRukuTime(new Date());
			wxWuliaoFenKucunService.save(wxWuliaoFenKucun);
			result.success("入库成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	
	/**
	  *   添加
	 * @param wxWuliaoFenKucun
	 * @return
	 */
	@AutoLog(value = "分校库存-添加")
	@ApiOperation(value="分校库存-添加", notes="分校库存-添加")
	@PostMapping(value = "/add")
	public Result<WxWuliaoFenKucun> add(@RequestBody WxWuliaoFenKucun wxWuliaoFenKucun) {
		Result<WxWuliaoFenKucun> result = new Result<WxWuliaoFenKucun>();
		try {
			wxWuliaoFenKucunService.save(wxWuliaoFenKucun);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxWuliaoFenKucun
	 * @return
	 */
	@AutoLog(value = "分校库存-编辑")
	@ApiOperation(value="分校库存-编辑", notes="分校库存-编辑")
	@PutMapping(value = "/edit")
	public Result<WxWuliaoFenKucun> edit(@RequestBody WxWuliaoFenKucun wxWuliaoFenKucun) {
		Result<WxWuliaoFenKucun> result = new Result<WxWuliaoFenKucun>();
		WxWuliaoFenKucun wxWuliaoFenKucunEntity = wxWuliaoFenKucunService.getById(wxWuliaoFenKucun.getId());
		if(wxWuliaoFenKucunEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxWuliaoFenKucunService.updateById(wxWuliaoFenKucun);
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
	@AutoLog(value = "分校库存-通过id删除")
	@ApiOperation(value="分校库存-通过id删除", notes="分校库存-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxWuliaoFenKucunService.removeById(id);
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
	@AutoLog(value = "分校库存-批量删除")
	@ApiOperation(value="分校库存-批量删除", notes="分校库存-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxWuliaoFenKucun> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxWuliaoFenKucun> result = new Result<WxWuliaoFenKucun>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxWuliaoFenKucunService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "分校库存-通过id查询")
	@ApiOperation(value="分校库存-通过id查询", notes="分校库存-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxWuliaoFenKucun> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxWuliaoFenKucun> result = new Result<WxWuliaoFenKucun>();
		WxWuliaoFenKucun wxWuliaoFenKucun = wxWuliaoFenKucunService.getById(id);
		if(wxWuliaoFenKucun==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxWuliaoFenKucun);
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
      QueryWrapper<WxWuliaoFenKucun> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxWuliaoFenKucun wxWuliaoFenKucun = JSON.parseObject(deString, WxWuliaoFenKucun.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxWuliaoFenKucun, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxWuliaoFenKucun> pageList = wxWuliaoFenKucunService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "分校库存列表");
      mv.addObject(NormalExcelConstants.CLASS, WxWuliaoFenKucun.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("分校库存列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxWuliaoFenKucun> listWxWuliaoFenKucuns = ExcelImportUtil.importExcel(file.getInputStream(), WxWuliaoFenKucun.class, params);
              wxWuliaoFenKucunService.saveBatch(listWxWuliaoFenKucuns);
              return Result.ok("文件导入成功！数据行数:" + listWxWuliaoFenKucuns.size());
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
