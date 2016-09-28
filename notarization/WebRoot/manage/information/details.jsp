<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>详情</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">信息列表&gt;&gt;查看</p>
		</div>
  		<div class="look-table-container">
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr class="operTableTRClass">
  						<td><span class="name-title">手机号码：</span>${information.phone}</td>
  						<td><span class="name-title"><s:if test="user.name!=null">用户姓名：</s:if></span>${user.name}</td>
					</tr>  	
					<tr class="operTableTRClass">
						<td><span class="name-title">公证类型：</span>${type.gz_name}</td>
						<td><span class="name-title">发送状态：</span>
							<s:if test="information.state==0 && information.send_state==1">发送成功</s:if>
							<s:else>发送失败</s:else>
						</td>
					</tr>
					<tr class="operTableTRClass">
						<td><span class="name-title">发送时间：</span><s:date name="Information.send_time"/></td>
						<td><span class="name-title">短信类型：</span>
							<s:if test="information.type==0">注册短信</s:if>
							<s:elseif test="information.type==1">登录短信</s:elseif>
							<s:elseif test="information.type==2">国内业务申办成功</s:elseif>
							<s:elseif test="information.type==3">涉外业务申办成功</s:elseif>
							<s:elseif test="information.type==4">国内业务申办失败</s:elseif>
							<s:elseif test="information.type==5">涉外业务申办失败</s:elseif>
							<s:elseif test="information.type==6">拒绝申办</s:elseif>
							<s:elseif test="information.type==7">缴费成功提示</s:elseif>
							<s:else>其他</s:else>
						</td>
					</tr>
					<tr>
					<s:if test="information.send_state==-1 || information.send_state==-2 || information.send_state==-3 || information.send_state==-4 || information.send_state==-5 || information.send_state==-6 ">
						<td><span class="name-title">失败原因：</span>
							<s:if test="information.send_state==-1">状态返回失败</s:if>
							<s:if test="information.send_state==-2">状态返回过期</s:if>
							<s:if test="information.send_state==-3">号码错误</s:if>
							<s:if test="information.send_state==-4">敏感字符</s:if>
							<s:if test="information.send_state==-5">流量超出</s:if>
							<s:if test="information.send_state==-6">主叫号码或密码错误</s:if>
						</td>
					</s:if>
					</tr>
					<tr>
						<td colspan = '2'><span class="name-title">发送内容：</span>${information.content}</td>
					</tr> 		
	  			</table>
  		</div>
  			<div style="height: 106px;width: 100%;">
		  		<div class="button-container">
				  	<button class="return" onclick="openlink('${basePath}/manage/information/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
</html>