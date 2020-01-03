package com.team.house.pcontroller;


import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
@RequestMapping("/page/")
public class DistrictController {

    //调用业务
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrictData")
    @ResponseBody
    public List<District> getDistrictData(){
        return districtService.getAllDistrict();
    }
}
