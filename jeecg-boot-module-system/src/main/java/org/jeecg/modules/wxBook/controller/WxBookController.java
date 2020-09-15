package org.jeecg.modules.wxBook.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiParam;
import org.jeecg.common.IDParam;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.jeecg.modules.wxBanji.entity.WxBanji;
import org.jeecg.modules.wxBanji.service.IWxBanjiService;
import org.jeecg.modules.wxBook.entity.WxBook;
import org.jeecg.modules.wxBook.entity.WxBookRegRel;
import org.jeecg.modules.wxBook.model.BookRegRelVO;
import org.jeecg.modules.wxBook.model.BookVO;
import org.jeecg.modules.wxBook.model.BorrowBookVO;
import org.jeecg.modules.wxBook.model.ReturnBookParam;
import org.jeecg.modules.wxBook.service.IWxBookService;
import org.jeecg.modules.wxBookGradeRel.entity.WxBookGradeRel;
import org.jeecg.modules.wxBookGradeRel.service.IWxBookGradeRelService;
import org.jeecg.modules.wxBookReg.entity.WxBookReg;
import org.jeecg.modules.wxStudy.entity.WxStudy;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
 * @Description: 图书管理
 * @Author: jeecg-boot
 * @Date: 2019-12-08
 * @Version: V1.0
 */
@RestController
@RequestMapping("/wxBook/wxBook")
@Slf4j
@Api(tags = "图书接口")
public class WxBookController {
    @Autowired
    private IWxBookService wxBookService;
    @Autowired
    private IWxBanjiService wxBanjiService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IWxBookGradeRelService wxBookGradeRelService;


