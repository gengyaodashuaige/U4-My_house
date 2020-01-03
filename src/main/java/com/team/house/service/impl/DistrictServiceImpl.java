package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;
import com.team.house.mapper.DistrictMapper;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.DistrictService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional  //所有方法都支持事务：只能都成功、或都不成功
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    //分页查询区域
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)//挂起事务，不用事务
    public PageInfo<District> getDistrictByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);

        return new PageInfo<District>(list);
    }

    //添加区域
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    //根据id查询单条数据信息
    public District getDistrictByID(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    //实现修改功能
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    //根据id删除
    @Transactional  //所有方法都支持事务：只能都成功、或都不成功
    //如果方法不报错，则事务管理器会提交。报错时，事务管理器会回滚
    public void deleteDistrict(Integer id) {
        //第一步：删除区域下的街道    编写dao层，提供删除街道的方法
        streetMapper.delStreetByDistritc(id);
        //第二步：删除区域
        districtMapper.deleteByPrimaryKey(id);
    }

    //实现批量删除
    public int deleteMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    //查询所有区域
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

}
