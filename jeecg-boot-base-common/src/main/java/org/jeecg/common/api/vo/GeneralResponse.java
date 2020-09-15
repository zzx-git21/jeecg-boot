// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package org.jeecg.common.api.vo;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description 消息的共通返回
 *
 * @author wenye
 *
 * @date 2017年11月15日
 */
public class GeneralResponse implements  Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1742479322187416377L;

    // 状态
    @ApiModelProperty(value = "状态", required = true)
    private Byte state;
    
    // 提示消息
    @ApiModelProperty(value = "提示消息", required = true)
    private String msg = "";
    
    // 返回对象
    @ApiModelProperty(value = "返回对象", required = true)
    private Object obj = "";
    
    //请求结果
    private Integer code;

    public GeneralResponse() {
        super();
        
    }

    public GeneralResponse(Byte state, String msg, Object obj) {
        super();
        this.state = state;
        this.msg = msg;
        this.obj = obj;
    }

    /**
     * @return the state
     */
    public Byte getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
    
}
