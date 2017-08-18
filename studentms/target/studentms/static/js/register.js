$(document).ready(function() {
	// 获取用户名，密码
	// 对数据进行校验
	$("#submit").click(function() {
		$("#register").validate({
			rules : {
				username : {
					required : true,
					maxlength : 16
				},
				password : {
					required : true,
					maxlength : 32
				},
				confrimPassword : {
					required : true,
					maxlength : 32,
					equalTo : "#password"
				},
				messages : {
					username : {
						required : "账号必填",
						maxlength : $.validator.format("账号长度不能超过{0}")
					},
					password : {
						required : "密码必填",
						maxlength : $.validator.format("密码长度不能超过{0}")
					},
					confrimPassword : {
						required : "密码必填",
						maxlength : $.validator.format("密码长度不能超过{0}"),
						equalTo : "两次密码不一致"
					}
				}
			},
			submitHandler:function(form){
				$(form).$.fn.ajaxSubmit();		//对所提交的数据采用ajax提胶
			}
		});
	});
});