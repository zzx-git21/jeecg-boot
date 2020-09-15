package org.jeecg.modules.wxBook.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.wxBook.entity.WxBook;
import org.jeecg.modules.wxBook.entity.WxBookRegRel;
import org.jeecg.modules.wxBook.model.BookRegRelVO;
import org.jeecg.modules.wxBook.model.BorrowBookVO;
import org.jeecg.modules.wxBook.model.ReturnBookParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 图书管理
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface IWxBookService extends IService<WxBook> { 
	public Page<WxBook> queryList(Page<WxBook> page,WxBook wxBook,String gradeId, String bookregId);

	public Page<WxBook> queryKeptBookList(Page<WxBook> page,WxBook wxBook,String gradeId, String bookregId);

	public Page<WxBook> queryRecommendBookList(Page<WxBook> page,String gradeId, String bookregId);

	public Integer queryUserKeptBook(String bookregId, String bookId);


	public void borrowBook(String username, String bookregId, String bookId, String boolName) throws Exception;

	public WxBookRegRel selectBookRegRelById(String id, String bookregId);


	public void returnBook(String id, String bookId, String bookregId);

	public Page<BookRegRelVO> queryBorrowList(Page<BookRegRelVO> page, String bookregId);

	public List<BookRegRelVO> queryBorrowList(String bookregId);

	public Page<BookRegRelVO> queryBorrowHistoryList(Page<BookRegRelVO> page, String bookregId);

	public Integer queryBorrowHisCount(String bookregId);

	public void borrowBooks(BorrowBookVO borrowBookVO);

	public void returnBooks(ReturnBookParam returnBookParam);
	
	public Page<Map<String,Object>> queryHaveTuijianBookList(Page<Map<String,Object>> page,WxBook wxBook,String gradeId);
	public Page<Map<String,Object>> queryNeedTuijianBookList(Page<Map<String,Object>> page,WxBook wxBook,String gradeId);

}
