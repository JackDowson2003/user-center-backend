package com.company.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenlong
 * @version 2020.2.3
 * 通用返回
 * @Date 2022/7/2 21:11
 */
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -2866528195902622365L;

    /**错误码*/
    private int code;

    /**传入的数据*/
    private T data;

    /**错误的信息*/
    private String message;

    /**详细信息*/
    private String description;

    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data,String message) {
        this(code,data,message,"");
    }

    public BaseResponse(int code, T data) {
        this(code,data,"","");
    }

    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }
}
