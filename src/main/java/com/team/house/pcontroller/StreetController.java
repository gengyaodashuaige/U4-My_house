package com.team.house.pcontroller;


import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "streetController2")
@RequestMapping("/page/")
public class StreetController {

    //调用业务
    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreetData")
    @ResponseBody
    public List<Street> getStreetData(Integer did){
        return streetService.getStreetByDistrict(did);
    }
}
