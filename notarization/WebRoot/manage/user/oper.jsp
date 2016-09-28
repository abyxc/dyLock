<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>添加</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">后台用户&gt;&gt;新增</p>
		</div>
  			<div class="look-table-container">
  			<form action="${basePath}/manage/user/insert.action" method="post" id="Form">
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						<td><span class="name-title">姓名：</span><s:textfield name="user.name" id="name" cssStyle="width: 200px;height: 25px;"></s:textfield></td>
  						<td><span class="name-title">性别：</span><s:radio list="#{'0':'男','1':'女'}" name="user.sex" id="sex" value="0"></s:radio></td>
  					</tr>
  					<tr style="height: 40px;line-height: 40px;">
  						<td><span class="name-title">手机号码：</span><s:textfield name="user.mobile_phone" id="mobile_phone" cssStyle="width: 200px;"></s:textfield></td>
  						<td><span class="name-title">角色：</span><s:select id="role_id" list="groups" name="user.role_id" listKey="id" listValue="name" headerKey="" headerValue="--请选择--" cssStyle="width: 200px;height: 25px;"></s:select></td>
  					</tr>
  					<tr style="height: 40px;line-height: 40px;">
  						<td><span class="name-title">登录名：</span><s:textfield name="user.loginname" id="loginname" cssStyle="width: 200px;"  onchange="ajaxLoginname(this.value,'%{user.loginname}')"></s:textfield></td>
  						<td><span class="name-title">密码：</span><s:textfield name="user.password" id="password" cssStyle="width: 200px;"></s:textfield></td>
  					</tr>
  					<tr style="height: 40px;line-height: 40px;">
  						<td><span class="name-title">地址：</span><s:textfield name="user.address" id="address" cssStyle="width: 200px;"></s:textfield></td>
  						<td><span class="name-title">备注：</span><s:textfield name="user.remark" id="remark" cssStyle="width: 200px;"></s:textfield></td>
  					</tr>
  				</table>
  			</form>
  			</div>
  			<div class="button-container">
		  		<div class="btn-container" style="padding-top: 22px;">
				  	<button class="submit" onclick="tijiao()" id="btnOK">确定</button>
				  	<button type="button" class="return" onclick="openlink('${basePath}/manage/user/list.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
  	<script type="text/javascript">
    	function ajaxLoginname(loginname){
     		if(loginname.trim().length == 0){
         		return false;
     		}
     		$.post("${basePath}/manage/user/ajaxLoginName.action",{"appUser.loginname":loginname},function(data){
				if(data == 0){
					alert("用户名已被注册,请修改登陆名！");
					$("#loginname").val("");
				}
				if(data == -1){
					alert("系统异常");
					$("#loginname").val("");
				}
         	});
    	}
    </script>
    <script type="text/javascript">
  		function tijiao(){
  	  		var name = $("#name").val().trim();
  	  		var mobile_phone = $("#mobile_phone").val().trim();
  	  		var role_id = $("#role_id").val().trim();
  	  		var loginname = $("#loginname").val().trim();
  	  		var password = $("#password").val().trim();

  	  		$("#name").val(name);
  	  		$("#mobile_phone").val(mobile_phone);
  			$("#loginname").val(loginname);
  			$("#password").val(password);
  	  		
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
			if (!patrn.exec(loginname)){
				alert("账号必须是：6-20个字母、数字、下划线");
				return false;
			}
			if (!patrn.exec(password)){
				alert("密码必须是：6-20个字母、数字、下划线");
				return false;
			}
			//有一个事件触发事件可以兼容ie
		  	if(confirm('确认提交？')){
		  		setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1);
			  	openlink('${basePath}/manage/user/insert.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','Form',1);
		  	}
  		}
  	</script>
</html>