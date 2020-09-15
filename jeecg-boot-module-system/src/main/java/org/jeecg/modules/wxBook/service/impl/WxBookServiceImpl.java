package org.jeecg.modules.wxBook.service.impl;

import javax.annotation.Resource;

import org.jeecg.modules.wxBook.entity.*;
import org.jeecg.modules.wxBook.mapper.WxBookMapper;
import org.jeecg.modules.wxBook.model.BookRegRelVO;
import org.jeecg.modules.wxBook.model.BookVO;
import org.jeecg.modules.wxBook.model.BorrowBookVO;
import org.jeecg.modules.wxBook.model.ReturnBookParam;
import org.jeecg.modules.wxBook.service.IWxBookService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 图书管理
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Service
public class WxBookServiceImpl extends ServiceImpl<WxBookMapper, WxBook> implements IWxBookService {
	@Resource
	private WxBookMapper wxBookMapper;
	@Override
	public Page<WxBook> queryList(Page<WxBook> page,WxBook wxBook,String gradeId, String bookregId) {
		return page.setRecords(wxBookMapper.queryList(page, wxBook,gradeId,bookregId));
	}

	@Override
	public Page<WxBook> queryKeptBookList(Page<WxBook> page, WxBook wxBook, String gradeId, String bookregId) {
		return page.setRecords(wxBookMapper.queryKeptBookList(page, wxBook,gradeId,bookregId));
	}

	@Override
	public Page<WxBook> queryRecommendBookList(Page<WxBook> page, String gradeId, String bookregId) {
		return page.setRecords(wxBookMapper.queryRecommendBookList(page,gradeId,bookregId));
	}

	@Override
	public Integer queryUserKeptBook(String bookregId, String bookId) {
		return wxBookMapper.queryUserKeptBook(bookregId, bookId);
	}

	@Override
	public void borrowBook(String username, String bookregId, String bookId, String boolName)  throws Exception{
		WxBookRegRel bookRegRel = new WxBookRegRel();
		bookRegRel.setBookId(bookId);
		bookRegRel.setBookRegId(bookregId);
		bookRegRel.setCreateBy(username);
		bookRegRel.setIsBorrow(0);  // wait approve
		bookRegRel.setIsReturned(0); // no return
		bookRegRel.setBookName(boolName);
		wxBookMapper.insertBookRegRel(bookRegRel);

		wxBookMapper.regulationBooks(bookId, 0);

	}

	@Override
	public WxBookRegRel selectBookRegRelById(String id, String bookregId) {
		return wxBookMapper.selectBookRegRelById(id, bookregId);
	}

	@Override
	public void returnBook(String id, String bookId, String bookregId) {
		wxBookMapper.returnBook(id, bookregId);
		wxBookMapper.regulationBooks(bookId, 1);
	}

	@Override
	public Page<BookRegRelVO> queryBorrowList(Page<BookRegRelVO> page, String bookregId) {
		return page.setRecords(wxBookMapper.queryBorrowList(bookregId, 0));
	}

	@Override
	public List<BookRegRelVO> queryBorrowList(String bookregId) {
		return wxBookMapper.queryBorrowList(bookregId, 0);
	}

	@Override
	public Page<BookRegRelVO> queryBorrowHistoryList(Page<BookRegRelVO> page, String bookregId) {
		return page.setRecords(wxBookMapper.queryBorrowList(bookregId, 1));
	}

	@Override
	public Integer queryBorrowHisCount(String bookregId) {
		return wxBookMapper.queryBorrowedCount(bookregId);
	}

	@Override
	public void borrowBooks(BorrowBookVO borrowBookVO) {
		Date now = new Date();
		List<WxBookRegRel> regRels = new ArrayList<>();
		WxBookRegRel regRel = null;
		for (BookVO book : borrowBookVO.getBookVOList()) {
			WxBook wxBook = getById(book.getBoolId());
			wxBookMapper.regulationBooks(wxBook.getId(), 0);
			regRel = new WxBookRegRel();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			regRel.setBookName(wxBook.getBookName());
			regRel.setBookId(book.getBoolId());
			regRel.setBookRegId(borrowBookVO.getBookRegId());
			regRel.setBorrowDay(book.getBorrowDay());
			regRel.setStartTime(now);
			regRel.setIsBorrow(1);
			regRel.setIsReturned(0);
			regRel.setRemark(book.getRemark());
			calendar.add(Calendar.DAY_OF_MONTH, book.getBorrowDay());
			regRel.setReturnTime(calendar.getTime());
			regRels.add(regRel);
		}

		wxBookMapper.insertBookRegRelBatch(regRels);
	}

	@Override
	public void returnBooks(ReturnBookParam returnBookParam) {
		for (String relId : returnBookParam.getBookRegRelIds()){
			WxBookRegRel regRel = selectBookRegRelById(relId, returnBookParam.getBookRegId());

			wxBookMapper.returnBookAdmin(relId, returnBookParam.getBookRegId());
			wxBookMapper.regulationBooks(regRel.getBookId(), 1);
		}

	}
	
	@Override
	public Page<Map<String,Object>> queryHaveTuijianBookList(Page<Map<String,Object>> page,WxBook wxBook,String gradeId) {
		List<Map<String,Object>> bookList=wxBookMapper.queryHaveTuijianBookList(page, wxBook,gradeId);
		return page.setRecords(bookList);
	}
	@Override
	public Page<Map<String,Object>> queryNeedTuijianBookList(Page<Map<String,Object>> page,WxBook wxBook,String gradeId) {
		List<Map<String,Object>> bookList=wxBookMapper.queryNeedTuijianBookList(page, wxBook,gradeId);
		return page.setRecords(bookList);
	}

}
