/* 
* @Author: anchen
* @Date:   2017-08-15 16:30:27
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-15 20:49:18
*/

$(function(){

// 改变左上角title 用于导航
$('.easyui-layout').layout('panel','center').panel({
    title: '首页 > 院系管理 > 添加学院',
    iconCls: 'icon-page'
});
$('.content_div').panel({
    title: '学院信息',
    width: 500
});
$('#name').validatebox({
    required: true
});
// 定义表单提交的事件
$('.fm').form({
    url: '',
    // 提交前事件定义  progress的进度条防止重复提交 + 表单中validbox的确认验证

    onSubmit: function(){
        $.messager.progress();
        var isValid = $(this).form('validate');
        if (!isValid) {
            $.messager.progress('close');
        }
        console.log(isValid);
        return isValid;
    },
    // 有返回值触发success JSON.parse看情况去留
    success: function(data){
        var result = JSON.parse(data),
            msg = '';

        $.messager.progress('close');
        if (result.success) {
            // msg = '成功!';
        }else{
            // msg = result.msg;
        }
        $.messager.show({
            title: '执行结果',
            msg: msg
        });
    }
});
// 保存点击事件
$('.fm_save').click(function(event) {
    $('.fm').submit();
});
// 清空点击则清空
$('.fm_clear').click(function(event) {

    $('.fm').form('clear');
});
});