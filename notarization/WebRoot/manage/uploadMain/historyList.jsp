<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
	<title>模板列表</title>
  </head>
  <body >
	<div class="nav-header">
	    <p class="new-add">申办记录</p>
	</div>
    	<div class="searchsty"> 
<%--    		<div style="float: left;">--%>
<%--		        <input type="button" class="zhenjian-add" onclick="openlink('${basePath}/manage/templet/toInsert.action','',0)"  value="增加"/>--%>
<%--		    </div> 											--%>
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    	    	<input type="text" name="searchTearm1"  value="${searchTearm1}" placeholder="记录编号" style="height: 26px;width: 200px;" />
	    		 	<input type="text" name="searchTearm2" id="meg"  value="${searchTearm2}" placeholder="用户姓名" style="height: 26px;width: 200px;" />
					<input type="text" name="searchTearm3" id="ids"  value="${searchTearm3}" placeholder="证件号码" style="height: 26px;width: 200px;"  />
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/uploadMain/list.action?status=2','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
	          	</div>
    		</form>
    	</div>
        <div class="zjtype">
    	<table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
           		<td>记录编号</td>
    			<td>用户姓名</td>
	   			<td>证件类型</td>
	   			<td>证件号码</td>
	   			<td>申办事项</td>
	   			<td>状态</td>
	   			<td>领取方式</td>
	   			<td>操作栏</td>
			</tr>
			<s:iterator value="pageBean.dataList" id="obj" status="sta">
				<tr <s:if test="#sta.odd == true">style="background-color: #FFFFFF;text-align: center;"</s:if> 
					<s:else>style="text-align: center;"</s:else>
				>
					<td style="width: 15%;">
					    ${obj[0]}
					</td>
					<td style="width: 10%;">
					    ${obj[1]}
					</td>
					<td style="width: 15%;">
						<s:if test="#obj[2] == 1">身份证</s:if>
						<s:elseif test="#obj[2] == 2">护照</s:elseif>
						<s:elseif test="#obj[2] == 3">台湾地区身份证</s:elseif>
			    		<s:elseif test="#obj[2] == 4">港澳通行证</s:elseif>
					</td>
					<td style="width: 20%;">
						${obj[3]}
					</td>
					<td style="width: 20%;">
						${obj[4]}
					</td>
					<td id='${obj[6]}wj1' style="width: 10%;">
						<s:if test="#obj[5] == 4">已撤销</s:if>
			    		<s:elseif test="#obj[5] == 6">已完结</s:elseif>
			    		<s:elseif test="#obj[5] == 0">已拒绝</s:elseif>
			    		<s:else>数据异常</s:else>
					</td>
					<td style="width: 10%;">
						${obj[8]}
					</td>
					<td style="width: 15%;">
						<a title="查看"   onclick="openlink('${basePath}/manage/uploadMain/checks.action?uploadMain.id=${obj[6]}&action=2&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
					</td>
				</tr>
			</s:iterator>
	</table>
	<div class="panelBar" style="background-color: #FFFFFF;width: 100%;height: 25px;">
			<div id="page" style="float: right;background-color: #FFFFFF;">${page}</div>
		</div>
</div>  	
  </body>
    <script type="text/javascript">
  </script>
</html>
