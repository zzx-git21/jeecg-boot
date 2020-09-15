package org.jeecg.modules.wxBanji.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import org.jeecg.modules.common.service.CommonService;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.service.ISysUserRoleService;
import org.jeecg.modules.system.service.ISysUserService;
import org.jeecg.modules.wxBanji.entity.WxBanji;
import org.jeecg.modules.wxBanji.service.IWxBanjiService;
import org.jeecg.modules.wxChatMsg.entity.WxChatDto;
import org.jeecg.modules.wxChatMsg.entity.WxChatMsg;
import org.jeecg.modules.wxChatMsg.service.IWxChatMsgService;
import org.jeecg.modules.wxKebiao.entity.WxKebiao;
import org.jeecg.modules.wxKebiao.service.IWxKebiaoService;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecg.modules.wxStudy.service.IWxStudyService;
import org.jeecg.modules.wxXiaoqu.entity.WxXiaoqu;
import org.jeecg.modules.wxXiaoqu.service.IWxXiaoquService;
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
 * @Description: 班级管理
 * @Author: jeecg-boot
 * @Date:   2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxBanji/wxBanji")
@Slf4j
@Api(tags = "班级接口")
public class WxBanjiController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private IWxXiaoquService wxXiaoquService;
	@Autowired
	private IWxBanjiService wxBanjiService;
	@Autowired
	 private RedisUtil redisUtil;
	@Autowired
	private IWxKebiaoService wxKebiaoService;
	@Autowired
	private IWxStudyService wxStudyService;
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private IWxChatMsgService wxChatMsgService;
	/**
	  * 分页列表查询
	 * @param wxBanji
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@ApiOperation(value = "查询 班级列表")
	public Result<IPage<WxBanji>> queryPageList(WxBanji wxBanji,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxBanji>> result = new Result<IPage<WxBanji>>();
		QueryWrapper<WxBanji> queryWrapper = new QueryWrapper<WxBanji>();//QueryGenerator.initQueryWrapper(wxBanji, req.getParameterMap());
		String banjiName = req.getParameter("banjiName");
		if(oConvertUtils.isNotEmpty(banjiName)) {
			queryWrapper.like("banji_name", banjiName);
		}
		String owernShcool = req.getParameter("owernShcool");
		if(oConvertUtils.isNotEmpty(owernShcool)) {
			queryWrapper.like("owern_shcool",owernShcool);
		}
		
		String username = JwtUtil.getUserNameByToken(req);
        SysUser sysUser = sysUserService.getUserByName(username);

		if(commonService.checkRole(username,CommonConstant.ROLE_TEACHER)||commonService.checkRole(username,CommonConstant.ROLE_BZR)){
			queryWrapper.eq("teacher", sysUser.getId()).or().eq("bzr", sysUser.getId());
		}
		
		Page<WxBanji> page = new Page<WxBanji>(pageNo, pageSize);
		IPage<WxBanji> pageList = wxBanjiService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@GetMapping(value = "/banjiTree")
	@ApiOperation(value = "查询 班级列表")
	public List<Map<String,Object>> banjiTree(HttpServletRequest req) {
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		List<WxXiaoqu> xiaoquList=wxXiaoquService.list();
		for(WxXiaoqu xiaoqu:xiaoquList ){
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("key", "xq"+xiaoqu.getId());
			m.put("title", xiaoqu.getXiaoquName());
			m.put("children", getBanjiByXiaoqu(xiaoqu.getId(),req));
			l.add(m);
		}
		//wxXiaoquService
		/*Map<String,Object> m1=new HashMap<String,Object>();
		m1.put("key", "xq1");
		m1.put("title", "合肥天鹅湖校区");
		m1.put("children", getBanjiByXiaoqu("1",req));
		Map<String,Object> m2=new HashMap<String,Object>();
		m2.put("key", "xq2");
		m2.put("title", "合肥农大校区");
		m2.put("children", getBanjiByXiaoqu("2",req));
		l.add(m1);
		l.add(m2);*/
		return l;
	}
	
	public List<Map<String,Object>> getBanjiByXiaoqu(String xiaoqu,HttpServletRequest req){
		
		String username = JwtUtil.getUserNameByToken(req);
        SysUser sysUser = sysUserService.getUserByName(username);
		
		WxBanji bj=new WxBanji();
		bj.setOwernShcool(xiaoqu);
		
		
		
		QueryWrapper<WxBanji> queryWrapper = QueryGenerator.initQueryWrapper(bj, req.getParameterMap());
		if(commonService.checkRole(username,CommonConstant.ROLE_TEACHER)||commonService.checkRole(username,CommonConstant.ROLE_BZR)){
			queryWrapper.eq("teacher", sysUser.getId()).or().eq("bzr", sysUser.getId());
		}
		
		List<Map<String,Object>>  bjList=wxBanjiService.listMaps(queryWrapper);
		for(Map<String,Object> banji:bjList){
			if(banji.get("banji_name")!=null){
				banji.put("title", banji.get("banji_name").toString());

			}else{
				banji.put("title", "");
			}
			banji.put("key", banji.get("id").toString());
		}
		return bjList;
	}
	
	@GetMapping(value = "/queryList")
	@ApiOperation(value = "查询班级列表APP")
	public Result<IPage<WxBanji>> queryList(WxBanji wxBanji,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxBanji>> result = new Result<IPage<WxBanji>>();	
				QueryWrapper<WxBanji> queryWrapper1 = new QueryWrapper<>();
				List<WxBanji> banjiList =getUserBanjiInfoByReq(req);
				if(banjiList.size()>0){
					for(WxBanji banji :banjiList){
						 queryWrapper1.lambda().or(obj -> obj.eq(WxBanji::getId, banji.getId()));
					}
					Page<WxBanji> page = new Page<WxBanji>(pageNo, pageSize);
					IPage<WxBanji> pageList = wxBanjiService.page(page, queryWrapper1);
					result.setSuccess(true);
					result.setResult(pageList);
				}else{
					result.setSuccess(true);
					result.setResult(null);
				}
		
		return result;
	}
	public List<WxBanji> getUserBanjiInfoByReq( HttpServletRequest req){
		List<WxBanji> banjiList=new ArrayList<WxBanji>();
		String username = JwtUtil.getUserNameByToken(req);
		SysUser sysUser = (SysUser)redisUtil.get(CommonConstant.USER_INFO + username);//用户信息
		List<SysUserRole> userRoleList = sysUserRoleService
				.list(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, sysUser.getId()));// 获取角色信息		
		for (SysUserRole sysUserRole : userRoleList) {
			String roleId=sysUserRole.getRoleId();
			if(roleId.equals(CommonConstant.ROLE_STUDENT)){//学生
				WxStudy study = wxStudyService.getOne(new QueryWrapper<WxStudy>().lambda().eq(WxStudy::getUserId, sysUser.getId()));
				if(study!=null&&study.getBanjiId()!=null&&!("").equals(study.getBanjiId())){
				WxBanji wxBanjiStudent = wxBanjiService.getById(study.getBanjiId());
				if(wxBanjiStudent!=null){
					banjiList.add(wxBanjiStudent);
				}
				}
			}else if(roleId.equals(CommonConstant.ROLE_TEACHER)){//教师
				List<WxBanji> banjiListForTeacher = wxBanjiService.list(new QueryWrapper<WxBanji>().lambda().eq(WxBanji::getTeacher, sysUser.getId()));
				if(banjiListForTeacher.size()>0){
					banjiList.addAll(banjiListForTeacher);
				}
			}else if(roleId.equals(CommonConstant.ROLE_BZR)){//班主任
				List<WxBanji> banjiListForBZR = wxBanjiService.list(new QueryWrapper<WxBanji>().lambda().eq(WxBanji::getBzr, sysUser.getId()));
				if(banjiListForBZR.size()>0){
					banjiList.addAll(banjiListForBZR);
				}
			}
		}	
		return banjiList;
	}
	//学生获取班级Id
	public String getBanjiId(HttpServletRequest req){
		String gradeId="";
		String username = JwtUtil.getUserNameByToken(req);
		WxStudy study = (WxStudy)redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
		if(study!=null){
			gradeId=study.getBanjiId();
		}
		return gradeId;
	}
	/**
	  *   添加
	 * @param wxBanji
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxBanji> add(@RequestBody WxBanji wxBanji) {
		Result<WxBanji> result = new Result<WxBanji>();
		try {
			wxBanjiService.save(wxBanji);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxBanji
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxBanji> edit(@RequestBody WxBanji wxBanji) {
		Result<WxBanji> result = new Result<WxBanji>();
		WxBanji wxBanjiEntity = wxBanjiService.getById(wxBanji.getId());
		if(wxBanjiEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxBanjiService.updateById(wxBanji);
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
			wxBanjiService.removeById(id);
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
	public Result<WxBanji> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxBanji> result = new Result<WxBanji>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxBanjiService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxBanji> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxBanji> result = new Result<WxBanji>();
		WxBanji wxBanji = wxBanjiService.getById(id);
		if(wxBanji==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxBanji);
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
  public ModelAndView exportXls(HttpServletRequest request, WxBanji wxBanji) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxBanji> queryWrapper = QueryGenerator.initQueryWrapper(wxBanji, request.getParameterMap());
      List<WxBanji> pageList = wxBanjiService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxBanji> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "班级管理列表");
      mv.addObject(NormalExcelConstants.CLASS, WxBanji.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("班级管理列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxBanji> listWxBanjis = ExcelImportUtil.importExcel(file.getInputStream(), WxBanji.class, params);
              wxBanjiService.saveBatch(listWxBanjis);
              return Result.ok("文件导入成功！数据行数:" + listWxBanjis.size());
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
  
  //生成课程
  @GetMapping("/generateCourse")
	public Map<String,String> generateCourse(HttpServletRequest request, 
			@RequestParam(name = "banjiId", required = false) String banjiId
			) {
		Map<String,String> rtn=new HashMap<String,String>();
		try{
			WxBanji wxBanji = wxBanjiService.getById(banjiId);
			if(wxBanji!=null){
				//生成课表前，删除之前课表
				QueryWrapper<WxKebiao> queryWrapper = new QueryWrapper<WxKebiao>();
				queryWrapper.eq("banji_code",banjiId);
			      wxKebiaoService.remove(queryWrapper);

			      
			  	long coureTime =wxBanji.getCourseTime().longValue();// 课次
				String coureTimes = wxBanji.getOneDayCourseNum();// 每次课时
				
				BigDecimal num1 = new BigDecimal(coureTime);
				BigDecimal num2 = new BigDecimal(coureTimes);
				BigDecimal totalCourseTimes = num1.multiply(num2);//总课时
				
			  SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			  String startStr=sdf.format(wxBanji.getStartTime()) ;
			  //开始时间
			  Date startDate =sdf.parse(startStr);
			  Calendar startCalendar = Calendar.getInstance();
			  startCalendar.setTime(startDate);
			  //结束时间
			  Calendar endCalendar = Calendar.getInstance();
			  Date newDate = addDate(startDate, (coureTime-1)*7);
			  endCalendar.setTime(newDate);
			//开课小时
			  String courseStartTime=wxBanji.getOneDayStart()+":00";
			  SimpleDateFormat sdfHoure= new SimpleDateFormat("HH:mm:ss");
			  Date courseStartTimeDate =sdfHoure.parse(courseStartTime);
			  Calendar ourseStartTimeCalendar = Calendar.getInstance();
			  ourseStartTimeCalendar.setTime(courseStartTimeDate);
			  BigDecimal endTime = num2.multiply(new BigDecimal(40))
						.add((num2.subtract(new BigDecimal(1)).multiply(new BigDecimal(10))));
			     int endTimeTemp=endTime.intValue();
			  ourseStartTimeCalendar.add(Calendar.MINUTE, endTimeTemp);
			  Date coureEndtime =ourseStartTimeCalendar.getTime();
			String endDate=  sdfHoure.format(coureEndtime);
			  System.out.println(coureEndtime);
			  int i=0;
			  Date nowTime=new Date();
			  //String nowTime=sdf.format(new Date());
			  while(!startCalendar.after(endCalendar)){
			      Date time = startCalendar.getTime();  
			      //保存课表
			      WxKebiao wxKebiao=new WxKebiao();
			      wxKebiao.setBanjiCode(banjiId);
			      wxKebiao.setKeshiStatus(1);
			      wxKebiao.setTotalCourseTime(totalCourseTimes.toString());
			      wxKebiao.setKaoqingStatus(1);
			      wxKebiao.setCreateTime(nowTime);
			     // wxKebiao.setCreateBy(nowTime);
			      wxKebiao.setZhouji(wxBanji.getOneDayWeek());
			      wxKebiao.setCourseTime(wxBanji.getOneDayCourseNum());
			      wxKebiao.setCourseTimes(coureTimes);
			      wxKebiao.setCourseStartTime(wxBanji.getOneDayStart());
			      wxKebiao.setCourseEndTime(endDate);
			      wxKebiao.setTeacherName(wxBanji.getTeacherName());
			      wxKebiao.setBzrName(wxBanji.getBzrName());
			      i+=1;
			      wxKebiao.setCourseName("第"+i+"次课");
			      wxKebiao.setStartTime(time);
			      wxKebiao.setEndTime(endCalendar.getTime());
			      String preDay = sdf.format(time); 
			      System.out.println(banjiId+"生成课表"+preDay);
			      wxKebiaoService.save(wxKebiao);
			     startCalendar.add(Calendar.DATE, 7);   
			  }
			  rtn.put("code", "1");
			}else{
				 rtn.put("code", "1");
				 rtn.put("msg", "异常");
			}
		}catch(Exception e){
				rtn.put("code", "0");
		}
		return rtn;
	}
  
  public static Date addDate(Date date,long day) throws ParseException {
	  long time = date.getTime(); // 得到指定日期的毫秒数
	  day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
	  time+=day; // 相加得到新的毫秒数
	  return new Date(time); // 将毫秒数转换成日期
	 }
  public static void main(String[] args) throws ParseException{
//	  String startStr="2012-5-27";
//	  String endStr="2015-5-24";
	  SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//	  //开始时间
//	  Date startDate =sdf.parse(startStr);
//	  Calendar startCalendar = Calendar.getInstance();
//	  startCalendar.setTime(startDate);
//	  //结束时间
//	  Date endDate =sdf.parse(endStr);
//	  Calendar endCalendar = Calendar.getInstance();
//	  endCalendar.setTime(endDate);
//	  
//	  while(!startCalendar.after(endCalendar)){
//	     // startCalendar.add(Calendar.DATE, 7);           
//	      Date time = startCalendar.getTime();         
//	      String preDay = sdf.format(time); 
//	      System.out.println(preDay);
//	     startCalendar.add(Calendar.DATE, 7);   
//	  }
//	  
	  
	 /*    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");                

      Calendar c=Calendar.getInstance(); 
      System.out.println("-------After 7 Days----------");
      c.add(Calendar.DATE, 7);           

      Date time = c.getTime();         
      
      String preDay = sdf.format(time); */
    // System.out.println();
	  
	  
	  String courseStartTime="12:00:12";
	  SimpleDateFormat sdfHoure= new SimpleDateFormat("HH:mm:ss");
	  Date courseStartTimeDate =sdfHoure.parse(courseStartTime);
	  Calendar ourseStartTimeCalendar = Calendar.getInstance();
	  ourseStartTimeCalendar.setTime(courseStartTimeDate);
	  ourseStartTimeCalendar.add(Calendar.MINUTE, 80);
	  Date coureEndtime =ourseStartTimeCalendar.getTime();
	  System.out.println(sdfHoure.format(coureEndtime));
  }
  @ApiOperation(value = "查询同班同学列表APP", notes = "author by skycc")
	@GetMapping(value = "/queryBybanjiId")
	public Result<List<WxChatDto>> queryBybanjiId(HttpServletRequest req,String toUserId) {
		List<WxBanji> banjiList = getUserBanjiInfoByReq(req);
		QueryWrapper<WxStudy> queryWrapper = new QueryWrapper<WxStudy>();
		List<WxChatDto> list2 = new ArrayList<WxChatDto>();
		Result<List<WxChatDto>> result = new Result<List<WxChatDto>>();
		if (banjiList.size() > 0) {
			int i=0;
			for (WxBanji banji : banjiList) {
				queryWrapper.eq("banji_id", banji.getId());
				List<WxStudy> studyList = wxStudyService.list(queryWrapper);// 获取到学生列表
				WxChatMsg wxChatMsg = new WxChatMsg();
				wxChatMsg.setToUserid(toUserId);
				wxChatMsg.setIsRead("0");
				if(i==0) {
				WxStudy wxDto2 = new WxStudy();
				wxDto2.setStudyName(banji.getBzrName());
				wxDto2.setUserId(banji.getBzr());
				wxDto2.setBanjiId(banji.getId());
				studyList.add(wxDto2);
				
				wxDto2 = new WxStudy();
				wxDto2.setStudyName(banji.getTeacherName());
				wxDto2.setUserId(banji.getTeacher());
				wxDto2.setBanjiId(banji.getId());
				studyList.add(wxDto2);
				}
				i++;
				List<WxChatMsg> chatList = wxChatMsgService.chatlist(wxChatMsg);// 获取到消息列表
				for (WxStudy study : studyList) {
					WxChatDto wxDto = new WxChatDto();
					for (WxChatMsg wxChatMsg2 : chatList) {
						if (study.getUserId() == wxChatMsg2.getFromUserid() && wxChatMsg2.getIsRead().equals("0")) {
							wxDto.setIsRead("0");// 有未读消息
						}
					}
					wxDto.setBanjiId(study.getBanjiId());
					wxDto.setStudyName(study.getStudyName());
					wxDto.setUserId(study.getUserId());
					list2.add(wxDto);
				}
			}
		}
		result.setResult(list2);
		return result;
	}

}
