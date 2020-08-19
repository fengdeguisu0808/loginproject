package com.fanzhou.controller;

import com.fanzhou.utils.VerifyCodeUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author fanzhou
 * @create 2020/8/19 0019
 */
@RestController
@CrossOrigin //允许跨域
@RequestMapping("user")
public class UserController {

    @GetMapping("getImage") //http://localhost:8989/ems_vue/user/getImage
    public  String getImageCode(HttpServletRequest request){
        // 获取Code
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 将验证码放入 servletContext 作用域中
        request.getServletContext().setAttribute("code",code);
        String s = "";
        try {
            //3.将图片转为Base64
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            VerifyCodeUtils.outputImage(120,40,byteArrayOutputStream,code);
             s = "data:image/png;base64,"+Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
