/* 
 * @Author: anchen
 * @Date:   2017-07-24 15:33:05
 * @Last Modified by:   anchen
 * @Last Modified time: 2017-08-20 11:35:30
 */

$(document).ready(function() {
	var login_form = $('#login_form'), login_button = $('#login_button');

	// 登录
	/*$("#login_button").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		var userType = $('input:radio[name="type"]:checked').val();

		alert(userType);
		if (username.trim() == "" || password.trim() == "") {
			alert("用户名和密码不能为空");
			return;
		} else {
			alert("ok" + ctx + "/users/login");
			$.ajax({
				type : "post",
				url : ctx + "/users/login",
				dataType : "json",
				async : false, // 改为不同步
				data : {
					"username" : username,
					"password" : password,
					"userType" : userType
				},
				success : function(data) {
					var jsonOb = eval("(" + data + ")");
					alert(jsonOb.success);
					 if(jsonOj.success=="success")
						 window.location.href = ctx+"/users/goMain";
					 else
						 alert("密码或者用户名错误");
				},
				error : function() {
					alert("系统繁忙");
				}
			});
		}
	});*/

	// show_validate(['错','再错']);
	// 显示验证信息
	function show_validate(arr) {
		$('.container__validate>ol').text('');
		for (var i = 0; i < arr.length; i++) {
			$('.container__validate>ol').append('<li>' + arr[i] + '</li>')
		}
	}

});