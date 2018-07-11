package com.spring.controller;

import com.spring.pojo.User;
import com.spring.service.impl.UserServiceImpl;
import com.spring.vo.JsonBean;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/check/username", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean checkusername(@RequestParam("username") String username){
        User user = userService.findByName(username);
        JsonBean jsonBean = new JsonBean();
        if(user == null){
            jsonBean.setCode(1);
            jsonBean.setMsg("用户名可以使用");
        }else{
            jsonBean.setCode(-1);
            jsonBean.setMsg("用户名重复");
        }
        return jsonBean;
    }


    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public @ResponseBody JsonBean login(@RequestParam("username") String username, @RequestParam("password") String password,
                                        HttpSession httpSession){
        System.out.println("post");
        User user = userService.findByName(username);
        JsonBean jsonBean = new JsonBean();
        if(user == null){
            jsonBean.setCode(-1);
            jsonBean.setMsg("用户名不存在");
        }else{
            if(password.equals(user.getPassword())){
                jsonBean.setCode(1);
                jsonBean.setMsg("登录成功");
                httpSession.setAttribute("username", username);
            }else{
                jsonBean.setCode(0);
                jsonBean.setMsg("密码错误");
            }
        }
        return jsonBean;
    }


    @RequestMapping(value = "/logout/", method = RequestMethod.GET)
    public @ResponseBody JsonBean logout(HttpSession httpSession) {
        JsonBean jsonBean = new JsonBean();
        try{
            httpSession.removeAttribute("username");
            jsonBean.setCode(1);
            jsonBean.setMsg("注销成功");
        }catch (Exception e){
            jsonBean.setCode(-1);
            jsonBean.setMsg("注销失败");
        }
        return jsonBean;
    }

    @RequestMapping(value = "/getSession/", method = RequestMethod.GET)
    public @ResponseBody JsonBean login(HttpSession httpSession){
        String username = (String)httpSession.getAttribute("username");
        JsonBean jsonBean = new JsonBean();
        if(username == null){
            jsonBean.setCode(-1);
            jsonBean.setMsg("未登录");
        }else{
            jsonBean.setCode(1);
            jsonBean.setMsg(username);
        }
        return jsonBean;
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public @ResponseBody JsonBean login(@RequestParam("username") String username, @RequestParam("password") String password,
                                        @RequestParam("username") String email) {
        JsonBean jsonBean = new JsonBean();
        try {
            userService.createUser(username, password, email);
            jsonBean.setCode(1);
            jsonBean.setMsg("注册成功");
        }catch (Exception e){
            jsonBean.setCode(-1);
            jsonBean.setMsg("注册失败");
        }
        return jsonBean;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<User> list(){
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
    @ResponseBody
    public User find(@PathVariable(value = "name") String name){
        User user = userService.findByName(name);
        return user;
    }


    @RequestMapping(value = "/list/{msg}", method = RequestMethod.GET)
    @ResponseBody
    public String list_mag(@PathVariable("msg") String msg){
        return msg;
    }

}
