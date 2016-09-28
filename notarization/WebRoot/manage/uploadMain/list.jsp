<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head lang="en">
	<title>申办审核列表</title>
  </head>
  <body >
	<div class="nav-header">
	    <p class="new-add">审核列表</p>
	</div>
    	<div class="searchsty"> 
<%--    		<div style="float: left;">--%>
<%--		        <input type="button" class="zhenjian-add" onclick="openlink('${basePath}/manage/templet/toInsert.action','',0)"  value="增加"/>--%>
<%--		    </div> 											--%>
    		<form method="post" id="Form">
	    	    <div class="xinxi-type">
	    	    <span class="role-name">
	    		 	<input type="text" name="searchTearm1" id="meg"  value="${searchTearm1}" placeholder="记录编号" style="height: 26px;width: 200px;" />
	    		 </span>
	    	    <span class="role-name">
	    		 	<input type="text" name="searchTearm2" id="meg"  value="${searchTearm2}" placeholder="用户姓名" style="height: 26px;width: 200px;" />
	    		 </span>
	    		 <span class="role-state">
					<input type="text" name="searchTearm3" id="ids"  value="${searchTearm3}" placeholder="证件号码" style="height: 26px;width: 200px;"  />
				</span>
	                <input type="button" value="查询" class="zhenjian-add"  onclick="openlink('${basePath}/manage/uploadMain/list.action','Form',1)" style="margin-left: 0px;height:30px;line-height: 28px;"/>
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
					<td style="width: 12%;">
					    ${obj[0]}
					</td>
					<td style="width: 8%;">
					    ${obj[1]}
					</td>
					<td style="width: 10%;">
						<s:if test="#obj[2] == 1">身份证</s:if>
						<s:elseif test="#obj[2] == 2">护照</s:elseif>
						<s:elseif test="#obj[2] == 3">台湾地区身份证</s:elseif>
			    		<s:elseif test="#obj[2] == 4">港澳通行证</s:elseif>
					</td>
					<td style="width: 13%;">
						${obj[3]}
					</td>
					<td style="width: 13%;">
						${obj[4]}
					</td>
					<td id='${obj[6]}wj1' style="width: 8%;">
						<s:if test="#obj[5] == 0">拒绝审核</s:if>
						<s:elseif test="#obj[5] == 1">等待审核</s:elseif>
						<s:elseif test="#obj[5] == 2">审核成功</s:elseif>
			    		<s:elseif test="#obj[5] == 3">审核失败</s:elseif>
			    		<s:elseif test="#obj[5] == 4">已撤销</s:elseif>
			    		<s:elseif test="#obj[5] == 5">已缴费</s:elseif>
			    		<s:elseif test="#obj[5] == 6">已完结</s:elseif>
					</td>
					<td style="width: 10%;">
						${obj[8]}
					</td>
					<td id="${obj[6]}wj2" style="width: 30%;">
						<s:if test="#obj[5] == 5">
								<a id="${obj[6]}wj3" title="完结" style="cursor:pointer"  onclick="ends('${obj[6]}','')" class="role-del">完结</a>
						</s:if>
						<a title="查看"   onclick="openlink('${basePath}/manage/uploadMain/checks.action?uploadMain.id=${obj[6]}&action=1&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-look">查看</a>
						<s:if test="#obj[5] == 1">
							<a title="拒绝审核" id="${obj[6]}wj4" onclick="ends('${obj[6]}','0')" class="role-edit">拒绝</a>
							<a title="审核" id="${obj[6]}wj5" onclick="openlink('${basePath}/manage/uploadMain/checks.action?uploadMain.id=${obj[6]}&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',0)" class="role-edit">审批</a>
						</s:if>
						<a title="删除"  class="role-del"
							onclick="javascript:if(!confirm('确认删除？')){return false;} openlink('${basePath}/manage/uploadMain/delete.action?uploadMain.id=${obj[6]}&searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',1)">删除</a>
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
	  	function ends (id,meg) {
	  		if(!confirm('确认操作？')){
		  		return false;
		  	}
		  	$.post("${basePath}/manage/uploadMain/ends.action",{"ids":id,"meg":meg},function(data){
		  		if(data == '1'){
			  		alert("操作失败，请刷新后再试");
			  		return false;
				  	}
		  		if(data == '0'){
		  			alert("系统异常");
			  		return false;
				  	}
			  	if(Number((data.lastIndexOf('reject'))>0)){
				  		data = data.substring(0,data.lastIndexOf('reject'));
				  		alert("操作成功");
				  		$("#"+data+"wj1").html("拒绝审核");
				  		$("#"+data+"wj4").css('display','none');
				  		$("#"+data+"wj5").css('display','none');
<%--				  		openlink('${basePath}/manage/uploadMain/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',0);--%>
				  		return false;
				  	}else{
				  		alert("操作成功");
						$("#"+data+"wj1").html("已完结");
						$("#"+data+"wj3").css('display','none');
<%--						openlink('${basePath}/manage/uploadMain/list.action?searchTearm1=${searchTearm1}&searchTearm2=${searchTearm2}&searchTearm3=${searchTearm3}&pageBean.currentPage=${pageBean.currentPage}','',0);--%>
					  	}
		     	});
	  	 }
  </script>
</html>
