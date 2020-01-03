package com.team.house.mapper;

import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询当前用户发布了所有出租房
    List<House> getHouseByUser(@Param("userid") Integer userid);

    //查询单条出租房数据
    House getHouse(String id);

    //在后台显示所有出租房信息，等待审核
    List<House> getBackHouseAll();

    //查询所有通过审核的出租房
    List<House> getPassHouseAll();
}