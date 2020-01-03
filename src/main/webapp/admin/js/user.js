$(function(){
    //1.使用datagrid组件绑定数据
    $('#userData').datagrid({
        url:'getUserData',
        pagination:true,  //开启分页
        pageSize:5,  //初始化页大小
        pageList:[3,5,8,10,15,20],  //页大小选项
        toolbar:'#ToolBar',
        columns:[[
            {checkbox:true,width:100,align:'center'},
            {field:'id',title:'编号',width:100,align:'center'},
            {field:'name',title:'用户名',width:100,align:'center'},
            {field:'telephone',title:'电话',width:100,align:'center'},
            {field:'isadmin',title:'是否是管理员',width:100,align:'center'},
            {field:'opt',title:'编辑|操作',width:100,align:'center',
                formatter: function(value,row,index){
                    //返回的内容就是呈现在单元格的内容
                    //value表示当前字段的值，row当前行的值，index表示行索引
                    return "<a href='javascript:goEdit("+row.id+")'>修改</a>  <a href='javascript:delType("+row.id+")'>删除</a>";
                }
            }
        ]]
    });
});

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