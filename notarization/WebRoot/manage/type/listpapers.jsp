<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>公证事项类型</title>
</head>
  <body >
	<div class="nav-header">
	    <p class="new-add">国内&gt;&gt;查看</p>
	</div>
	<div class="shenban-upload">
	    <p class="message-name">${meg}</p>
	    <div class="gongzheng-cailiao">
	        <ol>
				<s:iterator value="list"  status="sta" id="obj">  
				  <s:if test="#obj[0]!=null"><li>${obj[0]}</li></s:if>      
				  
				</s:iterator>
	        </ol>
	    </div>
	</div>   	
	<div class="bottom">
	        <input type="button" value="返回" class="back" onclick="openlink('${basePath}/manage/type/list.action',0,0)"/>
	</div>
  </body>
</html>
