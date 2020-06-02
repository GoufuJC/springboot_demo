package com.goufu.springboot_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.goufu.springboot_demo.annotation.UserLoginToken;
import com.goufu.springboot_demo.entity.User;
import com.goufu.springboot_demo.service.TokenService;
import com.goufu.springboot_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    UserService userService;
    TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Object login(@RequestBody User user) {
        JSONObject jsonpObject = new JSONObject();
        String userId = user.getUserId();
        String password = user.getPassword();
        User userForBase = userService.findUserById(userId);
        if (userForBase == null) {
            jsonpObject.put("message", "用户不存在");
        } else {
            if (!userForBase.getPassword().equals(password)) {
                jsonpObject.put("message", "密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
                jsonpObject.put("token", token);
                jsonpObject.put("user", userForBase);
            }
        }
        return jsonpObject;
    }

    @UserLoginToken
    @RequestMapping("/getMessage")
    @ResponseBody
    public String getMessage() {
        return "成功通过验证";
    }
}
