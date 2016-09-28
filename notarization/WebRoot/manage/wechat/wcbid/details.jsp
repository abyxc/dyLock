<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>申办详情</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    
</head>
<body>
	<dl class="container">
	    <dd><span class="text-title">申请人：</span><span class="text-content">${objArr[0]}</span></dd>
	    <dd><span class="text-title">证件类型：</span>
	    	<span class="text-content">
	    		<s:if test="objArr[1] == 1">身份证</s:if>
	    		<s:if test="objArr[1] == 2">护照</s:if>
	    		<s:if test="objArr[1] == 3">台湾地区身份证</s:if>
	    		<s:if test="objArr[1] == 4">港澳通行证</s:if>
		</dd>
	    <dd><span class="text-title">证件号码：</span><span class="text-content">${objArr[2]}</span></dd>
	    <dd><span class="text-title">手机号：</span><span class="text-content">${objArr[3]}</span></dd>
	    <dd><span class="text-title">公证类型：</span><span class="text-content">${objArr[4]}</span></dd>
	    <dd><span class="text-title">领取方式：</span><span class="text-content">${objArr[5]}</span></dd>
	    <dd class="last-dd"><span class="text-title">公证书份数：</span><span class="text-content">${objArr[6]}份</span></dd>
	</dl>
	<s:iterator value="picList" status="sta">
		<div class="img-container">
		    <p class="img-name">${pic_name}</p>
		    <div class="img-content">
		    	<s:iterator value="uploadPictures" status="sta">
			        <div class="img"><img src="${picture_url}" alt=""/></div>
		    	</s:iterator>
		    </div>
		</div>
	</s:iterator>
</body>
</html>