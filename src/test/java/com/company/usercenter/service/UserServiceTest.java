package com.company.usercenter.service;

import com.company.usercenter.model.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author chenlong
 * @version 2020.2.3
 * @Date 2022/6/19 23:41
 * <p>
 * 用户服务测试
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("yupi");
        user.setUserAccount("123");
        user.setAvatarUrl("/Users/cl/Desktop/pics/img.png");
        user.setGender(1);
        user.setUserPassword("123456");
        user.setPhone("123");
        user.setEmail("456");

        boolean save = userService.save(user);
        System.out.println(user.getId());
        assertTrue(save);
    }

    @Test
    void testMD5() throws NoSuchAlgorithmException {
        String s = DigestUtils.md5DigestAsHex(("abc" + "123").getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String p1 = "";
        String p2 = "123456";
        long result = userService.UserRegister(userAccount, p1, p2,"1");
        assertEquals(-1, result);
        userAccount = "yu";
        result = userService.UserRegister(userAccount, p1, p2,"1");
        assertEquals(-1, result);

        userAccount = "yupidog";
        p1 = "123456";
        result = userService.UserRegister(userAccount, p1, p2,"1");
        assertEquals(-1, result);

        userAccount = "yu pi";
        p1 = "12345678";
        result = userService.UserRegister(userAccount, p1, p2,"1");
        assertEquals(-1, result);

        p2 = "123456789";
        result = userService.UserRegister(userAccount, p1, p2,"1");
        assertEquals(-1, result);

        userAccount = "123";
        p2 = "12345678";
        System.out.println(userAccount);
        System.out.println(p1);
        System.out.println(p2);
        result = userService.UserRegister(userAccount, p1, p2,"1");
        assertEquals(-1, result);

        userAccount = "yupidog";
        System.out.println(userAccount);
        System.out.println(p1);
        System.out.println(p2);
        result = userService.UserRegister(userAccount, p1, p2,"1");
        assertTrue(result > 0);
    }

    @Test
    void testGson() {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        String json = "\"{\"session_key\":\"rDwyzWPrPjdHsgfGI4vWPg\u003d\u003d\",\"openid\":\"owjPd5PqdcjPBpgeZyP5jKYd_fNg\"}\"";
        Map<String, String> myMap = gson.fromJson(json, type);
        System.out.println(myMap);
    }

    @Test
    void doLogin() {

        User yupidog = userService.userLogin("yupidog", "12345678", new MockHttpServletRequest());

        System.out.println(yupidog);
    }

}
/*

*/
/*


class ReverseLinkedTable{


    public static void reverse(Node orginal){
        Stack<Node> stack = new Stack<>();
        Node first = orginal.first;
        while (first != null){
            stack.push(first);
            first = first.next;
        }
    }
}

class Node{

    Node first;
    Node last;
    Node next;

    public Node(Node node){
        this.next = node;
    }

    public void add(Node node){
        if (first == null){
            first = node;
            last = node;
        }else if (first != null && first.next == null){
            first.next = node;
            last = node;
        }else {
            while (first.next != null){
                first = first.next;
            }
            first.next = node;
            last = node;
        }
    }
}*/
