<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
    <script language="JavaScript" type="text/javascript">

        $(function () {//加载
            //1\发送异步请求。获取请求类型，进行加载
            $.post("getTypeData",null,function (data) {
                //加载类型
                //[{"id":1000,"name":"一室一厅"},{"id":1001,"name":"一室两厅"},{"id":1002,"name":"两室一厅"},{"id":1003,"name":"两室两厅"},{"id":1004,"name":"三室一厅"},{"id":1005,"name":"三室两厅"},{"id":1006,"name":"四室一厅"},{"id":1007,"name":"四室两厅"},{"id":1008,"name":"四室三厅"}]
                for (var i=0;i<data.length;i++){
                    //创建option
                    var optionnode=$("<option value="+data[i].id+" >"+data[i].name+"</option>");
                    //讲option添加到下拉列表
                    $("#typeid").append(optionnode);
                }
            },"json");

            //2、发送异步请求加载区域信息
            $.post("getDistrictData",null,function (data) {
                //加载区域
                for (var i=0;i<data.length;i++){
                    //创建option
                    var optionnode=$("<option value="+data[i].id+" >"+data[i].name+"</option>");
                    //讲option添加到下拉列表
                    $("#district_id").append(optionnode);
                }
            },"json");

            //3、二级联动给区域下拉选项添加改变事件
            $("#district_id").change(function () {
                //取当前区域选项的id
                var did = $(this).val();
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
            })
        });

        //实现分页导航-带条件-的跳转
        //pageNum表示页码
        function goPage(pageNum) {
            //设置页码(把页码保存到表单的隐藏域中)
            $("#savePage").val(pageNum);
            //alert(pageNum);
            //提交表单  跳转==传条件(条件+页码)
            document.getElementById("fsss").submit();
        }
    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id="fsss" name="from1" method="post" action="/page/showAllHouse">
      <%--隐藏域保存当前页码--%>
         <input type="hidden" value="1" name="page" id="savePage">
    标题：<input type="text" name="title">
    区域：<select id="district_id" name="did"><option value="">请选择</option></select>
    街道：<select id="street_id" name="sid"><option value="">请选择</option></select>
    类型：<select id="typeid" name="tid"><option value="">请选择</option></select>
    价格：<input type="text" size="10" value="${searchCondition.startPrice}" name="startPrice">-<input type="text" size="10" value="${searchCondition.startPrice}" name="endPrice">
    <input type="submit" value="搜索" name="submitz">
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="p">
  <TR>
    <TD class=house-thumb><span><a href="/page/findHouse?id=${p.id}" target="_blank">
          <img src="http://localhost:80/${p.path}" width="100" height="75" alt=""></a></span>
    </TD>
      <TD>
      <DL>
        <DT><a href="/page/findHouse?id=${p.id}" target="_blank">${p.title}</a></DT>
        <DD>${p.dname}${p.sname},${p.floorage}平米<BR>联系方式：${p.contact} </DD></DL></TD>
    <TD class=house-type>${p.tname}${p.id}ZZ</TD>
    <TD class=house-price><SPAN>${p.price}</SPAN>元/月</TD></TR>
  </c:forEach>
  <TR>无租房信息</TR></TBODY></TABLE>
<DIV class=pager>
  <UL>
      <LI class=current><a href="javascript:goPage(1)">首页</a></LI>
      <LI><a href="javascript:goPage(${pageInfo.prePage})">上一页</a></LI>
      <LI><a href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</a></LI>
      <LI><a href="javascript:goPage(${pageInfo.pages})">尾页</a></LI>
  </UL><SPAN class=total>${pageInfo.pageNum==0?1:pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
