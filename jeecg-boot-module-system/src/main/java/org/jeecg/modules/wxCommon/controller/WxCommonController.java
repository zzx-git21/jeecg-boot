package org.jeecg.modules.wxCommon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.wxCommon.util.QiniuUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecg.modules.wxCommon.util.QiniuUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @Author scott
 * @since 2018-12-20
 */
@Slf4j
@RestController
@RequestMapping("/wx/common")
@Api(tags = "上传下载接口")
public class WxCommonController {

	@ApiOperation(value = "七牛上传接口")
	@PostMapping(value = "/upload")
	public Result<?> upload(HttpServletRequest request, HttpServletResponse response) {
		Result<?> result = new Result<>();
		log.info("七牛图片上传-进入方法");
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			log.info("七牛图片上传请求的multipartRequest-"+multipartRequest);
			MultipartFile mf = multipartRequest.getFile("file");// 获取上传文件对象
			log.info("七牛图片上传请求的MultipartFile-"+mf);

			String imgurl=QiniuUploadUtil.upload(mf);
			log.info("七牛图片imgurl-"+imgurl);
			result.setMessage(imgurl);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return result;
	}
}
