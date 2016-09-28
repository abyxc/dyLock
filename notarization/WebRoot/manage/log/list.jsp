<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
	<title>日志</title>
  </head>
  <body >
  	<div class="nav-header">
	    <p class="new-add">日志清单</p>
	</div>
<%--	<div style="line-height: 50px;"> --%>
<%--   		<form method="post" id="Form">--%>
<%--    	    <div class="xinxi-type">--%>
<%--    		 	<s:select list="typeList"  listValue="gz_name" listKey="id" headerValue="--请选择--" headerKey="" name="searchTearm1" id="type_id"  cssStyle="width: 200px;"/>--%>
<%--                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/templet/list.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>--%>
<%--          	</div>--%>
<%--   		</form>--%>
<%--   	</div>--%>
  		<div class="searchsty"> 
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    		 <span class="role-state">
	                <input type="text" name="searchTearm2" value="${searchTearm2}" placeholder="用户"/>
	             </span>
	             <span class="role-name">
	    		 	<input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="用户IP"/>
	    		 </span>
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/log/list.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
	          	</div>
    		</form>
    	</div>
    	<div class="zjtype">
        <table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
    			<td style="width: 20%;">用户</td>
				<td style="width: 20%;">创建日期</td>
				<td style="width: 30%;">操作内容</td>
    			<td style="width: 15%;">用户IP</td>
    			<td style="width: 15%;">操作</td>
			</tr>
			<s:iterator value="pageBean.dataList"  status="sta">
				<tr <s:if test="#sta.odd == true">style="background-color: #FFFFFF;text-align: center;"</s:if> 
					<s:else>style="text-align: center;"</s:else>
				>
					<td>
						${user_name}
					</td>
					<td>
						<s:date name="create_time"/>
					</td>
					<td title="${memo}">
						<s:if test="memo.length() > 19">
							<s:property value="memo.substring(0,15)"/>...
							</s:if>
							<s:else>
								${memo }
							</s:else>
						
					</td>
					<td>
						${ip}
					</td>
					<td>
						<a title="查看" onclick="openlink('${basePath}/manage/log/toDetails.action?log.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
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
