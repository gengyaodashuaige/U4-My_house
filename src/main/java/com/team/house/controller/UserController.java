package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.TypeService;
import com.team.house.service.UserService;
import com.team.house.util.PageUtil;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RestController// == @Controller + @ResponseBody ：表示后期的方法全部返回异步数据
@RequestMapping("/admin") //指定请求前缀
public class UserController {

    @Autowired
    private UserService userService;

    //分页查询显示
    @RequestMapping("/getUserData") //condition:包含page、rows、查询条件
    @ResponseBody  //把Java对象转换成Json格式
    public Map<String,Object> getTypeData(UserCondition condition){
        //调用业务查询区域数据
        PageInfo<Users> pageInfo = userService.getUsersByPage(condition);
        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}
