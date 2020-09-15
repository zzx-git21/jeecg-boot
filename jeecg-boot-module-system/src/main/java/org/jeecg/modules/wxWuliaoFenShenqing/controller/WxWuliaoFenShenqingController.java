package org.jeecg.modules.wxWuliaoFenShenqing.controller;

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
 * @Description: 分校物料申请
 * @Author: jeecg-boot
 * @Date:   2020-03-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="分校物料申请")
@RestController
@RequestMapping("/wxWuliaoFenShenqing/wxWuliaoFenShenqing")
public class WxWuliaoFenShenqingController {
	@Autowired
	private IWxWuliaoFenShenqingService wxWuliaoFenShenqingService;
	
	/**
	  * 分页列表查询
	 * @param wxWuliaoFenShenqing
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "分校物料申请-分页列表查询")
	@ApiOperation(value="分校物料申请-分页列表查询", notes="分校物料申请-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxWuliaoFenShenqing>> queryPageList(WxWuliaoFenShenqing wxWuliaoFenShenqing,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxWuliaoFenShenqing>> result = new Result<IPage<WxWuliaoFenShenqing>>();
		QueryWrapper<WxWuliaoFenShenqing> queryWrapper = QueryGenerator.initQueryWrapper(wxWuliaoFenShenqing, req.getParameterMap());
		Page<WxWuliaoFenShenqing> page = new Page<WxWuliaoFenShenqing>(pageNo, pageSize);
		IPage<WxWuliaoFenShenqing> pageList = wxWuliaoFenShenqingService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxWuliaoFenShenqing
	 * @return
	 */
	@AutoLog(value = "分校物料申请-添加")
	@ApiOperation(value="分校物料申请-添加", notes="分校物料申请-添加")
	@PostMapping(value = "/add")
	public Result<WxWuliaoFenShenqing> add(@RequestBody WxWuliaoFenShenqing wxWuliaoFenShenqing) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		try {
			wxWuliaoFenShenqing.setFenCheckStatus("0");
			wxWuliaoFenShenqing.setZongCheckStatus("0");
			wxWuliaoFenShenqing.setShenqingType("1");
			wxWuliaoFenShenqing.setShenqingStatus("0");
			wxWuliaoFenShenqing.setShenqingTime(new Date());
			wxWuliaoFenShenqingService.save(wxWuliaoFenShenqing);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxWuliaoFenShenqing
	 * @return
	 */
	@AutoLog(value = "分校物料申请-编辑")
	@ApiOperation(value="分校物料申请-编辑", notes="分校物料申请-编辑")
	@PutMapping(value = "/edit")
	public Result<WxWuliaoFenShenqing> edit(@RequestBody WxWuliaoFenShenqing wxWuliaoFenShenqing) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		WxWuliaoFenShenqing wxWuliaoFenShenqingEntity = wxWuliaoFenShenqingService.getById(wxWuliaoFenShenqing.getId());
		if(wxWuliaoFenShenqingEntity==null) {
			result.error500("未找到对应实体");
		}else {
			if(wxWuliaoFenShenqing.getZongCheckStatus()!=null){
			//如果总校审核为通过
			if(wxWuliaoFenShenqing.getZongCheckStatus().equals("1")){
				wxWuliaoFenShenqing.setShenqingStatus("4");//申请状态为待发货
			}
			//如果总校审核为不通过
			if(wxWuliaoFenShenqing.getZongCheckStatus().equals("0")){
				wxWuliaoFenShenqing.setShenqingStatus("0");//申请状态为等待审核
			}
			}
			boolean ok = wxWuliaoFenShenqingService.updateById(wxWuliaoFenShenqing);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	
	/**
	  *  发货
	 * @param wxWuliaoFenShenqing
	 * @return
	 */
	@AutoLog(value = "分校物料申请-发货")
	@ApiOperation(value="分校物料申请-发货", notes="分校物料申请-发货")
	@PutMapping(value = "/fahuo")
	public Result<WxWuliaoFenShenqing> fahuo(@RequestBody WxWuliaoFenShenqing wxWuliaoFenShenqing) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		WxWuliaoFenShenqing wxWuliaoFenShenqingEntity = wxWuliaoFenShenqingService.getById(wxWuliaoFenShenqing.getId());
		if(wxWuliaoFenShenqingEntity==null) {
			result.error500("未找到对应实体");
		}else {
			wxWuliaoFenShenqing.setFahuoTime(new Date());
			wxWuliaoFenShenqing.setShenqingStatus("1");//等待收货
			boolean ok = wxWuliaoFenShenqingService.updateById(wxWuliaoFenShenqing);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("发货成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  编辑订单号
	 * @param wxWuliaoFenShenqing
	 * @return
	 */
	@AutoLog(value = "分校物料申请-编辑订单号")
	@ApiOperation(value="分校物料申请-编辑订单号", notes="分校物料申请-编辑订单号")
	@PutMapping(value = "/editOrderNum")
	public Result<WxWuliaoFenShenqing> editOrderNum(@RequestBody WxWuliaoFenShenqing wxWuliaoFenShenqing) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		WxWuliaoFenShenqing wxWuliaoFenShenqingEntity = wxWuliaoFenShenqingService.getById(wxWuliaoFenShenqing.getId());
		if(wxWuliaoFenShenqingEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxWuliaoFenShenqingService.updateById(wxWuliaoFenShenqing);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改订单号成功!");
			}
		}
		
		return result;
	}
	
	
	@AutoLog(value = "分校物料申请-取消")
	@ApiOperation(value="分校物料申请-取消", notes="分校物料申请-取消")
	@GetMapping(value = "/quxiao")
	public Result<WxWuliaoFenShenqing> quxiao(String shenqingId) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		WxWuliaoFenShenqing wxWuliaoFenShenqingEntity = wxWuliaoFenShenqingService.getById(shenqingId);
		if(wxWuliaoFenShenqingEntity==null) {
			result.error500("未找到对应实体");
		}else {
			//WxWuliaoFenShenqing wxWuliaoFenShenqing=new WxWuliaoFenShenqing();
			wxWuliaoFenShenqingEntity.setShenqingStatus("3");
			boolean ok = wxWuliaoFenShenqingService.updateById(wxWuliaoFenShenqingEntity);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("取消成功!");
			}
		}
		
		return result;
	}
	
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "分校物料申请-通过id删除")
	@ApiOperation(value="分校物料申请-通过id删除", notes="分校物料申请-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxWuliaoFenShenqingService.removeById(id);
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
	@AutoLog(value = "分校物料申请-批量删除")
	@ApiOperation(value="分校物料申请-批量删除", notes="分校物料申请-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxWuliaoFenShenqing> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxWuliaoFenShenqingService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "分校物料申请-通过id查询")
	@ApiOperation(value="分校物料申请-通过id查询", notes="分校物料申请-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxWuliaoFenShenqing> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxWuliaoFenShenqing> result = new Result<WxWuliaoFenShenqing>();
		WxWuliaoFenShenqing wxWuliaoFenShenqing = wxWuliaoFenShenqingService.getById(id);
		if(wxWuliaoFenShenqing==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxWuliaoFenShenqing);
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
      QueryWrapper<WxWuliaoFenShenqing> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxWuliaoFenShenqing wxWuliaoFenShenqing = JSON.parseObject(deString, WxWuliaoFenShenqing.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxWuliaoFenShenqing, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxWuliaoFenShenqing> pageList = wxWuliaoFenShenqingService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "分校物料申请列表");
      mv.addObject(NormalExcelConstants.CLASS, WxWuliaoFenShenqing.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("分校物料申请列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxWuliaoFenShenqing> listWxWuliaoFenShenqings = ExcelImportUtil.importExcel(file.getInputStream(), WxWuliaoFenShenqing.class, params);
              wxWuliaoFenShenqingService.saveBatch(listWxWuliaoFenShenqings);
              return Result.ok("文件导入成功！数据行数:" + listWxWuliaoFenShenqings.size());
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
