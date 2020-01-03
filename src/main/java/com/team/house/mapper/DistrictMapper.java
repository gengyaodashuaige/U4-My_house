package com.team.house.mapper;

import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    /**
     * 实现区域批量删除
     * @param ids 批量删除的id
     * @return 影响行数
     */
    public int deleteMoreDistrict(Integer [] ids);
    //public int deleteMoreDistrict(List<Integer> ids);
}