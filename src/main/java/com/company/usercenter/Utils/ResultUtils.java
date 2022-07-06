package com.company.usercenter.Utils;

import com.company.usercenter.common.BaseResponse;
import com.company.usercenter.common.ErrorCode;

/**
 * 返回工具类
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/7/2 21:18
 */
public class ResultUtils {

    /**
     * 得到对应的基础响应
     * @param data 传入的数据 类型是T
     * @param <T> 泛型 <T></T>
     * @return   返回 BaseResponse<T>类型
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"ok");
    }

    /**
     * 得到对应的错误码
     * @param code 传入的状态码
     * @param description 详情
     * @param message 错误信息
     * @return 返回的 BaseResponse<ErrorCode>
     */
    public static BaseResponse<ErrorCode> error (int code,String  message,String description){
        return new BaseResponse<>(code,null,message,description);
    }

    /**
     * 得到对应的错误码
     * @param errorCode 传入的错误码
     * @param description 详情
     * @param message 错误信息
     * @return 返回的 BaseResponse<ErrorCode>
     */
    public static BaseResponse<ErrorCode> error (ErrorCode errorCode,String  message,String description){
        return new BaseResponse<>(errorCode.getCode(),null,message,description);
    }

    /**
     * 得到对应的错误码
     * @param errorCode 传入的错误码
     * @return 返回的 BaseResponse<ErrorCode>
     */
    public static BaseResponse<ErrorCode> error (ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

}
