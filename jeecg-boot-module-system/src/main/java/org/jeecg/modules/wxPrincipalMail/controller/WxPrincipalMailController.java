package org.jeecg.modules.wxPrincipalMail.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxPrincipalMail.entity.WxPrincipalMail;
import org.jeecg.modules.wxPrincipalMail.service.IWxPrincipalMailService;

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

/**
 * @Description: 校长信箱表
 * @Author: jeecg-boot
 * @Date: 2020-08-08
 * @Version: V1.0
 */
@ApiOperation(value = "邮箱接口", notes = "author by skycc")
@RestController
@RequestMapping("/wxPrincipalMail/wxPrincipalMail")
@Slf4j
public class WxPrincipalMailController {
	@Autowired
	private IWxPrincipalMailService wxPrincipalMailService;

	/**
	 * 分页列表查询
	 * 
	 * @param wxPrincipalMail
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WxPrincipalMail>> queryPageList(WxPrincipalMail wxPrincipalMail,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
		Result<IPage<WxPrincipalMail>> result = new Result<IPage<WxPrincipalMail>>();
		QueryWrapper<WxPrincipalMail> queryWrapper = QueryGenerator.initQueryWrapper(wxPrincipalMail,
				req.getParameterMap());
		Page<WxPrincipalMail> page = new Page<WxPrincipalMail>(pageNo, pageSize);
		IPage<WxPrincipalMail> pageList = wxPrincipalMailService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * 
	 * @param wxPrincipalMail
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxPrincipalMail> add(@RequestBody WxPrincipalMail wxPrincipalMail) {
		Result<WxPrincipalMail> result = new Result<WxPrincipalMail>();
		try {
			wxPrincipalMailService.save(wxPrincipalMail);
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
	 * @param wxPrincipalMail
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxPrincipalMail> edit(@RequestBody WxPrincipalMail wxPrincipalMail) {
		Result<WxPrincipalMail> result = new Result<WxPrincipalMail>();
		WxPrincipalMail wxPrincipalMailEntity = wxPrincipalMailService.getById(wxPrincipalMail.getId());
		if (wxPrincipalMailEntity == null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = wxPrincipalMailService.updateById(wxPrincipalMail);
			// TODO 返回false说明什么？
			if (ok) {
				result.success("修改成功!");
			}
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
			wxPrincipalMailService.removeById(id);
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
	public Result<WxPrincipalMail> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		Result<WxPrincipalMail> result = new Result<WxPrincipalMail>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		} else {
			this.wxPrincipalMailService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxPrincipalMail> queryById(@RequestParam(name = "id", required = true) String id) {
		Result<WxPrincipalMail> result = new Result<WxPrincipalMail>();
		WxPrincipalMail wxPrincipalMail = wxPrincipalMailService.getById(id);
		if (wxPrincipalMail == null) {
			result.error500("未找到对应实体");
		} else {
			result.setResult(wxPrincipalMail);
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
	public ModelAndView exportXls(HttpServletRequest request, WxPrincipalMail wxPrincipalMail) {
		// Step.1 组装查询条件查询数据
		QueryWrapper<WxPrincipalMail> queryWrapper = QueryGenerator.initQueryWrapper(wxPrincipalMail,
				request.getParameterMap());
		List<WxPrincipalMail> pageList = wxPrincipalMailService.list(queryWrapper);
		// Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		// 过滤选中数据
		String selections = request.getParameter("selections");
		if (oConvertUtils.isEmpty(selections)) {
			mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		} else {
			List<String> selectionList = Arrays.asList(selections.split(","));
			List<WxPrincipalMail> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId()))
					.collect(Collectors.toList());
			mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		}
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "校长信箱表列表");
		mv.addObject(NormalExcelConstants.CLASS, WxPrincipalMail.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("校长信箱表列表数据", "导出人:Jeecg", "导出信息"));
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
				List<WxPrincipalMail> listWxPrincipalMails = ExcelImportUtil.importExcel(file.getInputStream(),
						WxPrincipalMail.class, params);
				wxPrincipalMailService.saveBatch(listWxPrincipalMails);
				return Result.ok("文件导入成功！数据行数:" + listWxPrincipalMails.size());
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

	/**
	 * 添加
	 * 
	 * @param wxChatMsg
	 * @return
	 */
	@PostMapping(value = "/addMsg")
	@ApiOperation(value = "消息添加接口", notes = "author by skycc")
	public Result<WxPrincipalMail> addMsg(@RequestBody WxPrincipalMail wxChatMsg) {
		Result<WxPrincipalMail> result = new Result<WxPrincipalMail>();
		try {
			wxChatMsg.setReplayFlag("0");// 未回复
			wxPrincipalMailService.save(wxChatMsg);
			result.success("邮件发送成功！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.error500("发送失败");
		}
		return result;
	}

	@ApiOperation(value = "查询自己邮箱消息", notes = "author by skycc")
	@GetMapping(value = "/queryByUserId")
	public Result<List<WxPrincipalMail>> queryBybanjiId(HttpServletRequest req, String toUserId) {
		Result<List<WxPrincipalMail>> result = new Result<List<WxPrincipalMail>>();
		WxPrincipalMail wx = new WxPrincipalMail();
		wx.setToUserId(toUserId);
		List<WxPrincipalMail> list = wxPrincipalMailService.maillist(wx);
		result.setResult(list);
		result.setSuccess(true);
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/detailMailById")
	@ApiOperation(value = "根据id查询消息详情", notes = "author by skycc")
	public Result<WxPrincipalMail> queryDetailById(@RequestParam(name="id",required=true) String id) {
		Result<WxPrincipalMail> result = new Result<WxPrincipalMail>();
		WxPrincipalMail wxChatMsg = wxPrincipalMailService.getById(id);
		if(wxChatMsg==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxChatMsg);
			result.setSuccess(true);
		}
		return result;
	}

}
