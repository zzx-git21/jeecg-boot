package org.jeecg.modules.wxBookReg.service;


import org.jeecg.modules.wxBookReg.entity.WxBookReg;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.wxBookReg.model.BookRegVo;

/**
 * @Description: 图书会员注册
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
public interface IWxBookRegService extends IService<WxBookReg> {

    public BookRegVo queryBookReg(String stripma);

}