    @Autowired
    private ISysUserService sysUserService;
    /**
     * 分页列表查询
     *
     * @param wxBook
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/list")
//	@ApiOperation(value = "查询图书列表")
    public Result<IPage<WxBook>> queryPageList(WxBook wxBook,
                                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                               HttpServletRequest req) {
        String username = JwtUtil.getUserNameByToken(req);
        SysUser sysUser = sysUserService.getUserByName(username);
        wxBook.setSysOrgCode(sysUser.getOrgCode());
        Result<IPage<WxBook>> result = new Result<IPage<WxBook>>();
        QueryWrapper<WxBook> queryWrapper = QueryGenerator.initQueryWrapper(wxBook, req.getParameterMap());
        Page<WxBook> page = new Page<WxBook>(pageNo, pageSize);
        IPage<WxBook> pageList = wxBookService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @GetMapping(value = "/queryList")
    @ApiOperation(value = "查询可借图书列表APP", notes = "author by caofei")
    public Result<IPage<WxBook>> queryList(@ApiParam(value = "图书名",required = false) @RequestParam(name = "bookName", defaultValue = "") String bookName,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                           HttpServletRequest req) {

        String username = JwtUtil.getUserNameByToken(req);
        SysUser sysUser = sysUserService.getUserByName(username);
        WxStudy student = (WxStudy) redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
        WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
        String grageId = null;
        String bookregId = null;
        if(student != null){
            grageId = getGradeId(req);
        }
        if (bookReg != null){
            bookregId = bookReg.getId();
        }

        Result<IPage<WxBook>> result = new Result<IPage<WxBook>>();
        Page<WxBook> page = new Page<WxBook>(pageNo, pageSize);
        WxBook wxBook = new WxBook();
        wxBook.setBookName(bookName);
        wxBook.setSysOrgCode(sysUser.getOrgCode());
        Page<WxBook> pageList = wxBookService.queryList(page, wxBook, grageId, bookregId);
        for (WxBook book : pageList.getRecords()){
            book.setBookImg(CommonConstant.SHOW_IMG + book.getBookImg());
        }
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @GetMapping(value = "/queryKeptBookList")
    @ApiOperation(value = "查询已借图书列表APP", notes = "author by caofei")
    public Result<IPage<WxBook>> queryKeptBookList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                                   HttpServletRequest req) {
        Result<IPage<WxBook>> result = new Result<IPage<WxBook>>();
        String username = JwtUtil.getUserNameByToken(req);
        WxStudy student = (WxStudy) redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
        WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
        if (student == null){
            result.setSuccess(false);
            result.setCode(CommonConstant.SC_OPERATE_FAIL);
            result.setMessage("当前登陆用户不是学生");
            return result;
        }
        if (bookReg == null){
            result.setSuccess(false);
            result.setCode(CommonConstant.SC_OPERATE_FAIL);
            result.setMessage("当前登陆用户没有借阅证，请向图书管理员注册");
            return result;
        }
        if(bookReg.getStripStatus().equals("0")) {
            result.setSuccess(false);
            result.setCode(CommonConstant.SC_OPERATE_FAIL);
            result.setMessage("当前登陆借阅卡已无效");
            return result;

        }
        String grageId = null;
        String bookregId = null;
        if(student != null){
            grageId = getGradeId(req);
        }
        if (bookReg != null){
            bookregId = bookReg.getId();
        }


        Page<WxBook> page = new Page<WxBook>(pageNo, pageSize);
        Page<WxBook> pageList = wxBookService.queryKeptBookList(page, null, grageId, bookregId);
        for (WxBook book : pageList.getRecords()){
            if(book.getBookImg()!=null) {
                book.setBookImg(CommonConstant.SHOW_IMG + book.getBookImg());
            }
        }
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @GetMapping(value = "/queryRecommendBookList")
    @ApiOperation(value = "查询推荐图书列表APP", notes = "author by caofei")
    public Result<IPage<WxBook>> queryRecommendBookList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                                        HttpServletRequest req) {
        String username = JwtUtil.getUserNameByToken(req);
        WxStudy student = (WxStudy) redisUtil.get(CommonConstant.PREFIX_USER_INFO + username);
        WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
        String grageId = null;
        String bookregId = null;
        if(student != null){
            grageId = getGradeId(req);
        }
        if (bookReg != null){
            bookregId = bookReg.getId();
        }
        Result<IPage<WxBook>> result = new Result<IPage<WxBook>>();
        Page<WxBook> page = new Page<WxBook>(pageNo, pageSize);
        Page<WxBook> pageList = wxBookService.queryRecommendBookList(page, grageId, bookregId);
        for (WxBook book : pageList.getRecords()){
            if(book.getBookImg()!=null) {
                book.setBookImg(CommonConstant.SHOW_IMG + book.getBookImg());
            }
        }
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
            if (wxBanjiEntity != null ){
                gradeId=wxBanjiEntity.getGradeId();
            }
        }
        return gradeId;
    }
    @ApiOperation(value = "借书APP", notes = "author by caofei")
    @PostMapping(value = "/borrowBook")
    public Result borrowBook(@RequestBody IDParam idParam, HttpServletRequest req) {
        Result result = new Result();
        String username = JwtUtil.getUserNameByToken(req);
        WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
        if (bookReg == null){
            result.setSuccess(false);
            result.setCode(CommonConstant.SC_OPERATE_FAIL);
            result.setMessage("当前登陆用户没有借阅证，请向图书管理员注册");
            return result;
        }
        WxBook wxBook = wxBookService.getById(idParam.getId());
        if (wxBook == null || wxBook.getBookSurplus() == 0) {
            result.operateFail("本书已借完或已下架");
        } else {
            int i = wxBookService.queryUserKeptBook(bookReg.getId(), idParam.getId());
            if (i >= 1) {
                result.operateFail("您已借阅过本书，不可重复借阅");
            } else {
                try {
                    wxBookService.borrowBook(username, bookReg.getId(), idParam.getId(), wxBook.getBookName());
                    result.okapp("等待管理员审核");
                } catch (Exception e) {
                    e.printStackTrace();
                    result.operateFail("操作失败，请联系管理员");
                }
            }

        }
        return result;
    }




    /*借书APP2，借阅图书要扫学生的借阅码和图书的书籍编码,需要用到借书记录表,BookRegRelVO借阅记录中有学籍号*/
    @ApiOperation(value = "借书APP2", notes = "author by caofei2")
    @PostMapping(value = "/borrowBook2")
    public Result borrowBook2(@RequestParam(name = "bookIsbn") String bookIsbn,
                              @RequestParam(name="id") String id ,HttpServletRequest req) {
        Result result = new Result();
        String username = JwtUtil.getUserNameByToken(req);
        WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
        if (bookReg == null){
            result.setSuccess(false);
            result.setCode(CommonConstant.SC_OPERATE_FAIL);
            result.setMessage("当前登陆用户没有借阅证，请向图书管理员注册");
            return result;
        }
        WxBook wxBook = wxBookService.getById(idParam.getId());
        if (wxBook == null || wxBook.getBookSurplus() == 0) {
            result.operateFail("本书已借完或已下架");
        } else {
            int i = wxBookService.queryUserKeptBook(bookReg.getId(), idParam.getId());
            if (i >= 1) {
                result.operateFail("您已借阅过本书，不可重复借阅");
            } else {
                try {
                    wxBookService.borrowBook(username, bookReg.getId(), idParam.getId(), wxBook.getBookName());
                    result.okapp("等待管理员审核");
                } catch (Exception e) {
                    e.printStackTrace();
                    result.operateFail("操作失败，请联系管理员");
                }
            }

        }
        return result;
    }










