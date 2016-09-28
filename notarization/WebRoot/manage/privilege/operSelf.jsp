<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

  	</head>
  	<body>
		<form action="${basePath}/manage/privilege/update.action" id="Form" method="post">
			<s:hidden name="parent_id"></s:hidden>
			<s:hidden name="privilege.id"></s:hidden>
			<ol style="margin:0; padding:0; list-sytle:none;">
				<li>
					权限名称：<s:textfield name="privilege.name" id="name"></s:textfield>
				</li>
				<li>
					权限路径：<s:textfield name="privilege.url" id="url"></s:textfield>
				</li>
				<li>
					权限排序：<s:textfield name="privilege.order_num" id="order_num" onkeyup="clearNoNum(this);"></s:textfield>
				</li>
				<li>
					权限唯一标识符：<s:textfield name="privilege.code" id="code"></s:textfield>
				</li>
				<li>
					上级权限名称：${parent_name}
				</li>
			</ol>
			
			<input type="button" value="提交" id="btnOK" onclick="check();"/>
			<input type="button" value="返回" onclick="history.go(-1)"/>
		</form>
  	</body>
  	
  	<script type="text/javascript">
	  	//只允许输入数字和小数点
	  	function clearNoNum(obj){  
		  	obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符  
	  	}
  		function check(){
  			var name = $("#name").val().replace(/(^\s*)|(\s*$)/g, "");
  			var url = $("#url").val().replace(/(^\s*)|(\s*$)/g, "");
  			var order_num = $("#order_num").val().replace(/(^\s*)|(\s*$)/g, "");
  			var code = $("#code").val().replace(/(^\s*)|(\s*$)/g, "");

  			if(name.length == 0){
  	  			$("#name").val("");
  	  			alert("请填写权限名称！");
  	  			return false;
  			}
  			if(order_num.length == 0){
  	  			$("#order_num").val("");
  	  			alert("请填写权限排序！");
  	  			return false;
  			}
  			if(code.length == 0){
  	  			$("#code").val("");
  	  			alert("请填写权限唯一标识符！");
  	  			return false;
  			}
  			if(confirm("确认提交？")){
  				setTimeout(function () { $('#btnOK').attr('onclick', '').bind('click', function () { reclick(); }); }, 1); //有一个事件触发事件可以兼容ie
  				$("#Form").submit();
  			}
  		}
  	</script>
</html>