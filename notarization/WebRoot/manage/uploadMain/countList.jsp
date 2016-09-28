<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>申办列表</title>
	
  </head>
  <body >
  	<div class="nav-header">
	    <p class="new-add">统计列表</p>
	</div>
   	  <div class="searchsty"> 
		<form id="Form" method="post">
			<div class="xinxi-type">
				<label>事项类型：</label>
				<s:select name="searchTearm1" id="searchTearm1" value="searchTearm1"  list="#{'涉外（含港澳台）':'涉外（含港澳台）','国内':'国内'}" />
				<label>办理状态：</label>
				<s:select name="searchTearm2" id="searchTearm2" value="searchTearm2" list="#{6:'已完结',4:'已撤销',3:'审核失败'}" />
				<label>订单日期：</label>
				<input type="text" name="searchTearm3" value="${searchTearm3}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
				<label>至</label>
				<input type="text"name="searchTearm4"  value="${searchTearm4}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				<input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/uploadMain/countList.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
			</div>
		</form>
		</div>
	<div class="zjtype">
	<table class="zjtype-table" cellspacing="0" cellpadding="0">
			<tr class="table-th">
				<td style="width: 33%;">事项类型</td>
	   			<td style="width: 33%;">办理状态</td>
	   			<td style="width: 34%;">统计数量</td>
			</tr>
			<tr>
				<td>${searchTearm1}</td>
				<td>
					<s:if test="%{searchTearm2==3}">审批失败</s:if>
					<s:elseif test="%{searchTearm2==4}">已撤销</s:elseif>
					<s:elseif test="%{searchTearm2==6}">已完结</s:elseif>
				</td>
				<td>${searchTearm5}</td>
			</tr>
	</table>
</div>
  </body>
</html>
