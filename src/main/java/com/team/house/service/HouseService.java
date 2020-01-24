package com.team.house.service;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.PageUtil;
import com.team.house.util.SearchCondition;

import java.util.List;

public interface HouseService {

    /**
     * 添加出租房
     * @param house  出租房实体
     * @return
     */
    public int addHouse(House house);

    /**
     * 查询当前用户发布了所有出租房
     * @param id  用户编号
     * @return  出租房信息
     */
    PageInfo<House> getHouseByUser(Integer id, PageUtil pageUtil);

    /**
     * 查询某出租房的信息
     * @param id  编号
     * @return  出租房实体
     */
    House getHouse(String id);

    /**
     * 修改出租房信息
     * @param house  出租房实体
     * @return  影响行数
     */
    int updateHouse(House house);

    /**
     * 更新出租房删除的状态
     * @param houseId  出租房编号
     * @param delState  删除的状态
     *  状态为1：表示删除出租房     状态为0：表示恢复出租房，不删除
     * @return  影响行数
     */
    public int deleteHouse(String houseId,Integer delState);

    /**
     * 在后台显示所有出租房信息，等待审核
     * @param pageUtil  分页参数
     * @return  出租房信息
     */
    public PageInfo<House> getBackHouseAll(PageUtil pageUtil);
    /**
     * 查询所有通过审核的出租房
     * @param pageUtil  分页参数
     * @return  出租房信息
     */
    public PageInfo<House> getPassHouseAll(PageUtil pageUtil);

    /**
     * 更新出租房的审核状态
     * @param houseId  出租房编号
     * @param passState  状态
     * @return  影响行数
     */
    public int PassHouse(String houseId,Integer passState);

    /**
     * 分页查询所有的出租房
     * @param searchCondition  分页参数+封装的实体
     * @return  出租房实体
     */
//    public PageInfo<House> findAllHouse(PageUtil pageUtil);
    public PageInfo<House> findAllHouse(SearchCondition searchCondition);
}
