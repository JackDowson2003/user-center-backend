package com.company.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.usercenter.model.domain.User;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;


/**
 * @author cl
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2022-06-19 23:32:00
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long UserRegister(String userAccount, String userPassword, String checkPassword,String plantCode);

    /**
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request 请求域
     * @return 返回用户的脱敏信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 进行脱敏
     * @param originUser 传入的User 对象
     * @return 脱敏后的数据
     */
    User getSafetyUser(@NotNull User originUser);

    /**
     * 登出
     * @param request HttpServletRequest
     * @return 返回是否登出成功
     */
    int userLogout(HttpServletRequest request);
}
