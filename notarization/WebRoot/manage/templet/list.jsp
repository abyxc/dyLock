<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
	<title>模板列表</title>
  </head>
  <body >
	<div class="nav-header">
	    <p class="new-add">模板列表</p>
	</div>
    	<div class="searchsty"> 
    		<div style="float: left;">
		        <input type="button" class="zhenjian-add" onclick="openlink('${basePath}/manage/templet/toInsert.action?templet.id=${obj[0]}&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)"  value="增加"/>
		    </div> 											
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    		 	<s:select list="typeList"  listValue="gz_name" listKey="id" headerValue="--请选择模板--" headerKey="" name="searchTearm1" id="type_id"  cssStyle="width: 200px;"/>
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/templet/list.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
	          	</div>
    		</form>
    	</div>
        <div class="zjtype">
    	<table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
    			<td>模板</td>
    			<td>类型</td>
    			<td>操作</td>
			</tr>
			<s:iterator value="pageBean.dataList" id="obj" status="sta">
				<tr <s:if test="#sta.odd == true">style="background-color: #FFFFFF;text-align: center;"</s:if> 
					<s:else>style="text-align: center;"</s:else>
				>
					<td style="width: 30%;">
						${obj[1]}
					</td>
					<td style="width: 30%;">
						${obj[2]}
					</td>
					<td style="width: 40%;">
						<a title="查看" onclick="openlink('${basePath}/manage/templet/toDetails.action?searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
						<s:if test="#obj[3] == 0"><a title="启用"  onclick="openlink('${basePath}/manage/templet/work.action?templet.id=${obj[0]}&templet.type_id=${obj[4]}&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',1)" class="role-work">启用</a></s:if>
						<s:elseif test="#obj[3] == 1">已启用</s:elseif>
						<a title="编辑"  onclick="openlink('${basePath}/manage/templet/toUpdate.action?templet.id=${obj[0]}&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-edit">编辑</a>
						<s:if test="#obj[3] == 0">
						<a title="删除"  class="role-del"  onclick="javascript:if(!confirm('确认删除？')){return false;} openlink('${basePath}/manage/templet/delete.action?templet.id=${obj[0]}&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&pageBean.currentPage=${pageBean.currentPage}','',1)">删除</a>
						</s:if>
						<s:else><a title="删除"  onclick="" class=""><img  src="${basePath}/images/main/delete.png">删除</a></s:else>
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
