<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
  <title>角色列表</title>
  <script type="text/javascript">
  
  function delrole(a,b,c){
      if(confirm('确认删除？')){
         openlink(a,b,c);
      } 
  }
 
  </script>
</head>
  <body >
   	<div class="nav-header">
	    <p class="new-add">角色列表</p>
	</div>
<%--    	<div class="td-caozuo">--%>
<%--    		<div class="caozuo"><a href="${basePath}/manage/group/toOper.action" class="add"><span>新增</span></a></div>--%>
<%--    		<form action="${basePath}/manage/role/list.action" method="post" id="Form">--%>
<%--	    	    <div class="search-container">--%>
<%--	    		 	<input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="名称"/>--%>
<%--	                <input type="submit" value="查询" class="button_Seacher_Class"/>--%>
<%--	          	</div>--%>
<%--    		</form>--%>
<%--    	</div>--%>
    	<div class="searchsty">
    		<div style="float: left;">
		        <input type="button" class="zhenjian-add" onclick="openlink('${basePath}/manage/group/toOper.action?searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','',0)"  value="增加"/>
		    </div>
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    	    	<span class="role-name">
	    		 	<input type="text" name="searchTearm1" value="${searchTearm1}" placeholder="角色名称"/>
	    		 	</span>
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/group/list.action','Form',1)" />
	          	</div>
    		</form>
    	</div>
    	<div class="zjtype">
        <table class="zjtype-table" cellspacing="0" cellpadding="0">
            <tr class="table-th">
                <td style="width: 50%;">角色名称</td>
				<td style="width: 50%;">操作</td>
            </tr>
            <s:iterator value="pageBean.dataList"  status="sta" id="obj">
            	<tr <s:if test="#sta.index%2 == 0">style="background-color: #FFFFFF;"</s:if> >
	                <td>
						${name}
					</td>
	                <td>
	                	<a title="分配权限" onclick="openlink('${basePath}/manage/group/toAllot.action?group.id=${id}&searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-work">分配权限</a>
	                	<a title="删除"  onclick="delrole('${basePath}/manage/group/delete.action?group.id=${id}&searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-del" >删除</a>
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
