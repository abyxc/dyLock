<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body background="${basePath}/images/main/bg.png">
  <div style="background-color:#BCB0C5;">
    <div style="font-size: 23px;font-weight: bold;">
    	权限列表
    </div>
    <div>
    	<table width="100%">
    		<tr>
    			<td>
	    			<form action="${basePath}/manage/privilege/list.action" method="post">
			  			<s:textfield name="privilege.name" placeholder="名称"></s:textfield> 
			  			<input type="submit" value="查询"/>
			  		</form>
    			</td>
    			<td align="right">
					<table>
						<tr>
							<td>
								<s:a  href="toInsert.action">
									<div style="background-color:#185c90;color:white;float: left;width:50px;"
										 align="center">添加</div>
								</s:a>
							</td>
							<td>
						</tr>
					</table>
    			</td>
    		</tr>
    	</table>
    </div>
   </div>
    <div>
    	<table width="100%"  cellspacing="0" style="padding: 10 10 10 10;">
    		<tr bgcolor="#D1C8D9" height="30px;">
    			<td><input type="checkbox" onclick="checkAll(this,'privileges');"/></td>
    			<td><span style="font-size: 21px;font-weight: bold;">名称</span></td>
    			<td><span style="font-size: 21px;font-weight: bold;">地址</span></td>
    			<td><span style="font-size: 21px;font-weight: bold;">序号</span></td>
    			<td><span style="font-size: 21px;font-weight: bold;">标识</span></td>
    			<td><span style="font-size: 21px;font-weight: bold;">操作</span></td>
    		</tr>
    		<s:iterator value="privileges" status="sta">
    			<tr>
    				<td>
    				
    				</td>
    				<td>
	    					【一级】${name}
    				</td>
    				<td>
    					${url}
    				</td>
    				<td>
    					${order_num}
    				</td>
    				<td>
    					${code}
    				</td>
    				<td>
    					<a href="${basePath}/manage/privilege/toUpdate.action?privilege.id=${id}">编辑</a>
    					<a href="${basePath}/manage/privilege/delete.action?privilege.id=${id}">删除</a>
    					<a href="${basePath}/manage/privilege/toInsert.action?parent_id=${id}">添加下级</a>
    				</td>
    			</tr>
    			<s:iterator value="children" status="sta1">
	    			<tr>
	    				<td>
	    				
	    				</td>
	    				<td>
		    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【二级】${name}
	    				</td>
	    				<td>
	    					${url}
	    				</td>
	    				<td>
	    					${order_num}
	    				</td>
	    				<td>
	    					${code}
	    				</td>
	    				<td>
	    					<a href="${basePath}/manage/privilege/toUpdate.action?privilege.id=${id}">编辑</a>
	    					<a href="${basePath}/manage/privilege/delete.action?privilege.id=${id}">删除</a>
	    					<a href="${basePath}/manage/privilege/toInsert.action?parent_id=${id}">添加下级</a>
	    				</td>
	    			</tr>
			    		<s:iterator value="children" status="sta1">
			    			<tr>
			    				<td>
			    				
			    				</td>
			    				<td>
				    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    				【三级】${name}
			    				</td>
			    				<td>
			    					${url}
			    				</td>
			    				<td>
			    					${order_num}
			    				</td>
			    				<td>
			    					${code}
			    				</td>
			    				<td>
			    					<a href="${basePath}/manage/privilege/toUpdate.action?privilege.id=${id}">编辑</a>
			    					<a href="${basePath}/manage/privilege/delete.action?privilege.id=${id}">删除</a>
			    					<a href="${basePath}/manage/privilege/toInsert.action?parent_id=${id}">添加下级</a>
			    				</td>
			    			</tr>
		    			</s:iterator>
    			</s:iterator>
    		</s:iterator>
    	</table>
    </div>
  </body>
</html>
