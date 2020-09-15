package org.jeecg.modules.wxFile.controller;

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
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysPermission;
import org.jeecg.modules.system.model.TreeSelectModel;
import org.jeecg.modules.wxFile.entity.WxFile;
import org.jeecg.modules.wxFile.entity.WxFileTree;
import org.jeecg.modules.wxFile.service.IWxFileService;
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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

 /**
 * @Description: 分类字典
 * @Author: jeecg-boot
 * @Date:   2019-05-29
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxFile/wxFile")
@Slf4j
public class WxFileController {
	@Autowired
	private IWxFileService wxFileService;
	
	/**
	  * 分页列表查询
	 * @param wxFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/rootList")
	public Result<IPage<WxFile>> queryPageList(WxFile wxFile,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		if(oConvertUtils.isEmpty(wxFile.getPid())){
			wxFile.setPid("0");
		}
		Result<IPage<WxFile>> result = new Result<IPage<WxFile>>();
		
		//--author:os_chengtgen---date:20190804 -----for: 分类字典页面显示错误,issues:377--------start
		//QueryWrapper<WxFile> queryWrapper = QueryGenerator.initQueryWrapper(wxFile, req.getParameterMap());
		QueryWrapper<WxFile> queryWrapper = new QueryWrapper<WxFile>();
		queryWrapper.eq("pid", wxFile.getPid());
		//--author:os_chengtgen---date:20190804 -----for: 分类字典页面显示错误,issues:377--------end
		
		Page<WxFile> page = new Page<WxFile>(pageNo, pageSize);
		IPage<WxFile> pageList = wxFileService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	@RequestMapping(value = "/fileTree", method = RequestMethod.GET)
	public Result<Map<String,Object>> fileTree(WxFile wxFile,HttpServletRequest req) {
		Result<Map<String,Object>> result = new Result<>();
		List<String> ids = new ArrayList<>();
		try {
			/*if(oConvertUtils.isEmpty(wxFile.getPid())){
				wxFile.setPid("0");
			}*/
			/*LambdaQueryWrapper<WxFile> query = new LambdaQueryWrapper<WxFile>();
			query.eq(WxFile::getDelFlag, CommonConstant.DEL_FLAG_0);
			query.orderByAsc(WxFile::getSortNo);*/
			QueryWrapper<WxFile> queryWrapper = QueryGenerator.initQueryWrapper(wxFile, req.getParameterMap());
		      List<WxFile> list = wxFileService.list(queryWrapper);
		      
		      for (WxFile sysPer : list) {
					ids.add(sysPer.getId());
				}
			//List<WxFile> list = wxFileService.list(query);
			List<WxFileTree> treeList = new ArrayList<>();
			getTreeList(treeList, list, null);
			
			Map<String,Object> m=new HashMap <String,Object>();
			m.put("treeList", treeList);
			m.put("ids", ids);// 全部树ids
			result.setResult(m);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	private void getTreeList(List<WxFileTree> treeList, List<WxFile> metaList, WxFileTree parent) {
		String parentId="";
		String parentPid="";
		if(parent != null){
			parentId=parent.getId();
			parentPid=parent.getParentId();
		}
		for (WxFile wxFile : metaList) {
			String childId = wxFile.getId();
			String childPid = wxFile.getPid();
			WxFileTree tree = new WxFileTree(wxFile);
			if (parent == null && "0".equals(childPid)) {
				treeList.add(tree);
				if (!tree.getIsLeaf()) {
					getTreeList(treeList, metaList, tree);
				}
			} else if (parent != null && childPid != null && parentId.equals(childPid)) {
				parent.getChildren().add(tree);
				if (!tree.getIsLeaf()) {
					getTreeList(treeList, metaList, tree);
				}
			}

		}
	}
	
	@GetMapping(value = "/childList")
	public Result<List<WxFile>> queryPageList(WxFile wxFile,HttpServletRequest req) {
		Result<List<WxFile>> result = new Result<List<WxFile>>();
		QueryWrapper<WxFile> queryWrapper = QueryGenerator.initQueryWrapper(wxFile, req.getParameterMap());
		List<WxFile> list = wxFileService.list(queryWrapper);
		result.setSuccess(true);
		result.setResult(list);
		return result;
	}
	
	
	/**
	  *   添加
	 * @param wxFile
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<WxFile> add(@RequestBody WxFile wxFile) {
		Result<WxFile> result = new Result<WxFile>();
		try {
			wxFileService.addWxFile(wxFile);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param wxFile
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<WxFile> edit(@RequestBody WxFile wxFile) {
		Result<WxFile> result = new Result<WxFile>();
		WxFile wxFileEntity = wxFileService.getById(wxFile.getId());
		if(wxFileEntity==null) {
			result.error500("未找到对应实体");
		}else {
			wxFileService.updateWxFile(wxFile);
			result.success("修改成功!");
		}
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<WxFile> delete(@RequestParam(name="id",required=true) String id) {
		Result<WxFile> result = new Result<WxFile>();
		WxFile wxFile = wxFileService.getById(id);
		if(wxFile==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = wxFileService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<WxFile> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<WxFile> result = new Result<WxFile>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.wxFileService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<WxFile> queryById(@RequestParam(name="id",required=true) String id) {
		Result<WxFile> result = new Result<WxFile>();
		WxFile wxFile = wxFileService.getById(id);
		if(wxFile==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(wxFile);
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
  public ModelAndView exportXls(HttpServletRequest request, WxFile wxFile) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<WxFile> queryWrapper = QueryGenerator.initQueryWrapper(wxFile, request.getParameterMap());
      List<WxFile> pageList = wxFileService.list(queryWrapper);
      // Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      // 过滤选中数据
      String selections = request.getParameter("selections");
      if(oConvertUtils.isEmpty(selections)) {
    	  mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  List<WxFile> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
    	  mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "分类字典列表");
      mv.addObject(NormalExcelConstants.CLASS, WxFile.class);
      LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("分类字典列表数据", "导出人:"+user.getRealname(), "导出信息"));
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
              List<WxFile> listWxFiles = ExcelImportUtil.importExcel(file.getInputStream(), WxFile.class, params);
              for (WxFile wxFileExcel : listWxFiles) {
                  wxFileService.save(wxFileExcel);
              }
              return Result.ok("文件导入成功！数据行数:" + listWxFiles.size());
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
     * 加载单个数据 用于回显
   */
    @RequestMapping(value = "/loadOne", method = RequestMethod.GET)
 	public Result<WxFile> loadOne(@RequestParam(name="field") String field,@RequestParam(name="val") String val) {
 		Result<WxFile> result = new Result<WxFile>();
 		try {
 			
 			QueryWrapper<WxFile> query = new QueryWrapper<WxFile>();
 			query.eq(field, val);
 			List<WxFile> ls = this.wxFileService.list(query);
 			if(ls==null || ls.size()==0) {
 				result.setMessage("查询无果");
 	 			result.setSuccess(false);
 			}else if(ls.size()>1) {
 				result.setMessage("查询数据异常,["+field+"]存在多个值:"+val);
 	 			result.setSuccess(false);
 			}else {
 				result.setSuccess(true);
 				result.setResult(ls.get(0));
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			result.setMessage(e.getMessage());
 			result.setSuccess(false);
 		}
 		return result;
 	}
   
    /**
          * 加载节点的子数据
     */
    @RequestMapping(value = "/loadTreeChildren", method = RequestMethod.GET)
	public Result<List<TreeSelectModel>> loadTreeChildren(@RequestParam(name="pid") String pid) {
		Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
		try {
			List<TreeSelectModel> ls = this.wxFileService.queryListByPid(pid);
			result.setResult(ls);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}
    
    /**
         * 加载一级节点/如果是同步 则所有数据
     */
    @RequestMapping(value = "/loadTreeRoot", method = RequestMethod.GET)
   	public Result<List<TreeSelectModel>> loadTreeRoot(@RequestParam(name="async") Boolean async,@RequestParam(name="pcode") String pcode) {
   		Result<List<TreeSelectModel>> result = new Result<List<TreeSelectModel>>();
   		try {
   			List<TreeSelectModel> ls = this.wxFileService.queryListByCode(pcode);
   			if(!async) {
   				loadAllFileChildren(ls);
   			}
   			result.setResult(ls);
   			result.setSuccess(true);
   		} catch (Exception e) {
   			e.printStackTrace();
   			result.setMessage(e.getMessage());
   			result.setSuccess(false);
   		}
   		return result;
   	}
  
    /**
         * 递归求子节点 同步加载用到
     */
  	private void loadAllFileChildren(List<TreeSelectModel> ls) {
  		for (TreeSelectModel tsm : ls) {
			List<TreeSelectModel> temp = this.wxFileService.queryListByPid(tsm.getKey());
			if(temp!=null && temp.size()>0) {
				tsm.setChildren(temp);
				loadAllFileChildren(temp);
			}
		}
  	}

}
