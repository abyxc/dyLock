<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>添加</title>
  	</head>
  	<body>
  			<div class="nav-header">
			    <p class="new-add">角色列表&gt;&gt;增加</p>
			</div>
  			<div class="look-table-container">
  			<form action="${basePath}/manage/group/${action}.action" method="post" id="Form">
  				<s:hidden name="group.id"></s:hidden>
				
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						<td><span class="name-title">名称：</span><s:textfield name="group.name" id="name" cssStyle="width: 200px;height: 25px;"></s:textfield></td>
  						<td><span class="name-title">备注：</span><s:textarea name="group.remark" cssStyle="height:50px;width: 300px;"></s:textarea></td>
  					</tr>
<%--  					<div style="width: 100%;height: 50px;"></div>--%>
<%--  					<div style="width: 100%;height: 50px;text-align: center;line-height: 50px;font-size: 14px;">--%>
<%--  						名称：<s:textfield name="group.name" id="name" cssStyle="width: 200px;height: 25px;"></s:textfield>--%>
<%--  					</div>--%>
<%--  					<div style="width: 100%;height: 50px;text-align: center;line-height: 50px;font-size: 14px;">--%>
<%--  						备注：<s:textarea name="group.remark" cssStyle="height:50px;width: 200px;"></s:textarea>--%>
<%--  					</div>--%>
  				</table>
  			</form>
<%--  				<div style="height: 106px;width: 100%;background-color: #FFFFFF;">--%>
<%--		  			<div class="btn-container" style="padding-top: 22px;">--%>
<%--					  	<a onclick="tijiao()" id="btnOK">确定</a>--%>
<%--					  	<a onclick="history.go(-1)">返回</a>--%>
<%--					</div>--%>
<%--  				</div>--%>
  			</div>
  			<div class="button-container">
		  		<div class="btn-container" style="padding-top: 22px;">
				  	<button class="submit" onclick="tijiao()" id="btnOK">确定</button>
				  	<button type="button" class="return" onclick="openlink('${basePath}/manage/group/list.action?searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
    <script type="text/javascript">
  		function tijiao(){
  	  		var name = $("#name").val().trim();
  	  		if(name.length == 0){
  	  	  		alert("请填写用户名！");
  	  	  		return false;
  	  		}
			//有一个事件触发事件可以兼容ie
		  	setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1); 
		  	openlink('${basePath}/manage/group/${action}.action?searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','Form',1);
  		}
  	</script>
</html>