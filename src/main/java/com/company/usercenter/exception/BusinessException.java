package com.company.usercenter.exception;

import com.company.usercenter.common.ErrorCode;
import org.jetbrains.annotations.NotNull;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/7/4 13:54
 */
public class BusinessException extends RuntimeException {

    /**
     * 详细信息
     */
    private final String description;

    /**
     * 状态码
     */
    private final int code;

    /**
     * @param message     异常信息
     * @param description 详细信息
     * @param code        状态码
     */
    public BusinessException(String message, String description, int code) {
        super(message);
        this.description = description;
        this.code = code;
    }

    /**
     * @param errorCode ErrorCode类
     *                  通过传入的对象来获取信息
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    /**
     * @param errorCode   ErrorCode对象
     * @param description 详细信息
     */
    public BusinessException(@NotNull ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    /**
     * @param errorCode ErrorCode对象
     * @param code      状态码
     */
    public BusinessException(@NotNull ErrorCode errorCode, int code) {
        super(errorCode.getMessage());
        this.code = code;
        this.description = errorCode.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
