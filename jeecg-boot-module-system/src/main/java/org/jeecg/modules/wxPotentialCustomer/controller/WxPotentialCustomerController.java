package org.jeecg.modules.wxPotentialCustomer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.DoubleUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 潜客,潜在客户
 * @Author: jeecg-boot
 * @Date:   2020-01-07
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxPotentialCustomer/wxPotentialCustomer")
@Slf4j
@Api(tags = "潜客,潜在客户")
public class WxPotentialCustomerController {
	@Autowired
	private IWxPotentialCustomerService wxPotentialCustomerService;
	@Autowired
	private IWxStudyService wxStudyService;
	/**
	  * 分页列表查询
	 * @param wxPotentialCustomer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "分页列表查询 WEB")
	@GetMapping(value = "/list")
	public Result<IPage<WxPotentialCustomer>> queryPageList(WxPotentialCustomer wxPotentialCustomer,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxPotentialCustomer>> result = new Result<IPage<WxPotentialCustomer>>();
		QueryWrapper<WxPotentialCustomer> queryWrapper = QueryGenerator.initQueryWrapper(wxPotentialCustomer, req.getParameterMap());
		
		Page<WxPotentialCustomer> page = new Page<WxPotentialCustomer>(pageNo, pageSize);
		IPage<WxPotentialCustomer> pageList = wxPotentialCustomerService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  * 分页列表查询
	 * @param wxPotentialCustomer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "潜客分页列表查询 WEB")
	@GetMapping(value = "/qklist")
	public Result<IPage<WxPotentialCustomer>> queryqkPageList(WxPotentialCustomer wxPotentialCustomer,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxPotentialCustomer>> result = new Result<IPage<WxPotentialCustomer>>();
		QueryWrapper<WxPotentialCustomer> queryWrapper = QueryGenerator.initQueryWrapper(wxPotentialCustomer, req.getParameterMap());
		
		ArrayList arrayList = new ArrayList();
		arrayList.add("2");//预约
		arrayList.add("5");//继续跟进
		arrayList.add("6");//主动联系
		arrayList.add("7");//暂不需要

		queryWrapper.in("yyjg",arrayList);
		Page<WxPotentialCustomer> page = new Page<WxPotentialCustomer>(pageNo, pageSize);
		IPage<WxPotentialCustomer> pageList = wxPotentialCustomerService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  * 潜客分配
	 * @param wxPotentialCustomer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "潜客分配分页列表查询 WEB")
	@GetMapping(value = "/listFenpei")
	public Result<IPage<WxPotentialCustomer>> listFenpei(WxPotentialCustomer wxPotentialCustomer,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxPotentialCustomer>> result = new Result<IPage<WxPotentialCustomer>>();
		QueryWrapper<WxPotentialCustomer> queryWrapper = QueryGenerator.initQueryWrapper(wxPotentialCustomer, req.getParameterMap());
		Page<WxPotentialCustomer> page = new Page<WxPotentialCustomer>(pageNo, pageSize);
		IPage<WxPotentialCustomer> pageList = wxPotentialCustomerService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param wxPotentialCustomer
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxPotentialCustomer> add(@RequestBody WxPotentialCustomer wxPotentialCustomer) {
		Result<WxPotentialCustomer> result = new Result<WxPotentialCustomer>();
		try {
			//
			 LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			wxPotentialCustomer.setCollecter(user.getId());
			wxPotentialCustomerService.save(wxPotentialCustomer);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxPotentialCustomer
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxPotentialCustomer> edit(@RequestBody WxPotentialCustomer wxPotentialCustomer) {
		Result<WxPotentialCustomer> result = new Result<WxPotentialCustomer>();
		WxPotentialCustomer wxPotentialCustomerEntity = wxPotentialCustomerService.getById(wxPotentialCustomer.getId());
		if(wxPotentialCustomerEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxPotentialCustomerService.updateById(wxPotentialCustomer);
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
			wxPotentialCustomerService.removeById(id);
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
	public Result<WxPotentialCustomer> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxPotentialCustomer> result = new Result<WxPotentialCustomer>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxPotentialCustomerService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxPotentialCustomer> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxPotentialCustomer> result = new Result<WxPotentialCustomer>();
		WxPotentialCustomer wxPotentialCustomer = wxPotentialCustomerService.getById(id);
		if(wxPotentialCustomer==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxPotentialCustomer);
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
  public ModelAndView exportXls(HttpServletRequest request, WxPotentialCustomer wxPotentialCustomer) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxPotentialCustomer> queryWrapper = QueryGenerator.initQueryWrapper(wxPotentialCustomer, request.getParameterMap());
      List<WxPotentialCustomer> pageList = wxPotentialCustomerService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxPotentialCustomer> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "潜客,潜在客户列表");
      mv.addObject(NormalExcelConstants.CLASS, WxPotentialCustomer.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("潜客,潜在客户列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxPotentialCustomer> listWxPotentialCustomers = ExcelImportUtil.importExcel(file.getInputStream(), WxPotentialCustomer.class, params);
              wxPotentialCustomerService.saveBatch(listWxPotentialCustomers);
              return Result.ok("文件导入成功！数据行数:" + listWxPotentialCustomers.size());
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
  
  /**
	  *  批量分配
	 * @param wxPotentialCustomer
	 * @return
	 */
  @GetMapping(value = "/batchFenpei")
	public Result<Object> batchFenpei(
			@RequestParam(name="qiankeList") String qiankeList,
			  @RequestParam(name="ywy") String ywy,
									  HttpServletRequest req) {
	  
  
       Result<Object> result = new Result<Object>();
		
       String[] qiankeListArray = qiankeList.split(",");

       for (int i = 0 ; i <qiankeListArray.length ; i++ ) {
        String qiankeid=qiankeListArray[i];
		WxPotentialCustomer wxPotentialCustomerEntity = wxPotentialCustomerService.getById(qiankeid);
		wxPotentialCustomerEntity.setBelonger(ywy);
		boolean ok = wxPotentialCustomerService.updateById(wxPotentialCustomerEntity);
       } 
       
       System.out.println(qiankeList+"=="+ywy);
       result.success("修改成功!");
		return result;
	}
//SysUserController.java
@GetMapping(value = "/queryComList") 
	public Result<IPage<Map<String,Object>>> queryComList(@RequestParam(name="keyword" ,required=false) String keyword,@RequestParam(name="type") String type,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
	  if(null==keyword){
		  keyword="";
	  }
		System.out.println("搜索的关键字"+keyword);
		Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
		Page<Map<String,Object>> pageList =wxPotentialCustomerService.queryComList(page,keyword,type);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
}

