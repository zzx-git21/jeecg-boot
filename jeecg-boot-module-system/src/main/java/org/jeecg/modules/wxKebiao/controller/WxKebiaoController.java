package org.jeecg.modules.wxKebiao.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxKebiao.entity.WxKebiao;
import org.jeecg.modules.wxKebiao.service.IWxKebiaoService;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecg.modules.wxStudy.service.IWxStudyService;

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
 * @Description: 课表
 * @Author: jeecg-boot
 * @Date: 2019-10-20
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxKebiao/wxKebiao")
@Slf4j
@Api(tags = "课表接口")
public class WxKebiaoController {
	@Autowired
	private IWxKebiaoService wxKebiaoService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IWxStudyService wxStudyService;
	/**
	 * 分页列表查询
	 * 
	 * @param wxKebiao
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@ApiOperation(value = "查询课表列表WEB")
	public Result<IPage<WxKebiao>> queryPageList(WxKebiao wxKebiao,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
		Result<IPage<WxKebiao>> result = new Result<IPage<WxKebiao>>();
		QueryWrapper<WxKebiao> queryWrapper = QueryGenerator.initQueryWrapper(wxKebiao, req.getParameterMap());
		queryWrapper.orderByAsc("start_time");
		Page<WxKebiao> page = new Page<WxKebiao>(pageNo, pageSize);
		IPage<WxKebiao> pageList = wxKebiaoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;

	}

	@GetMapping(value = "/queryKeStudyList")
	@ApiOperation(value = "查询上课学生列表")
	public Result<IPage<WxKebiao>> queryList(WxKebiao wxKebiao,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize, HttpServletRequest req) {
		Result<IPage<WxKebiao>> result = new Result<IPage<WxKebiao>>();
		QueryWrapper<WxKebiao> queryWrapper = QueryGenerator.initQueryWrapper(wxKebiao, req.getParameterMap());
		queryWrapper.orderByAsc("start_time");
		Page<WxKebiao> page = new Page<WxKebiao>(pageNo, pageSize);

		IPage<WxKebiao> pageList = wxKebiaoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	@GetMapping(value = "/queryKeBiaoByMoth")
	@ApiOperation(value = "按月查询")
	public Result<List<Map<String, Object>>> queryKeBiaoByMoth(String banjiCode,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize, HttpServletRequest req) {
		Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
		List<Map<String, Object>> list = wxKebiaoService.getByMoth(banjiCode, null);
		result.setSuccess(true);
		result.setResult(list);
		return result;
	}
	
	@GetMapping(value = "/queryStudyByBanJiCode")
	@ApiOperation(value = "根据班级code查询学生")
	public Result<List<Map<String, Object>>> queryStudyByBanJiCode(String banjiCode,String courseId, HttpServletRequest req) {
		Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
		List<Map<String, Object>> list=	 wxKebiaoService.getListStudy(banjiCode, courseId);
		result.setSuccess(true);
		result.setResult(list);
		return result;
	}
	@GetMapping(value = "/queryKeBiaoDetail")
	@ApiOperation(value = "根据月份和班级code查询课程详情")
	public Result<List<Map<String, Object>>> queryKeBiaoDetail(String months,String banjiCode,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize, HttpServletRequest req) {
		Result<List<Map<String, Object>>> result = new Result<List<Map<String, Object>>>();
		List<Map<String, Object>> list = wxKebiaoService.getByDetailMoth(banjiCode, months);
		result.setSuccess(true);
		result.setResult(list);
		return result;
	}

	@GetMapping(value = "/queryHasList")
	@ApiOperation(value = "查询已学课表列表APP")
	public Result<IPage<WxKebiao>> queryHasList(WxKebiao wxKebiao,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize, HttpServletRequest req) {
		String username = JwtUtil.getUserNameByToken(req);
		WxStudy student = (WxStudy) redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
		wxKebiao.setBanjiCode(student.getBanjiId());
		Result<IPage<WxKebiao>> result = new Result<IPage<WxKebiao>>();
		QueryWrapper<WxKebiao> queryWrapper = QueryGenerator.initQueryWrapper(wxKebiao, req.getParameterMap());
		queryWrapper.orderByAsc("start_time");
		Page<WxKebiao> page = new Page<WxKebiao>(pageNo, pageSize);
		IPage<WxKebiao> pageList = wxKebiaoService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * 
	 * @param wxKebiao
	 * @return
	 * @throws ParseException
	 */
	@PostMapping(value = "/add")
	public Result<WxKebiao> add(@RequestBody WxKebiao wxKebiao) throws ParseException {
		BigDecimal num1 = new BigDecimal(wxKebiao.getCourseTimes());
		BigDecimal endTime = num1.multiply(new BigDecimal(40))
				.add(num1.subtract(new BigDecimal(1)).multiply(new BigDecimal(10)));
		int endTimeTemp = endTime.intValue();
		// 开课小时
		String courseStartTime = wxKebiao.getCourseStartTime() + ":00";
		SimpleDateFormat sdfHoure = new SimpleDateFormat("HH:mm:ss");
		Date courseStartTimeDate = sdfHoure.parse(courseStartTime);
		Calendar ourseStartTimeCalendar = Calendar.getInstance();
		ourseStartTimeCalendar.setTime(courseStartTimeDate);
		ourseStartTimeCalendar.add(Calendar.MINUTE, endTimeTemp);
		Date coureEndtime = ourseStartTimeCalendar.getTime();
		String endDate = sdfHoure.format(coureEndtime);
		System.out.println(coureEndtime);
		wxKebiao.setCourseEndTime(endDate);

		Result<WxKebiao> result = new Result<WxKebiao>();
		try {
			wxKebiaoService.save(wxKebiao);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 编辑
	 * 
	 * @param wxKebiao
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxKebiao> edit(@RequestBody WxKebiao wxKebiao, HttpServletRequest req) {

		Result<WxKebiao> result = new Result<WxKebiao>();
		WxKebiao wxKebiaoEntity = wxKebiaoService.getById(wxKebiao.getId());
		if (wxKebiaoEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = wxKebiaoService.updateById(wxKebiao);
			// TODO 返回false说明什么？
			if (ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}

	/**
	 * 编辑
	 * 
	 * @param wxKebiao
	 * @return
	 */
	@GetMapping(value = "/edit1")
	public Result<WxKebiao> edit1(String banjiCode) {

		WxKebiao wxKebiao = new WxKebiao();
		wxKebiao.setBanjiCode(banjiCode);
		Result<WxKebiao> result = new Result<WxKebiao>();
		QueryWrapper<WxKebiao> queryWrapper = QueryGenerator.initQueryWrapper(wxKebiao, null);
		List<WxKebiao> wxKebiaoEntity = wxKebiaoService.list(queryWrapper);
		try {
			if (wxKebiaoEntity == null) {
				result.error500("未找到对应实体");
			} else {
				result.setResult(wxKebiaoEntity.get(0));
			}
		} catch (Exception e) {
			result.setResult(new WxKebiao());
		}
		return result;
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		try {
			wxKebiaoService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败", e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxKebiao> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<WxKebiao> result = new Result<WxKebiao>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.wxKebiaoService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	 * 通过id查询
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<WxKebiao> queryById(@RequestParam(name = "id", required = true) String id) {
		Result<WxKebiao> result = new Result<WxKebiao>();
		WxKebiao wxKebiao = wxKebiaoService.getById(id);
		if (wxKebiao == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(wxKebiao);
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
	public ModelAndView exportXls(HttpServletRequest request, WxKebiao wxKebiao) {
		// Step.1 组装查询条件查询数据
		QueryWrapper<WxKebiao> queryWrapper = QueryGenerator.initQueryWrapper(wxKebiao, request.getParameterMap());
		List<WxKebiao> pageList = wxKebiaoService.list(queryWrapper);
		// Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		// 过滤选中数据
		String selections = request.getParameter("selections");
		if (oConvertUtils.isEmpty(selections)) {
			mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		} else {
			List<String> selectionList = Arrays.asList(selections.split(","));
			List<WxKebiao> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId()))
					.collect(Collectors.toList());
			mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		}
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "课表列表");
		mv.addObject(NormalExcelConstants.CLASS, WxKebiao.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("课表列表数据", "导出人:Jeecg", "导出信息"));
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
				List<WxKebiao> listWxKebiaos = ExcelImportUtil.importExcel(file.getInputStream(), WxKebiao.class,
						params);
				wxKebiaoService.saveBatch(listWxKebiaos);
				return Result.ok("文件导入成功！数据行数:" + listWxKebiaos.size());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return Result.error("文件导入失败:" + e.getMessage());
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
