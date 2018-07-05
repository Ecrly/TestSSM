package com.spring.controller;

import com.spring.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/user")
    public ModelAndView user(ModelAndView mv, @RequestParam String name){
        String n = userService.getAge(name);
        mv.addObject("name", n);
        mv.setViewName("user");
        return mv;
    }
}
