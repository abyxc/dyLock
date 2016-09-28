<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>查看</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">后台用户&gt;&gt;查看</p>
		</div>
  			<div class="look-table-container">
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						 <td><span class="name-title">姓名：</span>${user.name}</td>
  						  <td><span class="name-title">性别：</span><s:if test="user.sex==0">男</s:if>
							<s:else>女</s:else></td>
  					</tr>
  					<tr>
  						 <td><span class="name-title">手机号码：</span>${user.mobile_phone}</td>
  						 <td><span class="name-title">角色：</span>${group.name}</td>
  					</tr>
  					<tr>
  						 <td><span class="name-title">登录名：</span>${user.loginname}</td>
  						 <td><span class="name-title">密码：</span>${user.password}</td>
  					</tr>
  					<tr>
  						 <td><span class="name-title">地址：</span>${user.address}</td>
  						  <td><span class="name-title">备注：</span>${user.remark}</td>
  					</tr>
  				</table>
  			</div>
  			<div style="height: 106px;width: 100%;">
		  		<div class="button-container">
				  	<button class="return" onclick="openlink('${basePath}/manage/user/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
</html>