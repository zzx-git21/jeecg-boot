package org.jeecg.modules.wxBook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxBook.model.BookRegRelVO;
import org.jeecg.modules.wxBook.entity.WxBook;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.wxBook.entity.WxBookRegRel;

/**
 * @Description: 图书管理
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface WxBookMapper extends BaseMapper<WxBook> {

	List<WxBook> queryList(Page<WxBook> page,@Param("wxBook")WxBook wxBook,@Param("gradeId")String gradeId,
						   @Param("bookregId")String bookregId);

	List<WxBook> queryKeptBookList(Page<WxBook> page,@Param("wxBook")WxBook wxBook,@Param("gradeId")String gradeId,
						   @Param("bookregId")String bookregId);

	List<WxBook> queryRecommendBookList(Page<WxBook> page,@Param("gradeId")String gradeId, @Param("bookregId")String bookregId);

	Integer queryUserKeptBook( @Param("bookregId")String bookregId, @Param("bookId") String bookId);

	void insertBookRegRel(WxBookRegRel bookRegRel);

	void regulationBooks(@Param("bookId") String bookId, @Param("type") Integer type);

	WxBookRegRel selectBookRegRelById(@Param("relId") String relId, @Param("bookregId")String bookregId);

	void returnBook(@Param("relId") String relId, @Param("bookregId")String bookregId);

	void returnBookAdmin(@Param("relId") String relId, @Param("bookregId")String bookregId);

	List<BookRegRelVO> queryBorrowList(@Param("bookregId")String bookregId, @Param("type") Integer type);

	Integer queryBorrowedCount(@Param("bookregId")String bookregId);

	void insertBookRegRelBatch(@Param("list") List<WxBookRegRel> list);
	
	List<Map<String,Object>> queryHaveTuijianBookList(Page<Map<String,Object>> page,@Param("wxBook")WxBook wxBook,@Param("gradeId")String gradeId);
	List<Map<String,Object>> queryNeedTuijianBookList(Page<Map<String,Object>> page,@Param("wxBook")WxBook wxBook,@Param("gradeId")String gradeId);

}
