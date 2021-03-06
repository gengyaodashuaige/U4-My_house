$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getHouse',
        pagination:true,  //开启分页
        pageSize:5,  //初始化页大小
        pageList:[3,5,8,10,15,20],  //页大小选项
        toolbar:'#ToolBar',
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:130,align:'center'},
            {field:'title',title:'标题',width:100,align:'center'},
            {field:'dname',title:'区域名称',width:100,align:'center'},
            {field:'sname',title:'街道',width:100,align:'center'},
            {field:'tname',title:'房屋类型',width:100,align:'center'},
            {field:'price',title:'价格',width:100,align:'center'},
            {field:'floorage',title:'面积',width:85,align:'center'},
            {field:'ispass',title:'状态',width:100,align:'center',
                formatter: function(value,row,index){
                    //返回的内容就是呈现在单元格的内容
                    //value表示当前字段的值，row当前行的值，index表示行索引
                    return value==0?"未审核":"已审核";
                }
            },
            {field:'opt',title:'编辑|操作',width:150,align:'center',
                formatter: function(value,row,index){
                    //返回的内容就是呈现在单元格的内容
                    //value表示当前字段的值，row当前行的值，index表示行索引
                    return "<a href='javascript:goPass("+row.id+")'>确认审核</a>  " +
                           "<a href='javascript:delType("+row.id+")'>详情</a>  " +
                           "<a href='javascript:backPass("+row.id+")'>取消</a>";
                }
            }
        ]]
    });
});

//通过出租房审核
function goPass(id) {
    //发送异步请求   state=1表示审核通过
    $.post("updatePassState",{"id":id,"state":1},function (data) {
        if (data.result>0){
            $('#dg').datagrid('reload'); //刷新datagrid
        }else {
            $.messager.alert('友情提示:','审核失败，请联系管理员：18772790567','info');
        }

    },"json");
}
//取消已审核的出租房
function backPass(id) {
    //发送异步请求   state=1表示审核通过
    $.post("updatePassState",{"id":id,"state":0},function (data) {
        if (data.result>0){
            $('#dg').datagrid('reload'); //刷新datagrid
        }else {
            $.messager.alert('友情提示:','审核失败，请联系管理员：18772790567','info');
        }

    },"json");
}

//实现datagrid绑定查询条件
function searchUser() {
    var inputName=$("#inputName").val();
    var inputTel=$("#inputTel").val();

    //设置条件查询
    $("#userData").datagrid("load",{
        //设置查询条件  和实体对应
        name: inputName,
        tel: inputTel
    });
}

/*
//打开添加窗口
function goAdd() {
    //打开对话框
    $("#AddDialog").dialog("open").dialog('setTitle',"添加类型");
}

//关闭弹窗  //通过dialogId关闭窗口
function CloseDialog(dialogId){
    $("#"+dialogId).dialog("close");
}
//编辑列中的修改
function goEdit(id) {
    $("#upDialog").dialog("open").dialog('setTitle',"编辑类型");
    //发送异步请求，回显
    $.post("getType",{"id":id},function (data) {
        //data对象的属性名和表单对象的名称相同，即可回显
        $("#upDialogForm").form('load',data);
    },"json")
}*/
