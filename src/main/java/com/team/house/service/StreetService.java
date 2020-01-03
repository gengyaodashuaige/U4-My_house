package com.team.house.service;

import com.team.house.entity.Street;

import java.util.List;

public interface StreetService {

    /**
    * 查询对应区域下的所有街道
    * @return
    */
    public List<Street> getStreetByDistrict(Integer did);
}
