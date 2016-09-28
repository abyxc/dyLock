<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
	<title>信息列表</title>
  </head>
  <body >
  	<div class="nav-header">
	    <p class="new-add">信息列表</p>
	</div>
    <div class="searchsty"> 
    	<form method="post" id="Form">
		    <div class="xinxi-type">
	           <s:select list="list"  listValue="gz_name" listKey="id" headerValue="--请选择公证类型--" headerKey="" name="searchTearm1" id="type_id"  cssStyle="width: 200px;"/>
	           <input type="button" value="查询" class="zhenjian-add" onclick="openlink('${basePath}/manage/information/list.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
	   		 </div>
	    </form>
	</div>
    <div class="role-table">
    	<table cellspacing="0" cellpadding="0">
            <tr class="table-th">
            	<td style="width: 15%;">手机号码</td>
    			<td style="width: 15%;">发送时间</td>
				<td style="width: 20%;">短信类型</td>
				<td style="width: 20%;">公证类型</td>
				<td style="width: 15%;">状态</td>
    			<td style="width: 15%;">操作</td>
			</tr>
			<s:iterator value="pageBean.dataList" id="obj"  status="sta">
				<tr <s:if test="#sta.odd == true">style="background-color: #FFFFFF;text-align: center;"</s:if> 
					<s:else>style="text-align: center;"</s:else>
				>
					<td>
						${obj[0]}
					</td>
					<td>
						<s:date name="#obj[1]" />
					</td>
					<td>
						<s:if test="#obj[2]==0">注册短信</s:if>
						<s:elseif test="#obj[2]==1">登录短信</s:elseif>
						<s:elseif test="#obj[2]==2">国内业务申办成功</s:elseif>
						<s:elseif test="#obj[2]==3">涉外业务申办成功</s:elseif>
						<s:elseif test="#obj[2]==4">国内业务申办失败</s:elseif>
						<s:elseif test="#obj[2]==5">涉外业务申办失败</s:elseif>
						<s:elseif test="#obj[2]==6">拒绝申办</s:elseif>
						<s:elseif test="#obj[2]==7">缴费成功提示</s:elseif>
						<s:else>其他</s:else>
					</td>
					<td>
						${obj[3]}
					</td>
					<td>
						<s:if test="#obj[4]==0 && #obj[6]==1">发送成功</s:if>
						<s:else>发送失败</s:else>
					</td>
					<td>
						<a title="查看" onclick="openlink('${basePath}/manage/information/toDetails.action?information.id=${obj[5] }&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
<%--						<s:if test="#obj[4]==0"><a title="发送"  onclick="openlink('${basePath}/manage/information/send.action?information.id=${obj[0] }','',0)" class="role-look">重新发送</a></s:if>--%>
<%--						<s:else><span title="发送" class="role-look">已重新发送</span></s:else>--%>
						<a title="删除"  onclick="if(confirm('确认删除？'))openlink('${basePath}/manage/information/delete.action?information.id=${obj[5] }&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-del">删除</a>
					</td>
				</tr>
			</s:iterator>
	</table>
	<div class="panelBar" style="background-color: #FFFFFF;width: 100%;height: 25px;">
			<div id="page" style="float: right;background-color: #FFFFFF;">${page}</div>
		</div>
</div>  	
  </body>
</html>
