<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
     <head>
     	<title>添加</title>
  	</head>
  	<body>
  		<div class="nav-header">
		    <p class="new-add">证件类型列表&gt;&gt;新增</p>
		</div>
  			<div class="look-table-container">
  			<form action="${basePath}/manage/pictureName/update.action" method="post" id="Form">
  				<s:hidden name="pictureName.id"></s:hidden>
  				<s:hidden name="pictureName.create_time"></s:hidden>
  				<table cellpadding="0" cellspacing="0" class="look-table">
  					<tr>
  						<td><span class="name-title">证件名称：</span>
  							<input name="pictureName.pic_name" value="${pictureName.pic_name}" id="pic_name" style="width: 200px;height: 25px;"/>
  						</td>
  					</tr>
  					<tr>
  						<td><span class="name-title">是否上传照片：</span>
  							<s:radio list="#{'0':'否','1':'是'}" name="pictureName.mast"></s:radio></td>
  						</td>
  					</tr>
  					<tr style="height: 40px;line-height: 40px;">
  						<td><span class="name-title">证件说明：</span>
  							<s:textarea rows="4" cols="40" name="pictureName.remark"></s:textarea>
  						</td>
  					</tr>
  				</table>
  			</form>
  			</div>
  			<div class="button-container">
		  		<div class="btn-container" style="padding-top: 22px;">
				  	<button class="submit" onclick="tijiao()" id="btnOK">确定</button>
				  	<button type="button" class="return" onclick="openlink('${basePath}/manage/pictureName/list.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','',0)">返回</button>
				</div>
  			</div>
  	</body>
    <script type="text/javascript">
  		function tijiao(){
  	  		var pic_name = $("#pic_name").val();
  	  		if(pic_name.trim().length == 0){
  	  	  		alert("请填写证件名称！");
  	  	  		return false;
  	  		}
		  	if(confirm('确认提交？')){
				//有一个事件触发事件可以兼容ie
			  	setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1); 
			  	openlink('${basePath}/manage/pictureName/update.action?searchTearm1=${searchTearm1 }&searchTearm2=${searchTearm2 }&pageBean.currentPage=${pageBean.currentPage}','Form',1);
		  	}
  		}
  	</script>
</html>