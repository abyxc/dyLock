<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>查看业务员</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">手机用户&gt;&gt;查看</p>
		</div>
  			<div class="look-table-container">
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						<td><span class="name-title">名称：</span>${appUser.name}</td>
  						<td><span class="name-title">手机号：</span>${appUser.mobile_phone}</td>
  					</tr>
  					<tr>
  						<td><span class="name-title">证件类型：</span><s:if test="appUser.idcard_type == 1">身份证</s:if>
  							<s:elseif test="appUser.idcard_type == 2">护照</s:elseif>
  							<s:elseif test="appUser.idcard_type == 3">台湾地区身份证</s:elseif>
  							<s:elseif test="appUser.idcard_type == 4">港澳通行证</s:elseif></td>
  						<td><span class="name-title">证件号码：</span>${appUser.idcard_number}</td>
  					</tr>
  					<tr>
  						<td><span class="name-title">登录名：</span>${appUser.loginname}</td>
  						<td><span class="name-title">微信号：</span><s:if test="appUser.openid!=null&&appUser.openid!=''">${appUser.openid}</s:if><s:else>未绑定</s:else></td>
  					</tr>
  				</table>
  			</div>
  			<div style="height: 106px;width: 100%;">
		  		<div class="button-container">
				  	<button class="return" onclick="openlink('${basePath}/manage/user/listSM.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
</html>