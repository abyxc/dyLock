<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<style type="text/css">
	</style>
	<script type="text/javascript" src="${basePath}/js/jquery_1.js"></script>
  </head>
  <body >
<%--		表单提交			--%>
	<div class="nav-header">
	    <p class="new-add">角色列表&gt;&gt;分配权限</p>
	</div>
	<div class="look-table-container">
		<form method="post" id="Form">
		<s:hidden name="group.id"></s:hidden> 
		<!--显示数据列表--> 
		<table cellpadding="0" cellspacing="0" class="look-table">

			
			<s:iterator value="privileges">			
			<tr>
				<!-- 显示权限树 -->
				<td id="tree">
				<!-- 显示树状结构内容 -->	
				    <div>		
						<input  id="cbox${id}" onclick="changeb('${id}')" type="checkbox" name="privilegeIds" value="${id}"  <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
						<label for="cb_${id}"><span class="folder" style="font-weight: bolder;">${name}</span></label>
						<ul id="ul_${id}" onclick="changebox('ul_${id}')" style="list-style: none;line-height: 30px">
							<s:iterator value="children">
								<li style="margin-left: 18px;float: left;">
									<input type="checkbox" name="privilegeIds" value="${id}" id="cb_${id}" <s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
									<label for="cb_${id}"><span class="folder" >${name}</span></label>
								</li>		
							</s:iterator>
						</ul>
					</div>	
				</td>									
			</tr>			
			</s:iterator>
			<tr>
				<td>
					<input type="checkbox" id="cbSelectAll" onClick="$('[name=privilegeIds]').attr('checked', this.checked)"/>
					<label for="cbSelectAll" style="color: #27408B;font-family: 微软雅黑;">全选</label>
				</td>
			</tr>			
		</table>					
		</form>
	</div>
	<div class="button-container">
  		<div class="btn-container" style="padding-top: 0px;">
		  	<button class="submit" onclick="tijiao()" id="btnOK">确定</button>
		  	<button type="button" class="return" onclick="openlink('${basePath}/manage/group/list.action?searchTearm1=${searchTearm1 }','',0)">返回</button>
		</div>
	</div>
  </body>
<script type="text/javascript">
	function tijiao(){
		openlink('${basePath}/manage/group/allot.action?searchTearm1=${searchTearm1}&pageBean.currentPage=${pageBean.currentPage}','Form',1);
	}
	function changeb(id){
		// 指定事件处理函数			
 		    $("#ul_"+id+" input[type='checkbox']").each(function(){		          
 		          var box=document.getElementById("cbox"+id);
			      this.checked=box.checked;      
			 });			

	}; 
	
	function changebox(id){
	        var n=0;
			$("#"+id+" input[type='checkbox']").each(function(){
			   if(this.checked==true){
			      n=1; 
			   }
			});	
			if(n==0){
	           $("#"+id).parents("div").children("input").attr("checked", false);
	        }else{
	           $("#"+id).parents("div").children("input").attr("checked", true);
	        }
	
	}
	
</script>
</html>