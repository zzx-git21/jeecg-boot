package org.jeecg.modules.wxBookReg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.wxBookReg.entity.WxBookReg;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.wxBookReg.model.BookRegVo;

/**
 * @Description: 图书会员注册
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface WxBookRegMapper extends BaseMapper<WxBookReg> {

    public BookRegVo queryBookReg(@Param("stripma") String stripma);

}
