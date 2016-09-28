<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
	<title>证件类型列表</title>
  </head>
  <body >
  	<div class="nav-header">
	    <p class="new-add">证件类型列表</p>
	</div>
    <div class="searchsty"> 
    	<div style="float: left;">
		    <input type="button" class="zhenjian-add" onclick="openlink('${basePath}/manage/pictureName/toInsert.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)"  value="增加"/>
		</div>
    	<form method="post" id="Form">
		    <div class="xinxi-type">
	           <input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="证件名称"/>
	           <input type="button" value="查询" class="zhenjian-add" onclick="openlink('${basePath}/manage/pictureName/list.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
	   		 </div>
	    </form>
	</div>
    <div class="role-table">
    	<table cellspacing="0" cellpadding="0">
            <tr class="table-th">
            	<td>证件名称</td>
            	<td>证件说明</td>
    			<td>操作</td>
			</tr>
			<s:iterator value="pageBean.dataList" id="obj"  status="sta">
				<tr <s:if test="#sta.odd == true">style="background-color: #FFFFFF;text-align: center;"</s:if> 
					<s:else>style="text-align: center;"</s:else>
				>
					<td style="width: 35%;">
						${pic_name}
					</td>
					<td style="width: 35%;" title="${remark}">
						<s:if test="remark.length() > 20">
							<s:property value="remark.substring(0,20)"/>...
						</s:if>
						<s:else>
							${remark}
						</s:else>
					</td>
					<td style="width: 30%;">
						<a title="查看" onclick="openlink('${basePath}/manage/pictureName/toDetails.action?pictureName.id=${id}&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
						<a title="编辑" onclick="openlink('${basePath}/manage/pictureName/toUpdate.action?pictureName.id=${id}&searchTearm1=${searchTearm1 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-edit">编辑</a>
						<a title="删除"  onclick="if(confirm('确认删除？'))openlink('${basePath}/manage/pictureName/delete.action?pictureName.id=${id}&searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-del">删除</a>
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
