<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chome=1"/>
    	<meta name="robots" content="index,follow"/>
    	<meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    	<title>智慧公正平台</title>
    	<link rel="stylesheet" href="${basePath}/css/enter.css"/>
    	<script src="${basePath}/javascript/jquery-1.11.3.js"></script>
    	<script src="${basePath}/javascript/placeholder.js"></script>
    	<link href="${basePath}/images/title.ico" rel="shortcut icon"/>
	</head>
	<body>
		<div class="enter-con">
		<form action="${basePath}/manage/user/login.action" method="post" id="login" class="enter-table">
			<input type="hidden" name="meg" value="${meg}" id="meg"/>							
			用户名：
			<input type="text" name="loginname" id="loginname" onfocus="changeColor(this)" ><br/><br/>
			<span  class="enter-password">密&nbsp;&nbsp;&nbsp;码：</span>
			<input type="password" name="password" id="password" onfocus="changeColor(this)">	
			<div  class="enter-submit">
				<input type="button" class="submitButtonClass" onclick="tijiao();" value="登录">
			</div>
		</form>
    </div>
	</body>
	<script type="text/javascript">
	   <%-- 按回车提交表单 --%>
       document.onkeydown=function(event){
           if(event.keyCode==13){  
        	   tijiao();  
            }  
		}
		
		function tijiao(){
	        var name=document.getElementById("loginname").value;
	        var pwd=document.getElementById("password").value;
	        if(name==''){
	                alert("请输入用户名。");
	                return false;
	            }
	        if(pwd==''){
	            alert("请输入密码。");
	            return false;
	        }
	         $("#login").submit();
	      }
		
		function clearval(obj,tp){
			var oval=$(obj).val();
			if(tp=="name"){
				if(oval=="请输入用户名:"){
					$(obj).val("");
				}
				if(oval=="请输入用户密码:"){
					$(obj).val("");
				}
			}
		}
		$(function(){
			if(window.parent != window){
				window.parent.location.href="${basePath}/login.jsp";
			}
			var meg=$("#meg").val();
			if(meg=="2"){
				alert('用户名或密码错误！');
				$("#loginname").focus();
			}
			if(meg=="4"){
				alert('此账号没有权限！');
				$("#loginname").focus();
			}
			var meg=$("#meg").val("");
		})
		
		function changeColor(obj){
			$(obj).css("color","");
		}

	</script>
</html>