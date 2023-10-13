$(function(){
	$("#message").html("");
	$("#error").html("");
	var type = 1;	//定义一个类型，用于判断（员工登录或者客户登录）
	$("#radio-type-kehu").click(function(){ type = 1; });
	$("#radio-type-ygong").click(function(){ type = 0; });
	//登录的ajax
	$("#btn-login").click(function() {
		//每次登录，相当于清空cookies
		$.cookie("permissions",null)
		$.ajax({
			"url" : type == 1 ? "/customer/login" : "/employees/login", 
			"data" : $("#form-login").serialize(),
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					$.cookie("uid",json.data.uid,{ expire:1  });
					$.cookie("username",json.data.username,{ expire:1  });
					$.cookie("avatar",json.data.avatar,{ expire:7  });
					$.cookie("permissions",json.data.permissions);
					location.href="index.html"
				} else{
					alert(json.message);
					$("#message").html(json.message);
				}
			},
			"error" : function(){
				$("#error").html(json.message);
			}
		});
		
	});
})