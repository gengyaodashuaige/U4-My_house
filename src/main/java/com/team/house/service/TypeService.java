package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.PageUtil;

import java.util.List;

public interface TypeService {

      /**
     * 分页查询区域
     * @param pageUtil
     *    page属性接收页码，rows属性接收页大小
     * @return
     */
    public PageInfo<Type> getTypeByPage(PageUtil pageUtil);

     /**
     * 添加区域
     * @param type 区域实体信息
     * @return 影响行数
     */
    public int addType(Type type);

     /**
     * 根据id查询单条数据信息
     * @param id  主键编号
     * @return  单条实体对象
     */
    public Type getTypeByID(Integer id);

     /**
     * 实现修改功能
     * @param type 区域实体
     * @return 影响行数
     */
    public int updateType(Type type);

     /**
     *  根据id删除
     * @param id  区域编号
     * @return  影响行数
     */
    public void deleteType(Integer id);

     /**
     * 实现批量删除
     * @param ids  批量删除的id
     * @return  影响行数
     */
    public int deleteMoreType(Integer[] ids);

    /**
    * 查询所有
    * @return
    */
    public List<Type> getAllType();
}
