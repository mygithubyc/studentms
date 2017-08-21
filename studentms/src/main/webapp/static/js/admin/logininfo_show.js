$(function(){
	// 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 系统管理 > 查看登录记录',
        iconCls: 'icon-page'
    });
    $('#dg').datagrid({
        url: '',
        height: 750,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        rownumbers: true,
        columns:[[
            {field: 'a', title: '用户名', width: 100},
            {field: 'b', title: 'IP', width: 100},
            {field: 'c', title: 'MAC', width: 100},
            {field: 'd', title: '开始时间', width: 100},
            {field: 'e', title: '结束时间', width: 100},
            {field: 'f', title: '职位', width: 100},
        ]],
        
        toolbar:'#toolbar',
        })
});
function doSearch(){
    var username = $('#username').val(),
        from_time = $('#from_time').val(),
        to_time = $('#to_time').val();
    alert(username+'/'+from_time+'/'+to_time);
}