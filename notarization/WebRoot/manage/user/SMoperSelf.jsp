<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>手机用户</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">手机用户&gt;&gt;修改</p>
		</div>
  			<div class="look-table-container">
  			<form action="${basePath}/manage/user/updateSM.action" method="post" id="Form">
  				<s:hidden name="appUser.id"></s:hidden>
  				<s:hidden name="appUser.create_time"></s:hidden>
  				<s:hidden name="appUser.role_type"></s:hidden>
  				<s:hidden name="appUser.imei"></s:hidden>
  				<s:hidden name="appUser.remark"></s:hidden>
  				<s:hidden name="appUser.openid"></s:hidden>
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						<td><span class="name-title">名称：</span><s:textfield name="appUser.name" id="name" cssStyle="width: 200px;"></s:textfield></td>
  						<td><span class="name-title">手机号：</span><s:textfield name="appUser.mobile_phone" id="cellphone" cssStyle="width: 200px;"></s:textfield></td>
  					</tr>
  					<tr>
  						<td><span class="name-title">证件类型：</span><s:select name="appUser.idcard_type" id="idcard_type" value="appUser.idcard_type"  list="#{0:'请选择证件类型',1:'身份证',2:'护照',3:'台湾地区身份证',4:'港澳通行证'}" cssStyle="width: 200px;"/></td>
  						<td><span class="name-title">证件号码：</span><s:textfield name="appUser.idcard_number" id="idcard_number" cssStyle="width: 200px;"></s:textfield></td>
  					</tr>
  					<tr>
  						<td><span class="name-title">登录名：</span><s:textfield name="appUser.loginname" id="newLoginName" cssStyle="width: 200px;" onchange="ajaxLoginname(this.value,'%{appUser.loginname}')"></s:textfield></td>
  						<td><span class="name-title">微信号：</span><s:textfield name="appUser.openid" id="openid" cssStyle="width: 200px;"></s:textfield></td>
  					</tr>
  				</table>
  			</form>
  			</div>
  			<div class="button-container">
		  		<div class="btn-container" style="padding-top: 22px;">
				  	<button class="submit" onclick="tijiao()" id="btnOK">确定</button>
				  	<button type="button" class="return" onclick="openlink('${basePath}/manage/user/listSM.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
  	<script type="text/javascript">
  	var patrn=/\w{6,18}/;
  	var oldLoginName = $("#oldLoginName").val();
    	function ajaxLoginname(){
    		var newLoginName = $("#newLoginName").val();
    		if (!patrn.exec(newLoginName)){
				alert("登录名必须是：6-20个字母、数字、下划线");
				return false;
    		}
     		if(newLoginName == oldLoginName){
     			return false;
     		}
     		$.post("${basePath}/manage/user/ajaxLoginName.action",{"appUser.loginname":newLoginName},function(data){
         		
				if(data == 0){
					alert("用户名已被注册,请修改登陆名！");
					$("#newLoginName").val("");
				}
				if(data == -1){
					alert("系统异常");
				}
         	});
    	}
  		function tijiao(){
  	  		var name = $("#name").val();
  	  		var cellphone = $("#cellphone").val();
  	  		var post_id = $("#post_id").val();
  	  		var newLoginName = $("#newLoginName").val();
<%--  	  		var passwordOld = $("#passwordOld").val();--%>
	  	  	if(name == ""){
				alert("名称不能为空!");
				return false;
		  	  	}
	  	  if($("#idcard_type").val() == 0){
				alert("请选择证件类型!");
				return false;
		  	  	}
		  	if($("#idcard_number").val() == 0){
				alert("证件号码不能为空!");
				return false;
		  	  	}
<%--	  	  	if (!patrn.exec(passwordOld)){--%>
<%--			  	alert("密码必须是：6-20个字母、数字、下划线");--%>
<%--				return false;--%>
<%--	  	  	}--%>
	  	  	if (!patrn.exec(newLoginName)){
				alert("登录名必须是：6-20个字母、数字、下划线");
				return false;
			}
	  		if(!isPhone(cellphone)){
				alert("手机号不合乎规则");
				return false;
		  	}
			//有一个事件触发事件可以兼容ie
		  	if(confirm('确认提交？')){
			  	openlink('${basePath}/manage/user/updateSM.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','Form',1);
			  	setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1);
		  	}
  		}
  		function isPhone(s){//判断手机号
  			var re = /^1[3578]\d{9}$/;
  			return re.test(s)
  		} 
  	</script>
</html>