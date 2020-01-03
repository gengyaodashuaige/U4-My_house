package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional  //所有方法都支持事务：只能都成功、或都不成功
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private StreetMapper streetMapper;

    //分页查询区域
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)//挂起事务，不用事务
    public PageInfo<Type> getTypeByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);

        return new PageInfo<Type>(list);
    }

    //添加区域
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    //根据id查询单条数据信息
    public Type getTypeByID(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    //实现修改功能
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    //根据id删除
    @Transactional  //所有方法都支持事务：只能都成功、或都不成功
    //如果方法不报错，则事务管理器会提交。报错时，事务管理器会回滚
    public void deleteType(Integer id) {
        //第一步：删除区域下的街道    编写dao层，提供删除街道的方法
        streetMapper.deleteByPrimaryKey(id);
        //第二步：删除区域
        typeMapper.deleteByPrimaryKey(id);
    }

    //实现批量删除
    public int deleteMoreType(Integer[] ids) {
        return typeMapper.deleteMoreType(ids);
    }

    //查询所有
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
