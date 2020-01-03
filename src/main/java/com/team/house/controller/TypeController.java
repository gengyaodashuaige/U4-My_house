package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.Type;
import com.team.house.service.DistrictService;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController// == @Controller + @ResponseBody ：表示后期的方法全部返回异步数据
@RequestMapping("/admin") //指定请求前缀
public class TypeController {

    @Autowired
    private TypeService typeService;

    //分页查询显示
    @RequestMapping("/getTypeData")
    //@ResponseBody  //把Java对象转换成Json格式
    public Map<String,Object> getTypeData(PageUtil pageUtil){
        //调用业务查询区域数据
        PageInfo<Type> pageInfo = typeService.getTypeByPage(pageUtil);
        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //添加
    @RequestMapping("addType")
    public String addType(Type type){
        try {
            //调用业务
            int result = typeService.addType(type);

            //封装返回数据
            /* 方法一：自动转换为json格式
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("result",result);
            */
            //方法二：使用拼接的json格式
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    //根据id查询单条数据信息
    @RequestMapping("getType")
    public Type getType(Integer id){
        try {
            //调用业务
            Type type = typeService.getTypeByID(id);
            return type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //实现修改功能
    @RequestMapping("updateType")  //一定要传id、name
    public String updateType(Type type){
        try {
            //调用业务
            int result = typeService.updateType(type);
            //封装返回数据
            /* 方法一：自动转换为json格式
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("result",result);
            */
            //方法二：使用拼接的json格式
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    //根据id删除
    @RequestMapping("delType")
    public String delType(Integer id){
        try {
            //调用业务
            typeService.deleteType(id);
            //封装返回数据
            /* 方法一：自动转换为json格式
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("result",result);
            */
            //方法二：使用拼接的json格式
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    //实现区域批量删除
    //一、前台传递数据的格式：ids=1，2，3   后台：String ids变量接收数据
    //二、前台传递数据的格式：ids=1&ids=2&ids=3   后台：Integer[] ids变量接收数据
    @RequestMapping("delMoreType")
    public String delMoreType(String ids){
        try {
            //将字符串转化为数据组
            String[] strList = ids.split(",");
            Integer[] idList = new Integer[strList.length];
            for (int i=0;i<strList.length;i++){
                idList[i] = new Integer(strList[i]);
            }
            //调用业务
            int temp = typeService.deleteMoreType(idList);
            //封装返回数据
            return "{\"result\":"+temp+"}";  //拼接的json格式
        }catch (Exception e){
            e.printStackTrace();  //使用日志工具记录
            return "{\"result\":-1}";
        }
    }

}
