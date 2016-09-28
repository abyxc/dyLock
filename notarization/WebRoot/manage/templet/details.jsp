<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>查看模板</title>
  	</head>
  	<body>
  			<div class="nav-header">
			    <p class="new-add">模板列表&gt;&gt;查看</p>
			</div>
			<div class="look-table-container">
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						 <td><span class="name-title">名称：</span>${templet.name}</td>
  						 <td><span class="name-title">类型：</span>${type.gz_name}</td>
					</tr> 
					<tr>
						<td colspan='2'><span class="name-title">内容：</span>${templet.content}</td>
					</tr> 	
	  			</table>
  		</div>
	  		<div style="height: 106px;width: 100%;">
		  		<div class="button-container">
				  	<button class="return" onclick="openlink('${basePath}/manage/templet/list.action?searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
</html>