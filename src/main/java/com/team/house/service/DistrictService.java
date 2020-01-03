package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.util.PageUtil;

import java.util.List;

public interface DistrictService {

     /**
     * 分页查询区域
     * @param pageUtil
     *    page属性接收页码，rows属性接收页大小
     * @return
     */
    public PageInfo<District> getDistrictByPage(PageUtil pageUtil);

     /**
     * 添加区域
     * @param district 区域实体信息
     * @return 影响行数
     */
    public int addDistrict(District district);

     /**
     * 根据id查询单条数据信息
     * @param id  主键编号
     * @return  单条实体对象
     */
    public District getDistrictByID(Integer id);

     /**
     * 实现修改功能
     * @param district 区域实体
     * @return 影响行数
     */
    public int updateDistrict(District district);

     /**
     *  根据id删除
     * @param id  区域编号
     * @return  影响行数
     */
    public void deleteDistrict(Integer id);

     /**
     * 实现批量删除
     * @param ids  批量删除的id
     * @return  影响行数
     */
    public int deleteMoreDistrict(Integer[] ids);

    /**
     * 查询所有区域
     * @return
     */
    public List<District> getAllDistrict();
}