    @ApiOperation(value = "还书APP", notes = "author by caofei")
    @PostMapping(value = "/returnBook")
    public Result returnBook(@RequestBody IDParam idParam, HttpServletRequest req) {
        Result result = new Result();
        String username = JwtUtil.getUserNameByToken(req);
        WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
        if (bookReg == null){
            result.setSuccess(false);
            result.setCode(CommonConstant.SC_OPERATE_FAIL);
            result.setMessage("当前登陆用户没有借阅证，请向图书管理员注册");
            return result;
        }

        WxBookRegRel regRel = wxBookService.selectBookRegRelById(idParam.getId(), bookReg.getId());
        if (regRel == null){
            result.operateFail("没有借阅记录");
            return result;
        }
        if (regRel.getIsBorrow() != 1 && regRel.getIsReturned() != 0 ){
            result.operateFail("此书已归还或未借阅，无法操作");
            return result;
        }
        try{
            wxBookService.returnBook(idParam.getId(), regRel.getBookId(), bookReg.getId());
            result.okapp("等待管理员审核");
        } catch (Exception e){
            e.printStackTrace();
            result.operateFail("操作失败，请联系管理员");
        }


        return result;
    }


    @GetMapping(value = "/queryBorrowList")
    @ApiOperation(value = "查询在读图书APP", notes = "author by caofei", response = BookRegRelVO.class)
    public Result<Map<String, Object>> queryBorrowList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                                       HttpServletRequest req) {
        Result<Map<String, Object>> result = new Result<Map<String, Object>>();
        try {
            Map<String, Object> map = new HashMap<>();
            String username = JwtUtil.getUserNameByToken(req);
            WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
//            if(bookReg==null) {
//			 bookReg = bookRegService.getOne(new QueryWrapper<WxBookReg>().lambda().eq(WxBookReg::getUserId, sysUser.getId()));
//				redisUtil.set(CommonConstant.STUDENT_BOOK_REG + username, bookReg);
//            }
            if (bookReg == null){
                result.setSuccess(false);
                result.setCode(CommonConstant.SC_OPERATE_FAIL);
                result.setMessage("当前登陆用户没有借阅证，请向图书管理员注册");
                return result;
            }
            if(bookReg.getStripStatus().equals("0")) {
                result.setSuccess(false);
                result.setCode(CommonConstant.SC_OPERATE_FAIL);
                result.setMessage("当前登陆借阅卡已无效");
                return result;

            }
            Page<BookRegRelVO> page = new Page<BookRegRelVO>(pageNo, pageSize);
            Page<BookRegRelVO> pageList = wxBookService.queryBorrowList(page, bookReg.getId());
            Integer borrowedCount = wxBookService.queryBorrowHisCount(bookReg.getId());
            map.put("list", pageList);
            map.put("bookRegId", bookReg.getStripMa());
            map.put("borrowedCount", borrowedCount);
            result.setMessage("查询成功");
            result.setResult(map);
            result.setSuccess(true);
        } catch (JeecgBootException e) {
            e.printStackTrace();
            result.operateFail("查询失败");
        }

        return  result;
    }

    @GetMapping(value = "/queryBorrowHistoryList")
    @ApiOperation(value = "查询已读图书APP", notes = "author by caofei", response = BookRegRelVO.class)
    public Result<IPage<BookRegRelVO>> queryBorrowHistoryList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                              @RequestParam(name = "pageSize", defaultValue = "100") Integer pageSize,
                                                              HttpServletRequest req) {
        Result<IPage<BookRegRelVO>> result = new Result<IPage<BookRegRelVO>>();

        try {
            String username = JwtUtil.getUserNameByToken(req);
            WxBookReg bookReg = (WxBookReg) redisUtil.get(CommonConstant.STUDENT_BOOK_REG + username);
            if (bookReg == null){
                result.setSuccess(false);
                result.setCode(CommonConstant.SC_OPERATE_FAIL);
                result.setMessage("当前登陆用户没有借阅证，请向图书管理员注册");
                return result;
            }

            Page<BookRegRelVO> page = new Page<BookRegRelVO>(pageNo, pageSize);
            Page<BookRegRelVO> pageList = wxBookService.queryBorrowHistoryList(page, bookReg.getId());

            result.setMessage("查询成功");
            result.setResult(pageList);
            result.setSuccess(true);
        } catch (JeecgBootException e) {
            e.printStackTrace();
            result.operateFail("查询失败");
        }

        return  result;
    }

    @ApiOperation(value = "根据条形码查询图书web", notes = "author by caofei", response = WxBook.class)
    @GetMapping("/queryBookRegByStrip")
    public Result<WxBook> queryBookByStrip(@RequestParam("stripma") String stripma){
        Result<WxBook> result = new Result<WxBook>();
        WxBook wxBook = wxBookService.getOne(new QueryWrapper<WxBook>().lambda().eq(WxBook::getStripMa, stripma));
        if(wxBook==null) {
            result.error500("未找到对应图书");
        }else {
            result.setResult(wxBook);
            result.setSuccess(true);
        }
        return result;
    }

    @ApiOperation(value = "加入借书库/验证图书是否可借web", notes = "author by caofei", response = Result.class)
    @GetMapping("/addBorrowLibrary")
    public Result addBorrowLibrary(@RequestParam("bookid") String bookid, @RequestParam("bookregId") String bookregId){
        Result result = new Result();
        if (StringUtils.isEmpty(bookregId)){
            return result.error500("请输入借阅卡条形码");
        }
        WxBook wxBook = wxBookService.getById(bookid);
        if (wxBook == null || StringUtils.isEmpty(wxBook.getBookSurplus()) || wxBook.getBookSurplus() == 0) {
            result.error500("本书已借完或已下架");
        } else {
            int i = wxBookService.queryUserKeptBook(bookregId, bookid);
            if (i >= 1) {
                result.error500("您已借阅过本书，不可重复借阅");
            } else {
                result.success("可以借本书");
            }
        }
        return result;
    }


    @ApiOperation(value = "图书借阅web", notes = "author by caofei", response = Result.class)
    @PostMapping(value = "borrowBooks")
    public Result borrowBooks(@RequestBody BorrowBookVO borrowBookVO, HttpServletRequest req){

        Result result = new Result();
        for (BookVO book : borrowBookVO.getBookVOList()){
            WxBook wxBook = wxBookService.getById(book.getBoolId());
            if (wxBook == null || wxBook.getBookSurplus() == 0) {
                result.error500(wxBook.getBookName() + "已借完或已下架");
                return result;
            } else {
                int i = wxBookService.queryUserKeptBook(borrowBookVO.getBookRegId(), book.getBoolId());
                if (i >= 1) {
                    result.error500("您已借阅过"+wxBook.getBookName()+"，不可重复借阅");
                    return result;
                }
            }
        }
        wxBookService.borrowBooks(borrowBookVO);
        return Result.ok("借书成功");
    }

    @ApiOperation(value = "归还图书web", notes = "author by caofei", response = Result.class)
    @PostMapping(value = "returnBooks")
    public Result returnBooks(@RequestBody ReturnBookParam returnBookParam, HttpServletRequest req){
        Result result = new Result();
        for (String relid : returnBookParam.getBookRegRelIds()){
            WxBookRegRel regRel = wxBookService.selectBookRegRelById(relid, returnBookParam.getBookRegId());
            if (regRel == null){
                result.operateFail("没有借阅记录");
                return result;
            }
        }
        wxBookService.returnBooks(returnBookParam);

        return result;
    }



    /**
     * 添加
     *
     * @param wxBook
     * @return
     */
    @PostMapping(value = "/add")
