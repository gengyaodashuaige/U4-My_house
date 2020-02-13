package com.team.house.pcontroller;


import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.util.FileUploadUtil;
import com.team.house.util.PageUtil;
import com.team.house.util.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller(value = "houseController2")
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //处理请求的方式
    @RequestMapping("addHouse")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String addHouse(House house,HttpSession session,
                           @RequestParam(value="pfile",required=false)CommonsMultipartFile pfile){
        try {
            //1、实现文件上传
            /*System.out.println("上传文件名称："+pfile.getOriginalFilename());
            System.out.println("上传文件大小："+pfile.getSize());
            System.out.println("上传文件的类型："+pfile.getContentType());*/

            //上传
            //获取文件的扩展名
            String saveFileName=FileUploadUtil.upload(pfile);

            //2、将数据添加到数据库
            //修改house实体，添加额外的属性值
            //设置编号
            house.setId(System.currentTimeMillis()+"");

            //设置所属用户  重点！！
            Users users = (Users) session.getAttribute("loginInfo");
            house.setUserId(users.getId());

            //设置图片路径
            house.setPath(saveFileName);

            //调用业务实现添加
            houseService.addHouse(house);
//            return "page/guanli";
            return "redirect:showHouse";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    //查询当前用户发布了所有出租房
    @RequestMapping("showHouse")  //pageUtil只用接收页码即可，page
    public String showHouse(PageUtil pageUtil,HttpSession session,  Model model){
        //设置页大小    选择设置默认值
        pageUtil.setRows(5);

        //调用业务层，获取数据
        //获取用户登录的id
        Users users = (Users) session.getAttribute("loginInfo");
        //Integer userid = 1012;  //固定法
        PageInfo<House> pageInfo = houseService.getHouseByUser(users.getId(), pageUtil);
        //将数据填充到作用域
        model.addAttribute("pageInfo",pageInfo);
        return "page/guanli";
    }

    //显示修改出租房信息
    @RequestMapping("editHouse")
    public String editHouse(String id,Model model){
        //调用业务获取数据
        House house = houseService.getHouse(id);
        model.addAttribute("house",house);
        return "page/upfabu";
    }

    //修改出租房信息
    @RequestMapping("updateHouse")
    //一个表单对象对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartFile对象对应
    public String updateHouse(House house,String oldPath,
                           @RequestParam(value="pfile",required=false)CommonsMultipartFile pfile){
        try {
            //1、判断图片有没有选择？  如果选中图片上传，否则不上传
            if (!pfile.getOriginalFilename().equals("")){
                //System.out.println("上传图片");
                String savaFileName=FileUploadUtil.upload(pfile);
                //设置修改实体图片的路径
                house.setPath(savaFileName);

                //删除旧图
                /*File file = new File("f:\\images\\"+oldPath);
                file.delete();*/
                FileUploadUtil.deleteFile(oldPath);
            }

            //2、修改数据信息
            //调用业务实现添加
            houseService.updateHouse(house);

            return "redirect:showHouse";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    //更新出租房删除的状态
    @RequestMapping("deleteHouse")//id为出租房编号
    public String deleteHouse(String id){
        try {
            //调用业务删除数据     1表示删除
            int i = houseService.deleteHouse(id, 1);
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    //恢复租房
    @RequestMapping("backHouse")//id为出租房编号
    public String backHouse(String id){
        try {
            //调用业务恢复出租房数据   0表示不删除
            int i = houseService.deleteHouse(id, 0);
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //分页查询所有的出租房
    @RequestMapping("showAllHouse")  //pageUtil只用接收页码即可，page
    public String showAllHouse(SearchCondition searchCondition, Model model){
        //设置页大小    选择设置默认值
        searchCondition.setRows(5);
        //调用业务层，获取数据
        PageInfo<House> pageInfo = houseService.findAllHouse(searchCondition);
        //将数据填充到作用域
        model.addAttribute("pageInfo",pageInfo);
        return "page/list";
    }

    //查询出租房详细信息
    @RequestMapping("findHouse")
    public String showHouseAll(String id, Model model){
        //调用业务层，获取数据
        House house = houseService.findHouse(id);
        //将数据填充到作用域
        model.addAttribute("house",house);
        return "page/details";
    }

}
