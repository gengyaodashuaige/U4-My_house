package com.team.house.service.impl;

import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "streetServiceImpl2")
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    //查询对应区域下的所有街道
    public List<Street> getStreetByDistrict(Integer did) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        //添加条件
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }


    //测试业务的方法
    public static void main(String[] args) {
        //创建spring容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //找对象调用
        StreetService streetService=(StreetService)ctx.getBean("streetServiceImpl2");
        System.out.println("街道个数是："+streetService.getStreetByDistrict(1004).size());
    }
}
