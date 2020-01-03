$(function(){
    //1.使用datagrid组件绑定数据
    $('#districtData').datagrid({
        url:'getDistrictData',
        pagination:true,  //开启分页
        pageSize:5,  //初始化页大小
        pageList:[3,5,8,10,15,20],  //页大小选项
        toolbar:'#ToolBar',
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:100,align:'center'},
            {field:'name',title:'区域名称',width:100,align:'center'},
            {field:'opt',title:'编辑|操作',width:100,align:'center',
                formatter: function(value,row,index){
                    //返回的内容就是呈现在单元格的内容
                    //value表示当前字段的值，row当前行的值，index表示行索引
                    return "<a href='javascript:goEdit("+row.id+")'>修改</a>  <a href='javascript:delDistrict("+row.id+")'>删除</a>";
                }
            }
        ]]
    });
});

//打开添加窗口
function goAdd() {
    //打开对话框
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}

//关闭弹窗  //通过dialogId关闭窗口
function CloseDialog(dialogId){
    $("#"+dialogId).dialog("close");
}
/* function CloseDialog() {
     $("#AddDialog").dialog("close");
 }*/

//保存添加的数据   异步添加
function SaveDialog() {
    //alert("要保存信息，先告诉我接口在哪儿？我去找它！")
    //使用异步技术实现添加，借助ajax技术

    /*方法一：使用jquery发送异步请求
        $.post("地址",参数,回调函数,"返回数据的格式")
        //将表单序列化参数数据
        var parm = $("addDialogForm").serialize();
        // console.log(parm);
        $.post("addDistrict",parm,function (data) {
            if (data.result>0){
                //添加成功，关闭窗口
                $("#AddDialog").dialog("close");
            }else {
                $.messager.alert('友情提示:','添加失败，请联系管理员：18772790567','info');
            }
        },"json");*/
    //方法二：借助easyui异步提交表单
    $('#addDialogForm').form('submit',{
        url:"addDistrict",
        success:function(data){     //{"result":1}
            var obj=$.parseJSON(data);//将json字符串转化为json对象
            if (obj.result>0){
                //添加成功，关闭窗口
                $("#AddDialog").dialog("close");
            } else {
                $.messager.alert('友情提示:','添加失败，请联系管理员：18772790567','info');
            }
        }
    });
}

//打开修改弹窗
function goUpdate() {
    //1、获取datagrid的选中行
    //getSelections：返回所有被选择的行，当没有记录被选择时，将返回一个空数组。
    var selObjs = $("#districtData").datagrid("getSelections");
    //2、验证是否选中一行
    if (selObjs.length==1){
        $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");

        //还原表单数据
        /* 1、如果表格中的数据支持修改窗口的数据展示：无需查询数据库
            查询数据库，通过id获取单行记录的对象，进行回显
           $("#upDialogForm").form('load',json对象：{"表单对象名称":值});
           selObjs[0]：[{id:值,name:"值"},{...},...]   id索引为0
        $("#upDialogForm").form('load',selObjs[0]);
           */

        //2、如果表格中的数据不支持修改窗口的数据展示：通过主键查询单条记录
        //使用post方式发送异步请求
        var id = selObjs[0].id;
        $.post("getDistrict",{"id":id},function (data) {
            // console.log(date);
            //data对象的属性名和表单对象的名称相同，即可回显
            $("#upDialogForm").form('load',data);
        },"json")


    }else {
        $.messager.alert('友情提示：','请选中其中一行！')
    }

}

//编辑列中的修改
function goEdit(id) {
    $("#upDialog").dialog("open").dialog('setTitle',"编辑区域")
    //发送异步请求，回显
    $.post("getDistrict",{"id":id},function (data) {
        //data对象的属性名和表单对象的名称相同，即可回显
        $("#upDialogForm").form('load',data);
    },"json")
}

//实现修改的更新数据
function updateSaveDialog() {
    $('#upDialogForm').form('submit',{
        url:"updateDistrict",
        success:function(data){     //{"result":1}
            var obj=$.parseJSON(data);//将json字符串转化为json对象
            if (obj.result>0){
                //添加成功,先刷新再关闭窗口
                $('#districtData').datagrid('reload');//刷新datagrid
                $("#upDialog").dialog("close");
            } else {
                $.messager.alert('友情提示:','修改失败，请联系管理员：18772790567','info');
            }
        }
    });
}

//超链接删除区域
function delDistrict(id) {
    if (confirm("是否确认删除？")){
        //使用jquery的post方式发送异步请求
        $.post("delDistrict",{"id":id},function (data) {
            if (data.result>0){
                $('#districtData').datagrid('reload');//刷新datagrid
            } else {
                $.messager.alert('友情提示:','删除失败，请联系管理员：18772790567','info');
            }
        },"json");
    }
}

//批量删除
function DeleteByFruitName() {
    //获取datagrid的选中行
    var selObjs = $("#districtData").datagrid("getSelections");
    //判断有没有选中项
    if (selObjs.length>0){
        //确认提示框
        $.messager.confirm('友情提示:','确定要删除吗？',function (r) {
            if (r){ //r=true 表示点击OK，否则点击取消
                //发送异步请求，调用接口，实现批量删除  ids=1，2，3，
                //1、获取选中项的值id，拼接成：值1，值2，值3
                var str = "";
                for (var i=0;i<selObjs.length;i++){
                    str = str + selObjs[i].id + ",";
                }
                str = str.substring(0,str.length-1);

                //2、发送异步请求
                $.post("delMoreDistrict",{"ids":str},function (data) {
                    if (data.result>0){
                        $('#districtData').datagrid('reload');//刷新datagrid
                    } else {
                        $.messager.alert('友情提示:','批量删除失败，请联系管理员：18772790567','info');
                    }
                },"json");
            }
        });
    } else {
        $.messager.alert('友情提示','请至少选择一行进行删除!','info');
    }
}