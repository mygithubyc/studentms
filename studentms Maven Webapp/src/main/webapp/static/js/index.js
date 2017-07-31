/* 
* @Author: anchen
* @Date:   2017-07-24 15:33:05
* @Last Modified by:   anchen
* @Last Modified time: 2017-07-25 09:06:40
*/




$(document).ready(function(){
    var register_form = $('#register_form'),
        login_form = $('#login_form'),
        login_switcher = $('.container__switcher a:nth-child(1)'),
        register_switcher = $('.container__switcher a:nth-child(2)'),
        login_button = $('#login_button'),
        register_button = $('#register_button'),
        switcher_tip = $('.container__tip');

    //初始化
    register_form.hide();
    login_switcher.addClass('container__a_status_active');
    login_form.show();

    // 切换到注册
    login_switcher.click(function(event) {
        $('.container__validate>ol').text('');
        login_form.show();
        register_form.hide();
        login_switcher.addClass('container__a_status_active');
        register_switcher.removeClass('container__a_status_active name');
        switcher_tip.removeClass('container__tip_status_switched');
    });

    // 切换到登录
    register_switcher.click(function(event) {
        $('.container__validate>ol').text('');
        login_form.hide();
        register_form.show();
        register_switcher.addClass('container__a_status_active');
        login_switcher.removeClass('container__a_status_active name');
        switcher_tip.addClass('container__tip_status_switched');
    });
    // 登录
    login_button.click(function(event) {
        login_form.submit(function(event2) {
        	event2.preventDefault();
            var usernameVal = $(this).find('#username').val(),
                passwordVal = $(this).find('#password').val(),
                typeVal = $(this).find('[type=radio]:checked').val();

            
            var arr = [];
            // 确认填写
            if (usernameVal && passwordVal && typeVal) {
                // alert(login_form.serialize());
                $.ajax({
                    url: ctx+'/users/doLogin',
                    type: 'post',
                    dataType: 'json',
                    data: login_form.serialize(),
                    success: function (result) {
                    	
	                     if (result.success) {
	                    	 window.location.href = ctx+"/users/goMain";
	                     }else{
	                         arr.push(result.message);
	                         show_validate(arr);
	                     }
                    }
                });
                
                
                
            }else{
                
                if (!usernameVal) {arr.push('请输入用户名')}
                if (!passwordVal) {arr.push('请输入密码')}
                if (!typeVal) {arr.push('请选择老师或学生')}
                
                show_validate(arr);
            }
            
            

        });
    });
     // 注册
    register_button.click(function(event) {
    		event.preventDefault();
            var usernameVal = register_form.find('#username').val(),
                passwordVal = register_form.find('#password').val(),
                passwordAgainVal = register_form.find('#password_again').val();
              

            
            var arr = [];
            // 确认填写 
            if (usernameVal && passwordVal && passwordAgainVal  && CHECK) {                
                $.ajax({
                    url: ctx+'/users/doRegister',
                    type: 'post',
                    dataType: 'json',
                    data: register_form.serialize(),
                    success: function (result) {
                        // 返回结果类型为json "{\"success\":true}" success为bool值
                        // 失败则在message中写上错误信息
                         if (result.success) {
                        	 window.location.href = ctx+'/users/goMain';
                         }else{
                             arr.push(result.message);
                             show_validate(arr);
                         }
                    }
                });
                      
            }else{
                
                if (!usernameVal) {arr.push('请输入用户名')}
                if (!passwordVal || !passwordAgainVal) {arr.push('请输入密码或确认密码')}
                if (passwordVal !== passwordAgainVal) {arr.push('俩次密码不一致')}
                if(!CHECK){arr.push('还未通过检测账号是否可用')}   
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
	//check用于检查重复用   初始化为false  判断时修改  还解决在注册时跳过这个检查的bug
    var CHECK = false;
    register_form.find('#username').blur(function(event) {
        var username = $(this).val();
        // console.log(username === '');
		
        if (username !== '') {
        	console.log(username);
            $.ajax({
				// 返回结果类型为json "{\"success\":true}" success为bool值  否则"{\"success\":false}"
            	url: ctx+"/users/testUsername",
	            type: 'post',
	            dataType: 'json',
	            data:{username: username},
	            success: function(result){
	            	if(result.success){
	            		CHECK = false;
	            		show_validate(['用户名'+username+'已存在']);
	            	}else{
	            		CHECK = true;
	            		show_validate(['该用户名可用']);
	            	}
	            }
            });
        }
    });
    //测试用  自动切换到注册
//    register_switcher.click();
});