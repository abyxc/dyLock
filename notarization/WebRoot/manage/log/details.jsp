<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>查看日志</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">日志列表&gt;&gt;查看</p>
		</div>
  		<div class="look-table-container">
			<table cellpadding="0" cellspacing="0" class="look-table">
				<tr>
					<td><span class="name-title">用户：</span>${log.user_name}</td>
					<td><span class="name-title">用户IP：</span>${log.ip}</td>
				</tr>  	
				<tr>
					<td><span class="name-title">大类型：</span><s:if test="log.type==0">用户列表</s:if>
						<s:elseif test="log.type == 1">角色列表</s:elseif>
						<s:elseif test="log.type==2">权限列表</s:elseif>
						<s:elseif test="log.type==3">用户列表</s:elseif></td>
					<td><span class="name-title">小类型：</span><s:if test="log.type_tiny==1">添加</s:if>
						<s:elseif test="log.type_tiny==2">修改</s:elseif>
						<s:elseif test="log.type_tiny==3">删除</s:elseif>
						<s:elseif test="log.type_tiny==5">登录</s:elseif></td>
				</tr>  		
				<tr>
					<td><span class="name-title">创建时间：</span><s:date name="log.create_time"/></td>
				</tr>  
				<tr>
					<td colspan="2"><span class="name-title">备注：</span>${log.memo}</td>
				</tr>		
  			</table>
  		</div>
  			<div style="height: 106px;width: 100%;">
		  		<div class="button-container">
				  	<button class="return" onclick="openlink('${basePath}/manage/log/list.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
</html>