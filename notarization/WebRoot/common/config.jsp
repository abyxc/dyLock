<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="a" uri="/pris-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--引入js--%>
<script src="${basePath}/js/jquery.js" type="text/javascript"></script>	
<script type="text/javascript" src="${basePath}/My97DatePicker/WdatePicker.js"></script>     
<script src="${basePath}/js/jquery.ui.draggable.js" type="text/javascript"></script>		
<script src="${basePath}/js/jquery.alerts.js" type="text/javascript"></script>
<script type="text/javascript" src="${basePath}/javascript/placeholder.js"></script>

<%--引入css--%>
<link href="${basePath}/css/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />  
<link href="${basePath}/css/main.css" type="text/css" rel="stylesheet">
<style type="text/css">
	body{padding: 0px;margin:0px;font-family: Microsoft YaHei;}
	.pagedivs{
		width: 100%;
		height: 25px;
	}
	.pageLi{
		height: 25px;
		line-height: 25px;
		margin-right: 6px;
		cursor: pointer;
		float: left;
		font-size: 13px;
	}
	.pageDiv{
		width: 500px;;
		float: right;
		height: 25px;
		margin-right: 20px;
	}
</style>
<script type="text/javascript">
function deleteObjs(name,url){
	var checklen=$("input[name='"+name+"']:checked");
	if(checklen.length<1){alert("您还没有选择删除列");return;}
	if(!confirm("确定吗？")){
		return false;
		}
	var beans = "";
	$.each( $("input[name='"+name+"']:checked"), function(i, n){
		  beans += name+"["+i+"].id="+$(n).val()+"&";
		});
	if(url.indexOf("?")<0){
		url += "?";
		}
		window.location.href="${basePath}/"+url+beans; 
}
function checkAll(sign,name){
	var sign = $(sign).attr("checked");
	$.each( $("input[name='"+name+"']"), function(i, n){
		$(n).attr("checked",sign);
		});
	}
	function mouseOverColor(obj){
		$(obj).css("color","006EC6");
	}
	function mouseOutColor(obj){
		$(obj).css("color","");
	}
<%--禁用回车	--%>
	document.onkeypress=function(event)
		{
			e = event ? event : (window.event ? window.event : null);
			if(e.keyCode==13){
				 return false;
		}
	}
</script>