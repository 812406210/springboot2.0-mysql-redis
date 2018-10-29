package com.cn.commodity.controller;

import com.alibaba.fastjson.JSONObject;
import com.cn.commodity.entity.User;
import com.cn.commodity.service.RedisService;
import com.cn.commodity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private RedisService redisService;

    private JSONObject json = new JSONObject();

    @RequestMapping("/showUser")
    @ResponseBody
    public User showUser(HttpServletRequest request){
        User user = null;
        int userId = Integer.parseInt(request.getParameter("id"));
        String result = redisService.get("user");
        if(result==null) {
            user = this.userService.getUserById(userId);
            System.out.println("来自数据库："+user.getUserName());
            redisService.set("user",json.toJSONString(user));
        }else {
            user = json.parseObject(result, User.class);
            System.out.println("来自redis缓存："+user.getUserName());
        }
        return user;
    }

    @RequestMapping("/showListUser")
    @ResponseBody
    public List<User> showListUser(HttpServletRequest request) {
        List<User> userList = null;
        String result = redisService.get("userList");
        if(result==null){
            userList = userService.selectAllUser();
            System.out.println("来自数据库："+userList);
            redisService.set("userList",json.toJSONString(userList));
        }else {
            userList = json.parseArray(result, User.class);
            System.out.println("来自redis缓存："+userList);
        }
        System.out.println(userList);
        return  userList;
    }
}