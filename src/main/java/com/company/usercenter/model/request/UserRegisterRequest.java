package com.company.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/6/23 12:53
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -8449611961492631780L;

    /**用户账号*/
    private String userAccount;

    /**用户密码*/
    private String userPassword;

    /**用户校验密码*/
    private String checkPassword;

    /**星球编号*/
    private String plantCode;

}
