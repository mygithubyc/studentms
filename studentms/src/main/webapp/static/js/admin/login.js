/* 
* @Author: anchen
* @Date:   2017-08-17 21:08:17
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 21:08:23
*/
$(function(){
    $('#login_form').form({
        url: ctx+'/admin/login',
        onSubmit: function(){
            // console.log($(this).form('validate'));
            return $(this).form('validate');
        },
        success: function(data){
        	var result = JSON.parse(data);
            if (result.success) {
            	location.href= ctx+"/admin/main";
            }else{
                $.messager.show({
                    title: '登录失败',
                    msg: result.msg
                });
            }
        }
        
    });
    $('#login_button').click(function(event) {
        $('#login_form').submit();
    });
});