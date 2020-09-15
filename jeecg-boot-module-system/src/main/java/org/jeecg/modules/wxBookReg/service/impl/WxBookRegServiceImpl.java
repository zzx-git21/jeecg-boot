package org.jeecg.modules.wxBookReg.service.impl;


import org.jeecg.modules.wxBookReg.entity.WxBookReg;
import org.jeecg.modules.wxBookReg.mapper.WxBookRegMapper;
import org.jeecg.modules.wxBookReg.model.BookRegVo;
import org.jeecg.modules.wxBookReg.service.IWxBookRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: 图书会员注册
 * @Author: jeecg-boot
 * @Date:   2019-12-08
 * @Version: V1.0
 */
@Service
public class WxBookRegServiceImpl extends ServiceImpl<WxBookRegMapper, WxBookReg> implements IWxBookRegService {

    @Resource
    private WxBookRegMapper wxBookRegMapper;

    @Override
    public BookRegVo queryBookReg(String stripma) {
        return wxBookRegMapper.queryBookReg(stripma);
    }
}
