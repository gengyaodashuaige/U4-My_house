package com.team.house.controller;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //在后台显示所有出租房信息，等待审核
    @RequestMapping("/getHouse")
    @ResponseBody
    public Map<String,Object> getHouse(PageUtil pageUtil){
        //调用业务获取数据
        PageInfo<House> pageInfo = houseService.getBackHouseAll(pageUtil);
        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //查询所有通过审核的出租房
    @RequestMapping("/getPassHouse")
    @ResponseBody
    public Map<String,Object> getPassHouse(PageUtil pageUtil){
        //调用业务获取数据
        PageInfo<House> pageInfo = houseService.getPassHouseAll(pageUtil);
        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //更新出租放的审核状态
    @RequestMapping("/updatePassState")
    @ResponseBody
    public Map<String,Object> updatePassState(String id,Integer state){
        //调用业务获取数据
        int i = houseService.PassHouse(id, state);
        //封装返回数据
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result",i);
        return map;
    }
}
