package org.jeecg.modules.wxChatMsg.controller;

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
import org.jeecg.modules.wxChatMsg.entity.WxChatDto;
import org.jeecg.modules.wxChatMsg.entity.WxChatMsg;
import org.jeecg.modules.wxChatMsg.service.IWxChatMsgService;
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

 /**
 * @Description: 消息描述表
 * @Author: jeecg-boot
 * @Date:   2020-06-11
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxChatMsg/wxChatMsg")
@Slf4j
@Api(tags = "消息发送接口")
public class WxChatMsgController {
	@Autowired
	private IWxChatMsgService wxChatMsgService;


	/**
	  * 分页列表查询
	 * @param wxChatMsg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<WxChatMsg>> queryPageList(WxChatMsg wxChatMsg,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<WxChatMsg>> result = new Result<IPage<WxChatMsg>>();
		QueryWrapper<WxChatMsg> queryWrapper = QueryGenerator.initQueryWrapper(wxChatMsg, req.getParameterMap());
		Page<WxChatMsg> page = new Page<WxChatMsg>(pageNo, pageSize);
		IPage<WxChatMsg> pageList = wxChatMsgService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	
	
	/**
	  * 分页列表查询
	 * @param wxChatMsg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/queryList")
	@ApiOperation(value = "根据id查询消息列表", notes = "author by skycc")
	public Result<IPage<WxChatMsg>> queryList(WxChatMsg wxChatMsg,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req,String fromUserid) {
		
		Result<IPage<WxChatMsg>> result = new Result<IPage<WxChatMsg>>();
		if(wxChatMsg.getToUserid()!=null) {
		Page<WxChatMsg> page = new Page<WxChatMsg>(pageNo, pageSize);
		IPage<WxChatMsg> pageList = wxChatMsgService.queryList(page, wxChatMsg);
		
		result.setSuccess(true);
		result.setResult(pageList);
		}
		return result;
	}
	
	@GetMapping(value = "/clickReadList")
	@ApiOperation(value = "阅读消息", notes = "author by skycc")
	public void clickReadList(WxChatMsg wxChatMsg,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req,String fromUserid) {
		
		
		wxChatMsgService.updateWxChatMsg(wxChatMsg);
		
	
	}
	
	/**
	  *   添加
	 * @param wxChatMsg
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxChatMsg> add(@RequestBody WxChatMsg wxChatMsg) {
		Result<WxChatMsg> result = new Result<WxChatMsg>();
		try {
			wxChatMsgService.save(wxChatMsg);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *   添加
	 * @param wxChatMsg
	 * @return
	 */
	@PostMapping(value = "/addMsg")
	@ApiOperation(value = "消息添加接口", notes = "author by skycc")
	public Result<WxChatMsg> addMsg(@RequestBody WxChatMsg wxChatMsg) {
		Result<WxChatMsg> result = new Result<WxChatMsg>();
		try {
			wxChatMsg.setIsRead("0");
			wxChatMsgService.save(wxChatMsg);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxChatMsg
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxChatMsg> edit(@RequestBody WxChatMsg wxChatMsg) {
		Result<WxChatMsg> result = new Result<WxChatMsg>();
		WxChatMsg wxChatMsgEntity = wxChatMsgService.getById(wxChatMsg.getId());
		if(wxChatMsgEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxChatMsgService.updateById(wxChatMsg);
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
			wxChatMsgService.removeById(id);
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
	public Result<WxChatMsg> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxChatMsg> result = new Result<WxChatMsg>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxChatMsgService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxChatMsg> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxChatMsg> result = new Result<WxChatMsg>();
		WxChatMsg wxChatMsg = wxChatMsgService.getById(id);
		if(wxChatMsg==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxChatMsg);
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
  public ModelAndView exportXls(HttpServletRequest request, WxChatMsg wxChatMsg) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxChatMsg> queryWrapper = QueryGenerator.initQueryWrapper(wxChatMsg, request.getParameterMap());
      List<WxChatMsg> pageList = wxChatMsgService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxChatMsg> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "消息描述表列表");
      mv.addObject(NormalExcelConstants.CLASS, WxChatMsg.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("消息描述表列表数据", "导出人:Jeecg", "导出信息"));
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
              List<WxChatMsg> listWxChatMsgs = ExcelImportUtil.importExcel(file.getInputStream(), WxChatMsg.class, params);
              wxChatMsgService.saveBatch(listWxChatMsgs);
              return Result.ok("文件导入成功！数据行数:" + listWxChatMsgs.size());
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