	@GetMapping(value = "/queryStudentCnt")
	public Result<Object> queryStudentCnt(
			@RequestParam(name="banjiId") String banjiId,
									  HttpServletRequest req) {
	   Result<Object> result = new Result<Object>();
		Map<String,Object> studentCntMap =wxPotentialCustomerService.queryStudentCnt(banjiId);
		result.setResult(studentCntMap);

	   System.out.println(banjiId);
	//result.error500("未找到对应实体");
	   result.success("修改成功!");
		return result;
	}
	
	@GetMapping(value = "/queryXuefeiByBanjiid")
	public Result<Object> queryXuefeiByBanjiid(
			@RequestParam(name="banjiId") String banjiId,
									  HttpServletRequest req) {
	   Result<Object> result = new Result<Object>();
		Map<String,Object> queryXuefeiByBanjiid =wxPotentialCustomerService.queryXuefeiByBanjiid(banjiId);
		result.setResult(queryXuefeiByBanjiid);

	   System.out.println(banjiId);
	//result.error500("未找到对应实体");
	   result.success("修改成功!");
		return result;
	}
	
	@GetMapping(value = "/queryComListNew") 
	public Result<IPage<Map<String,Object>>> queryComListNew(@RequestParam(name="studyName" ,required=false) String studyName,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {

		System.out.println("搜索的关键字"+studyName);
		Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("studyName",studyName);
		Page<Map<String,Object>> pageList =wxPotentialCustomerService.queryComListNew(page,m,"1");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
}
	
	
	@GetMapping(value = "/tuifeiList") 
	public Result<IPage<Map<String,Object>>> tuifeiList(@RequestParam(name="studyName" ,required=false) String studyName,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {

		System.out.println("搜索的关键字"+studyName);//刘思逸
		Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("studyName",studyName);
		Page<Map<String,Object>> pageList =wxPotentialCustomerService.tuifeiList(page,m);
		/*List<Map<String,Object>> records=pageList.getRecords();
		for(Map<String,Object> record:records){
			String studyid=record.get("studyid")==null?"":record.get("studyid").toString();
			String userid=record.get("user_id")==null?"":record.get("user_id").toString();
			//查询学生签到次数
			long qiandaoCnt=wxPotentialCustomerService.queryQiandaoCnt(studyid);
			record.put("qiandaoCnt", qiandaoCnt);
			//查询学生交的学费
		}
		pageList.setRecords(records);*/
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
}
	
	
	@GetMapping(value = "/queryJiaoFeiById")
	public Result<Object> queryJiaoFeiById(
			@RequestParam(name="qkId") String qkId,
									  HttpServletRequest req) {
	   Result<Object> result = new Result<Object>();
	   List<Map<String,Object>> queryJiaoFeiList =wxPotentialCustomerService.queryJiaoFeiById(qkId);
		result.setResult(queryJiaoFeiList);

	   System.out.println(qkId);
	   result.success("修改成功!");
		return result;
	}
	@GetMapping(value = "/lijikoufei")
	public Result<Object> lijikoufei(
			@RequestParam(name="keshishu") String keshishu,
			@RequestParam(name="studyid") String studyid,
									  HttpServletRequest req) {
		WxStudy wxStudyEntity = wxStudyService.getById(studyid);
		//本次消耗课时数
		double bckss=Double.parseDouble(keshishu);
		//消耗课时
		double xiaohaokss=Double.parseDouble(wxStudyEntity.getXiaohaoKeshi());

		double normalBalance=Double.parseDouble(wxStudyEntity.getNormalBalance());
		double normalUnitPrice=Double.parseDouble(wxStudyEntity.getNormalUnitPrice());
		double xiaohaoNomalXuefei=Double.parseDouble(wxStudyEntity.getXiaohaoNomalXuefei());
		//本次消耗正常学费
		double bcnormalXuefei=DoubleUtils.mul(bckss, normalUnitPrice);
		xiaohaoNomalXuefei=DoubleUtils.add(xiaohaoNomalXuefei, bcnormalXuefei);
		normalBalance=DoubleUtils.sub(normalBalance, bcnormalXuefei);
		
		double discountBalance=Double.parseDouble(wxStudyEntity.getDiscountBalance());
		double discountUnitPrice=Double.parseDouble(wxStudyEntity.getDiscountUnitPrice());
		double xiaohaoDiscountXuefei=Double.parseDouble(wxStudyEntity.getXiaohaoDiscountXuefei());
		//本次消耗优惠学费
		double bcdiscountXuefei=DoubleUtils.mul(bckss, discountUnitPrice);
		xiaohaoDiscountXuefei=DoubleUtils.add(xiaohaoDiscountXuefei, bcdiscountXuefei);
		discountBalance=DoubleUtils.sub(discountBalance, xiaohaoNomalXuefei);
		//消耗课时
		xiaohaokss=DoubleUtils.add(xiaohaokss, bckss);

		wxStudyEntity.setXiaohaoKeshi(xiaohaokss+"");
		wxStudyEntity.setNormalBalance(normalBalance+"");
		wxStudyEntity.setXiaohaoNomalXuefei(xiaohaoNomalXuefei+"");
		wxStudyEntity.setDiscountBalance(discountBalance+"");
		wxStudyEntity.setXiaohaoDiscountXuefei(xiaohaoDiscountXuefei+"");
		
	   Result<Object> result = new Result<Object>();
	   
	   boolean ok = wxStudyService.updateById(wxStudyEntity);
		//TODO 返回false说明什么？
		if(ok) {
			result.success("修改成功!");
		}
		
		return result;
	}

}
