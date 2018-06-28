<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function () {
    //alert(33);
    $('#sendCode').click(function () {
    	//alert(12);
    	var inMobile = $("#inMobile").val();
    	if(inMobile == "" || null == inMobile)
    	{
    		alert("手机号码不能为空！");
    		return;
    	}
        $.ajax({
            type:'get',
            url:"TelServlet",
          	data:{"opr":'code',"inMobile":inMobile},
          	dataType:"json",
            success: function (data) {
                if (data.code == 1)
                {
                	alert("验证码发送成功!");	
                }
                else if(data.code == 0)
                {
                	alert("验证码发送失败，稍后再试！");
                }
            },
            error: function () {
                alert("服务器连接异常");
            }
        })
    });
});
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>短信登录</title>
</head>
<body>
	<h1>短信登录</h1>
	<form action="TelServlet?opr=login" method = "post">
		请输入手机号：<input type = "text" name = "inMobile" id = "inMobile" value = "${mobile}" /><br/>
		请输入验证码：<input type = "text" name="inValidateCode" id="inValidateCode"/>
		<button id = "sendCode" onclick="return false;" >获取验证码</button><br/>
		<input type = "submit" value = "登录" />
	</form>
	
</body>
</html>