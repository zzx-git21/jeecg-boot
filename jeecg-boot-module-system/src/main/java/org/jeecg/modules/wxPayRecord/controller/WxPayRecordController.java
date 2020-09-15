package org.jeecg.modules.wxPayRecord.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import org.jeecg.modules.wxPayRecord.entity.WxPayRecord;
import org.jeecg.modules.wxPayRecord.service.IWxPayRecordService;
import org.jeecg.modules.wxPotentialCustomer.entity.WxPotentialCustomer;
import org.jeecg.modules.wxPotentialCustomer.service.IWxPotentialCustomerService;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecg.modules.wxStudy.service.IWxStudyService;
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
 * @Description: 缴费记录
 * @Author: jeecg-boot
 * @Date:   2020-03-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="缴费记录")
@RestController
@RequestMapping("/wxPayRecord/wxPayRecord")
public class WxPayRecordController {
	@Autowired
	private IWxPayRecordService wxPayRecordService;
	@Autowired
	private IWxStudyService wxStudyService;
	
	@Autowired
	private IWxPotentialCustomerService wxPotentialCustomerService;
	
	/**
	  * 分页列表查询
	 * @param wxPayRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "缴费记录-分页列表查询")
	@ApiOperation(value="缴费记录-分页列表查询WEB", notes="缴费记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WxPayRecord>> queryPageList(WxPayRecord wxPayRecord,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxPayRecord>> result = new Result<IPage<WxPayRecord>>();
		QueryWrapper<WxPayRecord> queryWrapper = QueryGenerator.initQueryWrapper(wxPayRecord, req.getParameterMap());
		Page<WxPayRecord> page = new Page<WxPayRecord>(pageNo, pageSize);
		IPage<WxPayRecord> pageList = wxPayRecordService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxPayRecord
	 * @return
	 */
	@AutoLog(value = "缴费记录-添加")
	@ApiOperation(value="缴费记录-添加WEB", notes="缴费记录-添加")
	@PostMapping(value = "/add")
	public Result<WxPayRecord> add(@RequestBody WxPayRecord wxPayRecord) {
		Result<WxPayRecord> result = new Result<WxPayRecord>();
		try {
			WxPotentialCustomer wxPotentialCustomerEntity = wxPotentialCustomerService.getById(wxPayRecord.getQiankeid());
			String studyid="1"+System.currentTimeMillis();
			String keshi=wxPayRecord.getKeshi();
			//添加学生
			WxStudy wxStudy=new WxStudy();
			wxStudy.setBanjiId(wxPayRecord.getBanji());
			wxStudy.setUserName(wxPayRecord.getUsername());
			wxStudy.setLinkMobile(wxPotentialCustomerEntity.getTelephone());
			wxStudy.setStudyName(wxPotentialCustomerEntity.getStudentName());
			wxStudy.setId(studyid);
			wxStudy.setTotalKeshi(wxPayRecord.getKeshi());
			//正常
			wxStudy.setNormalBalance(wxPayRecord.getXuefeiAmount());
			wxStudy.setNormalUnitPrice(getUnitPrice(wxPayRecord.getXuefeiAmount(),keshi));
			//优惠
			wxStudy.setDiscountBalance(wxPayRecord.getXf());
			wxStudy.setDiscountUnitPrice(getUnitPrice(wxPayRecord.getXf(),keshi));
			//
			wxStudy.setShijiXuefei(wxPayRecord.getXf());
			wxStudy.setXiaohaoDiscountXuefei("0");
			wxStudy.setXiaohaoKeshi("0");
			wxStudy.setXiaohaoNomalXuefei("0");
			wxStudy.setXuefei(wxPayRecord.getXuefeiAmount());
			wxStudy.setZhekou(wxPayRecord.getDiscount());
			wxStudy.setYouhui(wxPayRecord.getYouhui());
			wxStudyService.save(wxStudy);
			System.out.println("studyid="+studyid);
			wxPayRecord.setStudyId(studyid);
		  //添加缴费记录
			wxPayRecordService.save(wxPayRecord);
			
			
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	public String getUnitPrice(String totalMoney,String keshi){
		if("".equals(totalMoney)||"".equals(keshi)){
			return "0";
		}else{
			double  unitPrice=divide(Double.parseDouble(totalMoney),Double.parseDouble(keshi),2);
			return  String.valueOf(unitPrice);
		}
		
	}
	 public  double divide(double a, double b, int scale){
	        BigDecimal bd1 = new BigDecimal(Double.toString(a));
	        BigDecimal bd2 = new BigDecimal(Double.toString(b));
	        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	
	/**
	  *  编辑
	 * @param wxPayRecord
	 * @return
	 */
	@AutoLog(value = "缴费记录-编辑")
	@ApiOperation(value="缴费记录-编辑WEB", notes="缴费记录-编辑")
	@PutMapping(value = "/edit")
	public Result<WxPayRecord> edit(@RequestBody WxPayRecord wxPayRecord) {
		Result<WxPayRecord> result = new Result<WxPayRecord>();
		WxPayRecord wxPayRecordEntity = wxPayRecordService.getById(wxPayRecord.getId());
		if(wxPayRecordEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxPayRecordService.updateById(wxPayRecord);
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
	@AutoLog(value = "缴费记录-通过id删除")
	@ApiOperation(value="缴费记录-通过id删除WEB", notes="缴费记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			wxPayRecordService.removeById(id);
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
	@AutoLog(value = "缴费记录-批量删除")
	@ApiOperation(value="缴费记录-批量删除WEB", notes="缴费记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxPayRecord> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxPayRecord> result = new Result<WxPayRecord>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxPayRecordService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "缴费记录-通过id查询")
	@ApiOperation(value="缴费记录-通过id查询WEB", notes="缴费记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WxPayRecord> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxPayRecord> result = new Result<WxPayRecord>();
		WxPayRecord wxPayRecord = wxPayRecordService.getById(id);
		if(wxPayRecord==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxPayRecord);
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
      QueryWrapper<WxPayRecord> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              WxPayRecord wxPayRecord = JSON.parseObject(deString, WxPayRecord.class);
              queryWrapper = QueryGenerator.initQueryWrapper(wxPayRecord, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<WxPayRecord> pageList = wxPayRecordService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "缴费记录列表");
      mv.addObject(NormalExcelConstants.CLASS, WxPayRecord.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("缴费记录列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxPayRecord> listWxPayRecords = ExcelImportUtil.importExcel(file.getInputStream(), WxPayRecord.class, params);
              wxPayRecordService.saveBatch(listWxPayRecords);
              return Result.ok("文件导入成功！数据行数:" + listWxPayRecords.size());
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
