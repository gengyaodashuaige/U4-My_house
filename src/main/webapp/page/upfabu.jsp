﻿<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript" type="text/javascript">

      $(function () {//加载
          //一、发送异步请求。获取请求类型，进行加载
          $.post("getTypeData",null,function (data) {
              //加载类型
              //[{"id":1000,"name":"一室一厅"},{"id":1001,"name":"一室两厅"},{"id":1002,"name":"两室一厅"},{"id":1003,"name":"两室两厅"},{"id":1004,"name":"三室一厅"},{"id":1005,"name":"三室两厅"},{"id":1006,"name":"四室一厅"},{"id":1007,"name":"四室两厅"},{"id":1008,"name":"四室三厅"}]
              for (var i=0;i<data.length;i++){
                  //创建option
                  var optionnode=$("<option value="+data[i].id+" >"+data[i].name+"</option>");
                      //讲option添加到下拉列表
                      $("#typeid").append(optionnode);
              }

              //设置类型选中项
              $("#typeid").val(${house.typeId});
          },"json");

          //二、发送异步请求加载区域信息
          $.post("getDistrictData",null,function (data) {
              //加载区域
              for (var i=0;i<data.length;i++){
                  //创建option
                  var optionnode=$("<option value="+data[i].id+" >"+data[i].name+"</option>");
                  //讲option添加到下拉列表
                  $("#district_id").append(optionnode);
              }

              //1、设置区域选中项
              $("#district_id").val(${house.districtid});
              //2、加载对应区域下的街道
              //取当前区域选项的id
              var did = $("#district_id").val();
              //清空原有选项
              $("#street_id>option:gt(0)").remove();
              //发送异步请求加载街道
              $.post("getStreetData",{"did":did},function (data) {
                  //加载区域
                  for (var i=0;i<data.length;i++){
                      //创建option
                      var optionnode=$("<option value="+data[i].id+" >"+data[i].name+"</option>");
                      //讲option添加到下拉列表
                      $("#street_id").append(optionnode);
                  }

                  //3、设置街道选中项的回显
                  /*异步请求需要时间，但是代码会继续往下执行，导致不同步，所以要放在异步请求中*/
                  $("#street_id").val(${house.streetId});
              },"json");

          },"json");

          //三、二级联动给区域下拉选项添加改变事件
          $("#district_id").change(function () {
              loadStreet();
          })
      });

      //通过区域加载对应街道
      function loadStreet() {
          //取当前区域选项的id
          var did = $("#district_id").val();
          //清空原有选项
          $("#street_id>option:gt(0)").remove();
          //发送异步请求加载街道
          $.post("getStreetData",{"did":did},function (data) {
              //加载区域
              for (var i=0;i<data.length;i++){
                  //创建option
                  var optionnode=$("<option value="+data[i].id+" >"+data[i].name+"</option>");
                  //讲option添加到下拉列表
                  $("#street_id").append(optionnode);
              }
          },"json");
      }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>更新房屋信息发布</DT>
  <DD class=past>更改房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action enctype="multipart/form-data" method=post name=add.action
action=/page/updateHouse>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <input type="hidden" name="id" value="${house.id}">
    <input type="hidden" name="oldPath" value="${house.path}">
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text value="${house.title}" type=text name=title> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId id="typeid">
        <OPTION value="">=请选择=</OPTION>
        </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text value="${house.floorage}"
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD> <INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type="date" name=pubdate value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></f:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>
      区：<SELECT class=text id="district_id" name=district_id>
            <OPTION value="">=请选择=</OPTION>
         </SELECT>
      街：<SELECT class=text name=streetId id="street_id">
            <OPTION value="">=请选择=</OPTION>
         </SELECT>
    </TD>
  </TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD>
  </TR>
  <TR>
    <TD class=field>房屋图片：</TD>
    <TD><img width="100" height="100" src="http://localhost:80/${house.path}"><input type="file" name="pfile"></TD>
  </TR>
  </TBODY></TABLE>
<DIV class=buttons><INPUT value=更新 type="submit">
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
