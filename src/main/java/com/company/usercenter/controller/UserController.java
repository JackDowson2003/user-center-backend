package com.company.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.usercenter.Utils.ResultUtils;
import com.company.usercenter.common.BaseResponse;
import com.company.usercenter.common.ErrorCode;
import com.company.usercenter.constant.UserConstant;
import com.company.usercenter.exception.BusinessException;
import com.company.usercenter.model.domain.User;
import com.company.usercenter.model.request.UserLoginRequest;
import com.company.usercenter.model.request.UserRegisterRequest;
import com.company.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/6/23 12:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String userStatue = UserConstant.USER_LOGIN_STATUE;

    @Resource
    private UserService userService;

    /***
     * 用户注册
     * @param userRegisterRequest 我们定义了 userAccount userPassword checkPassword的自定义类
     * @return 返回 用户注册后的id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            //return null;
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR);
        }
        //从请求体中取数据
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String plantCode = userRegisterRequest.getPlantCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,plantCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "输入的信息不完整");
        }
        long result = userService.UserRegister(
                userAccount,
                userPassword,
                checkPassword,
                plantCode);
        return new BaseResponse<>(0,result,"ok");
    }

    /**
     * @param userLoginRequest 我们自定义的定义了了userAccount 和 userPassword的类
     * @param request          HttpServletRequest
     * @return 返回脱敏后的用户
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR,"用户未登录");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"您所填信息有未填写");
        }
        User user = userService.userLogin(userAccount, userPassword, request);
//        return new BaseResponse<>(0,user,"ok");
        return ResultUtils.success(user);
    }


    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN,"账号未登录");
        }

        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * @param username 传入的username
     * @param request  HttpServletRequest
     * @return 查询到的用户
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {

        if (isAdmin(request)) {
//            return Collections.emptyList();
            throw new BusinessException(ErrorCode.NO_AUTH,"您不是管理员");
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("username", username);
        }
        List<User> userList = userService.list(userQueryWrapper);
        List<User> users = userList.stream()
                .map(user -> userService.getSafetyUser(user))
                .collect(Collectors.toList());
        return ResultUtils.success(users);
    }

    /**
     * @param id      删除的id
     * @param request HttpServletRequest
     * @return 删除是否成功
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {

        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "您不是管理员");
        }

        //预防id的问题
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编号有误");
        }

        //逻辑删除
        boolean res = userService.removeById(id);
        return ResultUtils.success(res);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrent(HttpServletRequest request) {
        Object userObj =  request.getSession().getAttribute(userStatue);

        User currentUser = (User) userObj;
        Long id = currentUser.getId();
        // TODO: 2022/6/28 校验用户是否合法
        User user = userService.getById(id);
        if (user.getIsDelete() == 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "此账号已被注销");
        }

        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /***
     * @param request 传入的HttpServletRequest
     * @return 是否为管理员
     */
    private boolean isAdmin(HttpServletRequest request) {
        //仅管理员可查询
        Object attribute = request.getSession().getAttribute(userStatue);

        User user = (User) attribute;
        return user == null || user.getUserRole() != UserConstant.ADMIN_ROLE;
    }


}
