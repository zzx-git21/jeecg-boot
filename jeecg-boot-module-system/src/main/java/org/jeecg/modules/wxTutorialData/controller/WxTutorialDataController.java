package org.jeecg.modules.wxTutorialData.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.wxBook.entity.WxBook;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecg.modules.wxTutorialData.entity.WxTutorialData;
import org.jeecg.modules.wxTutorialData.model.TutorialParam;
import org.jeecg.modules.wxTutorialData.service.IWxTutorialDataService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @Description: 活动用户辅导视频资料表
 * @Author: jeecg-boot
 * @Date: 2019-12-20
 * @Version: V1.0
 */
@Api(tags = "活动辅导视频")
@RestController
@RequestMapping("/wxTutorialData/wxTutorialData")
@Slf4j
public class WxTutorialDataController {
    @Autowired
    private IWxTutorialDataService wxTutorialDataService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "查询辅导视频资料listAPP" , notes = "author by caofei")
    @GetMapping(value = "queryList")
    public Result<IPage<WxTutorialData>> queryList(@ApiParam("资料名称") @RequestParam(name = "dataName", defaultValue = "") String dataName,
                                                   @ApiParam("查询推荐 0.全部 1.推荐") @RequestParam(name = "recommend", defaultValue = "0") Integer recommend,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        String username = JwtUtil.getUserNameByToken(req);
        WxStudy student = (WxStudy) redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
		Page<WxTutorialData> page = new Page<WxTutorialData>(pageNo, pageSize);
		IPage<WxTutorialData> pageList = wxTutorialDataService.queryList(page, student.getNianji(), dataName, recommend);
		Result<IPage<WxTutorialData>> result = new Result<IPage<WxTutorialData>>();
		for (WxTutorialData data : pageList.getRecords()){
            data.setDataPic(CommonConstant.SHOW_IMG + data.getDataPic());
        }
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
    }

    /**
     * 分页列表查询
     *
     * @param wxTutorialData
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
    public Result<IPage<WxTutorialData>> queryPageList(WxTutorialData wxTutorialData,
                                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                       HttpServletRequest req) {
        Result<IPage<WxTutorialData>> result = new Result<IPage<WxTutorialData>>();
        QueryWrapper<WxTutorialData> queryWrapper = QueryGenerator.initQueryWrapper(wxTutorialData, req.getParameterMap());
        Page<WxTutorialData> page = new Page<WxTutorialData>(pageNo, pageSize);
        IPage<WxTutorialData> pageList = wxTutorialDataService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param wxTutorialData
     * @return
     */
    @PostMapping(value = "/add")
    public Result<WxTutorialData> add(@RequestBody WxTutorialData wxTutorialData) {
        Result<WxTutorialData> result = new Result<WxTutorialData>();
        try {
            wxTutorialDataService.save(wxTutorialData);
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
     * @param wxTutorialData
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<WxTutorialData> edit(@RequestBody WxTutorialData wxTutorialData) {
        Result<WxTutorialData> result = new Result<WxTutorialData>();
        WxTutorialData wxTutorialDataEntity = wxTutorialDataService.getById(wxTutorialData.getId());
        if (wxTutorialDataEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = wxTutorialDataService.updateById(wxTutorialData);
            //TODO 返回false说明什么？
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
            wxTutorialDataService.removeById(id);
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
    public Result<WxTutorialData> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<WxTutorialData> result = new Result<WxTutorialData>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.wxTutorialDataService.removeByIds(Arrays.asList(ids.split(",")));
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
    public Result<WxTutorialData> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<WxTutorialData> result = new Result<WxTutorialData>();
        WxTutorialData wxTutorialData = wxTutorialDataService.getById(id);
        if (wxTutorialData == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(wxTutorialData);
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
    public ModelAndView exportXls(HttpServletRequest request, WxTutorialData wxTutorialData) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<WxTutorialData> queryWrapper = QueryGenerator.initQueryWrapper(wxTutorialData, request.getParameterMap());
        List<WxTutorialData> pageList = wxTutorialDataService.list(queryWrapper);
        // Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        // 过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isEmpty(selections)) {
            mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            List<WxTutorialData> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
            mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "活动用户辅导视频资料表列表");
        mv.addObject(NormalExcelConstants.CLASS, WxTutorialData.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("活动用户辅导视频资料表列表数据", "导出人:Jeecg", "导出信息"));
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
                List<WxTutorialData> listWxTutorialDatas = ExcelImportUtil.importExcel(file.getInputStream(), WxTutorialData.class, params);
                wxTutorialDataService.saveBatch(listWxTutorialDatas);
                return Result.ok("文件导入成功！数据行数:" + listWxTutorialDatas.size());
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
