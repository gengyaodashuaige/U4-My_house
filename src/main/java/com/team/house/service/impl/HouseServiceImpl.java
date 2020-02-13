package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.util.PageUtil;
import com.team.house.util.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    //添加出租房
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    //查询当前用户发布了所有出租房
    public PageInfo<House> getHouseByUser(Integer id, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list = houseMapper.getHouseByUser(id);
        return new PageInfo<House>(list);
    }

    //查询某出租房的信息
    public House getHouse(String id) {
        return houseMapper.getHouse(id);
    }

    //修改出租房信息
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //更新出租房删除的状态
    public int deleteHouse(String houseId, Integer delState) {
        House house = new House();
        house.setId(houseId);  //条件
        house.setIsdel(delState);  //更新状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //在后台显示所有出租房信息，等待审核
    public PageInfo<House> getBackHouseAll(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list = houseMapper.getBackHouseAll();
        return new PageInfo<House>(list);
    }

    //查询所有通过审核的出租房
    public PageInfo<House> getPassHouseAll(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> list = houseMapper.getPassHouseAll();
        return new PageInfo<House>(list);
    }

    //更新出租房的审核状态
    public int PassHouse(String houseId, Integer passState) {
        House house = new House();
        house.setId(houseId);  //条件
        house.setIspass(passState);  //更新状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //分页查询所有的出租房
    public PageInfo<House> findAllHouse(SearchCondition searchCondition) {
        //开启分页
        PageHelper.startPage(searchCondition.getPage(),searchCondition.getRows());
        List<House> list = houseMapper.findAllHouse(searchCondition);
        return new PageInfo<House>(list);
    }

    //查询出租房详细信息
    public House findHouse(String id) {
        return houseMapper.findHouseByID(id);
    }
}
