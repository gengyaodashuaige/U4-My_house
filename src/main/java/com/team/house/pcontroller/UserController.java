package com.team.house.pcontroller;

import com.team.house.entity.Users;
import com.team.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 王建兵
 * @Classname UserController
 * @Description TODO
 * @Date 2019/12/26 9:16
 * @Created by Administrator
 */
@Controller(value = "userController2")
@RequestMapping(value = "/page")
public class UserController {

    @Autowired
    private UserService userService;

    //验证用户名是否可用
    @RequestMapping("/checkUname")
    @ResponseBody
    public String checkUnam(String uname){
        //调用service
        boolean res=userService.checkUname(uname);
        return "{\"result\":"+res+"}";
    }

    //用户注册
    @RequestMapping("reg")
    public String regUser(Users users){
        //调用service
       int res=userService.addUser(users);
       if(res>0)  //
       {
         return "redirect:login.jsp";
       }else{
          // return "redirect:error.jsp";
           return "redirect:regs.jsp";
       }
    }


    //用户登录
    @RequestMapping("login")
    public String regUser(String username, String password, HttpSession session){
          //调用业务
         Users users=userService.login(username,password);
         if(users==null){
             return "redirect:login.jsp";
         }else{
             //只要登入，肯定使用cookie或者session保存登入者的信息
             //使用session保存登入的者的信息
             session.setAttribute("loginInfo",users);
             //设置有效期
             session.setMaxInactiveInterval(10*60);  //秒

             return "redirect:showHouse";
         }
    }
}