//	@ApiOperation(value = "新增图书接口")
    public Result<WxBook> add(HttpServletRequest req,@RequestBody WxBook wxBook) {
        Result<WxBook> result = new Result<WxBook>();
        try {
            String username = JwtUtil.getUserNameByToken(req);
            SysUser sysUser = sysUserService.getUserByName(username);
            wxBook.setSysOrgCode(sysUser.getOrgCode());
            wxBookService.save(wxBook);
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
     * @param wxBook
     * @return
     */
    @PutMapping(value = "/edit")
    public Result<WxBook> edit(@RequestBody WxBook wxBook) {
        Result<WxBook> result = new Result<WxBook>();
        WxBook wxBookEntity = wxBookService.getById(wxBook.getId());
        if (wxBookEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = wxBookService.updateById(wxBook);
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
            wxBookService.removeById(id);
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
    public Result<WxBook> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<WxBook> result = new Result<WxBook>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.wxBookService.removeByIds(Arrays.asList(ids.split(",")));
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
    public Result<WxBook> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<WxBook> result = new Result<WxBook>();
        WxBook wxBook = wxBookService.getById(id);
        if (wxBook == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(wxBook);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WxBook wxBook) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<WxBook> queryWrapper = QueryGenerator.initQueryWrapper(wxBook, request.getParameterMap());
        List<WxBook> pageList = wxBookService.list(queryWrapper);
        // Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        // 过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isEmpty(selections)) {
            mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        } else {
            List<String> selectionList = Arrays.asList(selections.split(","));
            List<WxBook> exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
            mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        }
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "图书管理列表");
        mv.addObject(NormalExcelConstants.CLASS, WxBook.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("图书管理列表数据", "导出人:Jeecg", "导出信息"));
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
                List<WxBook> listWxBooks = ExcelImportUtil.importExcel(file.getInputStream(), WxBook.class, params);
                wxBookService.saveBatch(listWxBooks);
                return Result.ok("文件导入成功！数据行数:" + listWxBooks.size());
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
     * 查询已推荐图书
     * @param wxBook
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryHaveTuijianBookList")
    public Result<IPage<Map<String,Object>>> queryHaveTuijianBookList(WxBook wxBook,
                                                                      @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                                      @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                                      HttpServletRequest req) {
        Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
        Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
        String gradeId=req.getParameter("gradeId");
        Page<Map<String,Object>> pageList =wxBookService.queryHaveTuijianBookList(page, wxBook, gradeId);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 查询可以推荐图书（去掉已推荐的）
     * @param wxBook
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryNeedTuijianBookList")
    public Result<IPage<Map<String,Object>>> queryNeedTuijianBookList(WxBook wxBook,
                                                                      @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                                      @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                                      HttpServletRequest req) {
        Result<IPage<Map<String,Object>>> result = new Result<IPage<Map<String,Object>>>();
        Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageNo, pageSize);
        String gradeId=req.getParameter("gradeId");
        Page<Map<String,Object>> pageList =wxBookService.queryNeedTuijianBookList(page, wxBook, gradeId);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }


    @GetMapping(value = "/saveBookGradeRel")
    public Map<String,Object> saveBookGradeRel(
            @RequestParam(name="gradeId") String gradeId,
            @RequestParam(name="bookId") String bookId,
            HttpServletRequest req) {
        Map<String,Object> rtm=new HashMap<String,Object>();
        try{
            List<String> l = Arrays.asList(bookId.split(","));
            for(String s:l){
                if(!"".equals(s)){
                    WxBookGradeRel sgr=new WxBookGradeRel();
                    sgr.setGradeId(gradeId);
                    sgr.setBookId(s);
                    wxBookGradeRelService.save(sgr);
                }
            }
            rtm.put("code", "0");
        }catch(Exception e){
            rtm.put("code", "1");
        }

        return rtm;
    }

    @GetMapping(value = "/deleteBookGradeRel")
    public Map<String,Object> deleteBookGradeRel(
            @RequestParam(name="gradeId") String gradeId,
            @RequestParam(name="bookId") String bookId,
            HttpServletRequest req) {
        Map<String,Object> rtm=new HashMap<String,Object>();
        try{
            WxBookGradeRel sgr=new WxBookGradeRel();
            sgr.setGradeId(gradeId);
            sgr.setBookId(bookId);
            QueryWrapper<WxBookGradeRel> queryWrapper = QueryGenerator.initQueryWrapper(sgr, req.getParameterMap());
            wxBookGradeRelService.remove(queryWrapper);
            rtm.put("code", "0");
        }catch(Exception e){
            rtm.put("code", "1");
        }

        return rtm;
    }

}
