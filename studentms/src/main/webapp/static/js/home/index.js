/* 
* @Author: anchen
* @Date:   2017-07-24 15:33:05
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-20 11:35:30
*/

$(document).ready(function(){
    var 
        login_form = $('#login_form'),
        login_button = $('#login_button');

    //初始化
    login_form.show();   
    // 登录
    login_button.click(function(event) {        
        event.preventDefault();
        var usernameVal = login_form.find('#username').val(),
            passwordVal = login_form.find('#password').val(),
            typeVal = login_form.find('[type=radio]:checked').val();
        console.log(typeVal);
        var arr = [];
        // 确认填写
        // 使用showvalidate(arr.push(msg))
        // 或者 $.messager.show()  提示密码错误等
        if (usernameVal && passwordVal && typeVal){
            
            
            
        }else{
            
            if (!usernameVal) {arr.push('请输入用户名')}
            if (!passwordVal) {arr.push('请输入密码')}
            if (!typeVal) {arr.push('请选择老师或学生')}
               
            show_validate(arr);
        }
 });
     

    // show_validate(['错','再错']);
    // 显示验证信息
    function show_validate(arr){
        $('.container__validate>ol').text('');
        for(var i = 0; i < arr.length ;i ++){
            $('.container__validate>ol').append('<li>'+arr[i]+'</li>')
        }
    }
    
    
});