<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>用户列表</title>
</head>
  <body >
  	<div class="nav-header">
	    <p class="new-add">后台用户</p>
	</div>
    	<div class="searchsty">
    		<div style="float: left;">
		        <input type="button" class="zhenjian-add" onclick="openlink('${basePath}/manage/user/toInsert.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)"  value="增加"/>
		    </div>
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    	    	<span class="role-name">
	    		 	<input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="姓名"/>
	    		 	</span>
	    		 	<span class="role-state">
	                <input type="text" name="searchTearm2" value="${searchTearm2}" placeholder="登录名"/>
	                </span>
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/user/list.action','Form',1)" />
	          	</div>
    		</form>
    	</div>
    	<div class="zjtype">
        <table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
                <td style="width: 20%;">姓名</td>
    			<td style="width: 20%;">登录名</td>
    			<td style="width: 15%;">性别</td>
    			<td style="width: 15%;">手机号</td>
    			<td style="width: 30%;">操作</td>
            </tr>
            <s:iterator value="pageBean.dataList"  status="sta" id="obj">
            	<tr <s:if test="#sta.index%2 == 0">style="background-color: #FFFFFF;"</s:if> >
	                <td>
						${name }
					</td>
					<td>
						${loginname }
					</td>
					<td>
						<s:if test="sex==0">
							男
						</s:if>
						<s:else>
							女
						</s:else>
					</td>
					<td>
						${mobile_phone }
					</td>
	                <td style="width: 250px;">
	                	<a title="查看" onclick="openlink('${basePath}/manage/user/toDetails.action?user.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
	                	<a title="编辑" onclick="openlink('${basePath}/manage/user/toUpdate.action?user.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-edit">编辑</a>
	                	<a title="删除"  onclick="if(confirm('确认删除？'))openlink('${basePath}/manage/user/delete.action?user.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-del" >删除</a>
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
