<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
    	 <title>个人中心</title>
  	</head>
  	<body>
  	<div class="personal-container">
    <form  method="post" id="Form">
    <input type="hidden" id="id" name="user.id" value="${user.id }">
  	<input type="hidden" id="role_type" name="user.role_type" value="${user.role_type }">
  	<input type="hidden" id="role_id" name="user.role_id" value="${user.role_id}">
	<input type="hidden" id="create_time" name="user.create_time" value="${user.create_time }">
	<input type="hidden" id="password" name="user.password" value="${user.password}">
    <table class="personal-table" cellpadding="0" cellspacing="0">
        <tr>
            <td><span class="personal-item">姓名：</span><s:textfield name="user.name" id="name" cssStyle="color:black"></s:textfield></td>
            <td><span class="personal-item">性别：</span>
                <div class="sex-choose">
                    <input type="radio"  id="man" name="user.sex" value="1" <s:if test="user.sex==1">checked</s:if>/>
                    <label for="man">男</label>
                    <input type="radio"  id="woman" name="user.sex" value="0" <s:if test="user.sex==0">checked</s:if>/>
                    <label for="woman">女</label>
                </div>
            </td>
        </tr>
        <tr>
        
            <td><span class="personal-item">登录名：</span><input type="text" name="user.loginname" id="loginname" value="${user.loginname}" readonly="true"/><span class="tip">不可修改</span></td>
             <td><span class="personal-item">角色：</span><input type="text" value="${group.name}" readonly="true"/><span class="tip">不可修改</span></td>
        </tr>
        <tr>
        	<td><span class="personal-item">手机号码：</span><s:textfield name="user.mobile_phone" id="mobile_phone" cssStyle="color:black"></s:textfield></td>
            <td><span class="personal-item">原密码：</span><input type="password" value="${user.password}" readonly="true"/></td>
           
        </tr>
        <tr>
        	<td><span class="personal-item">新密码：</span><input type="password" name="password1" id="password1" style="color:black" /></td>
        	<td><span class="personal-item">确认密码：</span><input type="password" name="password2" id="password2" style="color:black" onchange="javascript:if(jQuery.trim($('#password1').val()).length<1){alert('请填写密码！');$('#password2').val('')}"/></td>
        </tr>
        <tr>
            <td><span class="personal-item">地址：</span><s:textfield name="user.address" id="address" cssClass="remark" cssStyle="color:black"></s:textfield></td>
            <td><span class="personal-item">备注：</span><s:textfield name="user.remark" id="remark" cssClass="remark" cssStyle="color:black"/></td>
        </tr>
    </table>
    	<div class="per-sub-container">
            <input type="button" value="确定" onclick="tijiao()" id="btnOK"/>
        </div>
    </form>
</div>
</body>
 <script type="text/javascript">
  		function tijiao(){
  			var name = jQuery.trim($("#name").val());
  	  		var mobile_phone = jQuery.trim($("#mobile_phone").val());
  	  		var role_id = jQuery.trim($("#role_id").val());
  	  		var loginname = jQuery.trim($("#loginname").val());
  	  		var password1 = jQuery.trim($("#password1").val());
  	  		var password2 = jQuery.trim($("#password2").val());
  	  		if(name.length == 0){
  	  	  		alert("请填写姓名！");
  	  	  		return false;
  	  		}
  	  		if(mobile_phone.length == 0){
	  	  		alert("请填写手机号码！");
	  	  		return false;
	  		}
	  		
  	  		var mobile_phone = $("#mobile_phone").val();
  			var reg = /^0?1[3|4|5|8][0-9]\d{8}$/; 
  			 if (reg.test(mobile_phone)) { 
  			 }else{ 
  			      alert("请填写正确的号码格式!"); 
  			      return false;
  			 };
  			 
		  	if(role_id.length == 0){
		  	  	alert("请选择角色！");
		  	  	return false;
		  	}

			//验证登录名和密码格式
			var patrn=/\w{6,18}/; 
			if(password1.length>0){ 
				if (!patrn.exec(password1)){
					alert("密码必须是：6-20个字母、数字、下划线");
					$("#password1").val("");
					$("#password2").val("");
					return false;
				}
				if(password1!=password2){
					alert("两次密码输入不一致,请重新输入");
					$("#password1").val("");
					$("#password2").val("");
					return false;
				}
			}
			//有一个事件触发事件可以兼容ie
		  	if(confirm('确认提交？')){
				setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1);
				if(password1.length>0){  
		  			$("#password").val(password1);
				}
			  	openlink('${basePath}/manage/user/updateUC.action','Form',1);
			};
  		}
  	</script>
  	<script type="text/javascript">
  		$(document).ready(function(){ 
  	  			if(${meg}==1){
  	  	  			alert("修改失败");
  	  			}
  	  			if(${meg}==0){
  	  				window.location.href='${basePath}/exit.jsp';
  	  				alert("修改成功,请重新登陆");
	  			}
  	  		}
  		)
  	</script>
</html>
