package com.company.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/6/23 14:01
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 8987678665843060882L;

    private String userAccount;

    private String userPassword;
}
