package org.jeecg.modules.wxStory.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxBanji.entity.WxBanji;
import org.jeecg.modules.wxBanji.service.IWxBanjiService;
import org.jeecg.modules.wxStory.entity.WxStory;
import org.jeecg.modules.wxStory.service.IWxStoryService;
import org.jeecg.modules.wxStoryGradeRel.entity.WxStoryGradeRel;
import org.jeecg.modules.wxStoryGradeRel.service.IWxStoryGradeRelService;
import org.jeecg.modules.wxStudy.entity.WxStudy;
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
 * @Description: 故事大会资料表
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxStory/wxStory")
@Slf4j
@Api(tags = "故事大会接口")
public class WxStoryController {
	@Autowired
	private IWxStoryService wxStoryService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Autowired
		private IWxStoryGradeRelService wxStoryGradeRelService;
	 @Autowired
		private IWxBanjiService wxBanjiService;
	/**
	  * 分页列表查询
	 * @param wxStory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@ApiOperation(value = "查询故事大会WEB")
	public Result<IPage<WxStory>> queryPageList(WxStory wxStory,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxStory>> result = new Result<IPage<WxStory>>();
		QueryWrapper<WxStory> queryWrapper = QueryGenerator.initQueryWrapper(wxStory, req.getParameterMap());
		Page<WxStory> page = new Page<WxStory>(pageNo, pageSize);
		IPage<WxStory> pageList = wxStoryService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@GetMapping(value = "/queryList")
	@ApiOperation(value = "查询故事大会APP")
	public Result<IPage<WxStory>> queryList(WxStory wxStory,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="100") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxStory>> result = new Result<IPage<WxStory>>();
		Page<WxStory> page = new Page<WxStory>(pageNo, pageSize);
		String gradeId=null;
		Page<WxStory> pageList =new Page<WxStory>();
		if(wxStory.getCategory()!=null) {
			wxStoryService.queryCateGroyList(pageList, wxStory, gradeId);

		}
		else
		{
			gradeId=getGradeId(req);
			wxStoryService.queryList(page, wxStory, gradeId);
		}
	
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	

	@GetMapping(value = "/queryStoreyList")
	@ApiOperation(value = "查询故事大会分类")
	public Result<IPage<WxStory>> queryStoreyList(WxStory wxStory,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="100") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxStory>> result = new Result<IPage<WxStory>>();
		Page<WxStory> page = new Page<WxStory>(pageNo, pageSize);
//		String gradeId=getGradeId(req);
		Page<WxStory> pageList =wxStoryService.queryStoreyList(page, wxStory, null);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	//获取年级Id
		public String getGradeId(HttpServletRequest req){
			String gradeId="";
			String username = JwtUtil.getUserNameByToken(req);
			WxStudy study = (WxStudy)redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
			if(study!=null&&study.getBanjiId()!=null&&!"".equals(study.getBanjiId())){
				String BanjiId=study.getBanjiId();
				WxBanji wxBanjiEntity = wxBanjiService.getById(BanjiId);
				if(wxBanjiEntity!=null) {
				gradeId=wxBanjiEntity.getGradeId();
				}
			}
			return gradeId;
		}
	/**
	  *   添加
	 * @param wxStory
	 * @return
	 */
	@PostMapping(value = "/add")
	@ApiOperation(value = "添加故事大会WEB")
	public Result<WxStory> add(@RequestBody WxStory wxStory) {
		Result<WxStory> result = new Result<WxStory>();
		try {
			wxStoryService.save(wxStory);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxStory
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxStory> edit(@RequestBody WxStory wxStory) {
		Result<WxStory> result = new Result<WxStory>();
		WxStory wxStoryEntity = wxStoryService.getById(wxStory.getId());
		if(wxStoryEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxStoryService.updateById(wxStory);
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
			wxStoryService.removeById(id);
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
	public Result<WxStory> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxStory> result = new Result<WxStory>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxStoryService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxStory> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxStory> result = new Result<WxStory>();
		WxStory wxStory = wxStoryService.getById(id);
		if(wxStory==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxStory);
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
  public ModelAndView exportXls(HttpServletRequest request, WxStory wxStory) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxStory> queryWrapper = QueryGenerator.initQueryWrapper(wxStory, request.getParameterMap());
      List<WxStory> pageList = wxStoryService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxStory> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "故事大会资料表列表");
      mv.addObject(NormalExcelConstants.CLASS, WxStory.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("故事大会资料表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxStory> listWxStorys = ExcelImportUtil.importExcel(file.getInputStream(), WxStory.class, params);
              wxStoryService.saveBatch(listWxStorys);
              return Result.ok("文件导入成功！数据行数:" + listWxStorys.size());
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
   * 查询已推荐故事
   * @param wxStory
   * @param pageNo
   * @param pageSize
   * @param req
   * @return
   */
  @RequestMapping(value = "/queryHaveTuijianStoryList")
	public Result<IPage<Map<String,Object>>> queryHaveTuijianStoryList(WxStory wxStory,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
		String gradeId=req.getParameter("gradeId");
		Page<Map<String,Object>> pageList =wxStoryService.queryHaveTuijianStoryList(page, wxStory, gradeId);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
  
  /**
   * 查询可以推荐故事（去掉已推荐的）
   * @param wxStory
   * @param pageNo
   * @param pageSize
   * @param req
   * @return
   */
  @RequestMapping(value = "/queryNeedTuijianStoryList")
	public Result<IPage<Map<String,Object>>> queryNeedTuijianStoryList(WxStory wxStory,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
		String gradeId=req.getParameter("gradeId");
		Page<Map<String,Object>> pageList =wxStoryService.queryNeedTuijianStoryList(page, wxStory, gradeId);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
  
  
  @GetMapping(value = "/saveStoryGradeRel")
 	public Map<String,Object> saveStoryGradeRel(
 			@RequestParam(name="gradeId") String gradeId,
 			  @RequestParam(name="storyId") String storyId,
 									  HttpServletRequest req) {
	  Map<String,Object> rtm=new HashMap<String,Object>();
	  try{
	  	  List<String> l = Arrays.asList(storyId.split(","));
	  	  for(String s:l){
	  		  if(!"".equals(s)){
	  			WxStoryGradeRel sgr=new WxStoryGradeRel();
	  			sgr.setGradeId(gradeId);
	  			sgr.setStoryId(s);
	  			wxStoryGradeRelService.save(sgr);
	  		  }
	  	  }
	  	rtm.put("code", "0");
	  }catch(Exception e){
		  	rtm.put("code", "1");
	  }
 		
 		return rtm;
 	}
  
  @GetMapping(value = "/deleteStoryGradeRel")
	public Map<String,Object> deleteStoryGradeRel(
			@RequestParam(name="gradeId") String gradeId,
			  @RequestParam(name="storyId") String storyId,
									  HttpServletRequest req) {
	  Map<String,Object> rtm=new HashMap<String,Object>();
	  try{
		  WxStoryGradeRel sgr=new WxStoryGradeRel();
			sgr.setGradeId(gradeId);
			sgr.setStoryId(storyId);
		      QueryWrapper<WxStoryGradeRel> queryWrapper = QueryGenerator.initQueryWrapper(sgr, req.getParameterMap());
			wxStoryGradeRelService.remove(queryWrapper);
	  	rtm.put("code", "0");
	  }catch(Exception e){
		  	rtm.put("code", "1");
	  }
		
		return rtm;
	}

}
