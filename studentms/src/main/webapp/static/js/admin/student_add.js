/* 
* @Author: anchen
* @Date:   2017-08-15 22:56:41
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 15:51:49
*/

$(function(){

    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 学生管理 > 录入学生信息',
        iconCls: 'icon-page'
    });
    $('.content_div').panel({
        title: '学生信息',
        width: 500
    });
    $('#fm_student_name').validatebox({
        required: true,
        validateOnCreate: false
    });
    $('#fm_student_num').blur(function(event) {
        var student_num = $(this).val(),
            validate_span = $(this).next('span');
        console.log(validate_span);
        if(student_num.length !== 0){
            if(check_student_num(student_num)){
                validate_span.html('可用');
            }else{
                validate_span.html('学号已存在,请确认!');
            }
        }
    });
    
    $('#fm_student_birth').datebox();
    $('#fm_student_entrance').datetimebox();
    $('#fm_id').validatebox({
        required: true,
        validateOnCreate: false
    });
    $('#fm_academic_year').numberbox({
        required: true,
        validateOnCreate: false,
        max: 8
    });
    // 三级联动   分别是学院 系 班级  注意修改 id 和 text(根据返回的json对象的key)
    $('#fm_college_name').combobox({
        editable: false,
        url: '',
        valueField: 'id',
        textField: 'text',
        onSelect: function(rec){
            $('#fm_class_name').combobox('clear');
            $('#fm_depart_name').combobox('reload', 'xxx?id='+rec.id);
        }
    });
    $('#fm_depart_name').combobox({
        editable: false,
        valueField: 'id',
        textField: 'text',
        onSelect: function(rec){
            $('#fm_class_name').combobox('reload', 'xxx?id='+rec.id);
        }
    });
    $('#fm_class_name').combobox({
        editable: false,
        valueField: 'id',
        textField: 'text',
    });
    // 定义表单提交的事件
    $('.fm').form({
        
        url: 'xxx',
        // 提交前事件定义  progress的进度条防止重复提交 + 表单中validbox的确认验证
        onSubmit: function(){
            $.messager.progress();
            var isValid = $(this).form('validate');
            if (!isValid) {
                $.messager.progress('close');
            }
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
        $('.fm table input[type=radio]').get(0).checked = true;
    });
});
// true 通过,学号可用 false则提示重复
function check_student_num(username){
    return false;
    // $.ajax({
    //     url: '',
    //     type: 'post',
    //     dataType: 'json',
    //     data: {username: usernameVal},
    //     success: function(result){
    //         if (result.success) {
    //             return true;
    //         }else{
    //             return false;
    //         }
    //     }
    // });
}