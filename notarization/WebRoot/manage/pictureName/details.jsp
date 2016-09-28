<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>查看</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">证件类型&gt;&gt;查看</p>
		</div>
  			<div class="look-table-container">
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						 <td><span class="name-title">证件名称：</span>${pictureName.pic_name}</td>
  					</tr>
  					<tr>
  						 <td><span class="name-title">是否上传照片：</span>
  						 	 <s:if test="pictureName.mast==0">否</s:if>
							 <s:else>是</s:else>
						 </td>
  					</tr>
  					<tr>
  						 <td><span class="name-title">证件说明：</span>${pictureName.remark}</td>
  					</tr>
  				</table>
  			</div>
  			<div style="height: 106px;width: 100%;">
		  		<div class="button-container">
				  	<button class="return" onclick="openlink('${basePath}/manage/pictureName/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
</html>