$(function(){
	var oldPassword = "";
	var newPassword = "";
	var confirmPassword = "";
	$("input[name='oldPassword']").keyup(function(){
		oldPassword = $("input[name='oldPassword']").val();
		if( /^[a-zA-Z0-9]{6,16}$/.test(oldPassword) ){
			$("#oldPassword").html("密码输入正确").css("color","green");
		}else{
			$("#oldPassword").html("原密码包含大小写字母，数字，并且在6到16位之间").css("color","red");
		}
	});
	$("input[name='newPassword']").keyup(function(){
		newPassword = $("input[name='newPassword']").val();
		if( /^[a-zA-Z0-9]{6,16}$/.test(newPassword) ){
			$("#newPassword").html("密码输入正确").css("color","green");
		}else{
			$("#newPassword").html("新密码包含大小写字母，数字，并且在6到16位之间").css("color","red");
		}
	})
	$("input[name='confirmPassword']").keyup(function(){
		confirmPassword = $("input[name='confirmPassword']").val();
		if( confirmPassword != newPassword ){
			$("#confirmPassword").html("确认密码与新密码不一致").css("color","red");
		}else{
			$("#confirmPassword").html("密码输入正确").css("color","green");
		}
	})
})

