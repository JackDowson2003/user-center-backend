package com.company.usercenter.common;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/7/2 22:05
 */
public enum ErrorCode {

    /**
     * SUCCESS 成功
     * PARAMS_ERROR 请求参数出错
     * PARAMS_NULL_ERROR 请求数据位空
     * NOT_LOGIN 未登录
     * NO_AUTH 无权限
     */
    SUCCESS(0,"ok",""),
    PARAMS_ERROR(40000,"请求参数错误",""),
    PARAMS_NULL_ERROR(40001,"请求数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统内部异常",""),
    INPUT_ERROR(40002,"输入信息有误","")
    ;

    /**
     * 状态码
     */
    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态的详细信息
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
