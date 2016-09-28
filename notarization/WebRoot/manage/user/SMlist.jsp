<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>手机用户</title>
</head>
  <body >
  	<div class="nav-header">
	    <p class="new-add">手机用户</p>
	</div>
    	<div class="searchsty">
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    	    	<span class="role-name">
	    		 	<input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="姓名"/>
	    		 	</span>
	    		 	<span class="role-state">
	                <input type="text" name="searchTearm2" value="${searchTearm2}" placeholder="登录名"/>
	                </span>
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/user/listSM.action','Form',1)" />
	          	</div>
    		</form>
    	</div>
    	<div class="zjtype">
        <table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
                <td>姓名</td>
                <td style="width: 15%;">登录名</td>
                <td >手机号</td>
                <td style="width: 15%;">证件号</td>
                <td style="width: 15%;">证件类型</td>
                <td style="width: 25%;">操作</td>
            </tr>
            <s:iterator value="pageBean.dataList"  status="sta" id="obj">
            	<tr <s:if test="#sta.index%2 == 0">style="background-color: #FFFFFF;"</s:if> >
	                <td>${name}</td>
	                <td>${loginname}</td>
	                <td>${mobile_phone}</td>
	                <td>${idcard_number}</td>
	                <td>
	                    <s:if test="%{idcard_type==1}">身份证</s:if>
	                    <s:elseif test="%{idcard_type==2}">护照</s:elseif>
	                    <s:elseif test="%{idcard_type==3}">台湾地区身份证</s:elseif>
	                    <s:elseif test="%{idcard_type==4}">港澳通行证</s:elseif>
	                </td>
	                <td>
	                	<a title="查看" onclick="openlink('${basePath}/manage/user/toDetailsSM.action?appUser.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
	                	<a title="编辑" onclick="openlink('${basePath}/manage/user/toUpdateSM.action?appUser.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-edit">编辑</a>
	                	<a title="删除"  onclick="if(confirm('确认删除？'))openlink('${basePath}/manage/user/deleteSM.action?appUser.id=${id}&searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-del" >删除</a>
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
