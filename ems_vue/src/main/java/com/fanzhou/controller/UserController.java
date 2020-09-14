package com.fanzhou.controller;

import com.fanzhou.entity.User;
import com.fanzhou.service.UserService;
import com.fanzhou.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanzhou
 * @create 2020/8/19 0019
 */
@RestController
@CrossOrigin //允许跨域
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("getImage") //http://localhost:8989/ems_vue/user/getImage
    public String getImageCode(HttpServletRequest request) {
        // 获取Code
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 将验证码放入 servletContext 作用域中
        request.getServletContext().setAttribute("code", code);
        String s = "";
        try {
            //3.将图片转为Base64
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            VerifyCodeUtils.outputImage(120, 40, byteArrayOutputStream, code);
            s = "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @PostMapping("register")
    public Map<String, Object> register(@RequestBody User user, String code, HttpServletRequest req) {
        log.info("用户信息:[{}],用户输入的验证码信息:[{}]", user.toString(), code);
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "注册成功！");
        try {
            Boolean checkUser = user.checkUser();
            if (!checkUser) {
                map.put("state", false);
                map.put("msg", "注册信息填写不完整！");
                throw new RuntimeException();
            }
            User existUser = userService.findUserByName(user.getUsername());
            if (existUser != null) {
                map.put("state", false);
                map.put("msg", "用户名已存在，请重新输入！");
                throw new RuntimeException();
            }
            String key = (String) req.getServletContext().getAttribute("code");
            if (code == null || !key.equalsIgnoreCase(code)) {
                map.put("state", false);
                map.put("msg", "验证码输入错误，请重新输入！");
                throw new RuntimeException();
            }
            userService.save(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "提示：注册失败！");
        }
        return map;
    }

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user) {
        log.info("用户输入的用户信息为[{}]",user.toString());
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("msg", "登录成功！");

        User existUser = userService.findUserByUser(user);
        if (existUser != null) {
            if (existUser.getPassword().equals(user.getUsername())) {
                map.put("user",existUser);
                return map;
            }
            map.put("status", false);
            map.put("msg", "用户密码错误，请重新输入！");
        } else {
            map.put("status", false);
            map.put("msg", "用户名错误，请重新输入！");
        }
        return map;
    }
}
