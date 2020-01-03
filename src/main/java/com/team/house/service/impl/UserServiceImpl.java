package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UserService;
import com.team.house.util.MD5Utils;
import com.team.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王建兵
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/12/24 10:38
 * @Created by Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    //分页查询区域
    public PageInfo<Users> getUsersByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        //查询所有用户
        UsersExample usersExample = new UsersExample();
        //封装条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //动态sql:一个属性一个判断
        if(condition.getName()!=null)
            criteria.andNameLike("%"+condition.getName()+"%");
        if(condition.getTel()!=null)
            criteria.andTelephoneLike("%"+condition.getTel()+"%");
        List<Users> list=usersMapper.selectByExample(usersExample);
        return new PageInfo<Users>(list);
    }

    //检查用户名是否存在
    public boolean checkUname(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0")); //保证房东用户
        criteria.andNameEqualTo(name);
        List<Users> list=usersMapper.selectByExample(usersExample);
        //如果当集合为null说明没有查询到记录,用户名可用
        if(list!=null&&list.size()!=0){
            return false;
        }
        else
        return true;
    }

    //注册用户
    public int addUser(Users users) {
        //出于系统用户安全考虑_对密码进行加密码
        //使用MD5工具类对密码加密
          System.out.println("未加密的密码:"+ users.getPassword());
        String newPassword= MD5Utils.md5Encrypt(users.getPassword());
          System.out.println("加密的密码:"+ newPassword);
        users.setPassword(newPassword);
        //设置户东用户
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    //用户登录
    public Users login(String usernmae, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0")); //保证房东用户
        criteria.andNameEqualTo(usernmae);  //用户名
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));  //密码

        List<Users> list=usersMapper.selectByExample(usersExample);
        if(list!=null&& list.size()>0)
        {
            return list.get(0);  //返回登入的人
        }
        return null;
    }
}
