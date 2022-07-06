package com.company.usercenter.exception;

import com.company.usercenter.Utils.ResultUtils;
import com.company.usercenter.common.BaseResponse;
import com.company.usercenter.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/7/5 00:30
 */
@RestControllerAdvice //此注解对所有的异常进行一个拦截 实现接口的统一返回格式
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class) //声明捕获异常
    public BaseResponse<ErrorCode> businessExceptionHandler(BusinessException e){
        log.error("businessException"+e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<ErrorCode> runTimeExceptionHandler(RuntimeException e){
        log.error("runTimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,ErrorCode.SYSTEM_ERROR.getMessage(),"");
    }
}
