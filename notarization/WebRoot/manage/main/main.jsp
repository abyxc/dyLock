<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/config.jsp" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chome=1"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <meta name="robots" content="index,follow"/>
    
    <link href="${basePath}/images/title.ico" rel="shortcut icon"/>
    <title>国立公证处管理系统</title>
    <script type="text/javascript">
    	function showHideDiv(){
			$('#content-right').append('<div class="alert_div" ></div><div class="loaders"><a><img src="${basePath}/images/ajax-loader.gif"></a></div>');
    	}
	    function openlink(src,id,m){
	    	showHideDiv();
	        if(m==0){
	       		if(src.indexOf("?") > 0){
		        	src += "&a="+Math.random();
	        	}else{
		        	src += "?a="+Math.random();
	        	}
	        	src=encodeURI(encodeURI(src));
	            $(".content-right").load(src);
	        }else{
	        	src=encodeURI(encodeURI(src));
	          	$.ajax({   
					async:false,    
					cache:false,        	       		
					type:"post",
			        url: src,	 
	      		    data:$("#"+id).serialize()+"&a="+Math.random(),
			        success:function(d) {
			        	$(".content-right").html(d);
			        }
		       });
	        }	
	    }   	    
    </script>
</head>
<body style="background-color: #F4F4F4;"> 
	<div class="header">
	    <div class="header-left">
	        <span class="logo">国立公证后台管理系统</span>
	    </div>
	    <div class="header-right">
	        <span class="link-container"><a href="#" class="home">首页</a></span>
	        <span class="link-container"><a href="#" onclick="openlink('${basePath}/manage/user/toUpdateUC.action','',0)" class="personal-data">个人资料</a></span>
	        <span class="link-container"><a href="${basePath}/exit.jsp" onclick="javascript:if(confirm('确定退出吗？')){return true;}else{return false;};" class="exit">安全退出</a></span>
	    </div>
	</div>
	<div class="nav">
	    <div class="welcome">
	        <img src="${basePath}/images/main/welcome_icon.png" alt=""/><a href="#" title="${user.loginname }"><s:if test="user.loginname.length()>6"><s:property value="user.loginname.substring(0,6)"/>...</s:if><s:else>${user.loginname}</s:else>,欢迎您！</a>
	    </div>
	    <ul class="list-first">
			<s:iterator value="#application.stackPrivileges" var="p">
			<s:if test="code in #session.sessionPriCodes">
	 			<li class="first"><span>${name}</span>
	              <ul class="list-second">
	             	<s:iterator value="children"  status="st2">
		  				<s:if test="code in #session.sessionPriCodes">
		           			<li class="second">
		           				<a name="${basePath}/${url}" target="content" >${name}</a>
		           			</li>
		                 </s:if>
	                 </s:iterator>
	             </ul>
	        	</li>
			</s:if>
		    </s:iterator>                
	    </ul>
	</div>        
	<div class="container">
	    <div class="content-left"></div>
	    <div class="content-right" id="content-right"></div>
	</div>
	 <script>
	    $(function(){
	    	getIndexJsp();
	        $(".first").click(function(){
	            $(".first").css("background","#185c90");
	            $(this).css("background","#e4550e");
	            $(".list-second").css("display","none");
	            $(this).children('ul.list-second').css("display","block");
	        });
	        
	        $("li.first span").click(function () {
	            var name=$(this).siblings(".list-second").children("li:eq(0)").children('a').attr("name");
	            $(".content-right").load(name+"?a="+Math.random());
	            $(this).siblings(".list-second").children("li").removeClass("active");
	            $(this).siblings(".list-second").children("li:eq(0)").addClass("active");
	        });
	        $(".second").click(function () {
	            $(this).addClass("active").siblings().removeClass("active");
	        });
	        $("ul.list-first a").click(function(){
	        	showHideDiv();
	          	$(".content-right").load($(this).attr('name')+"?a="+Math.random());
	        });
	        $(".home").click(function(){
	        	getIndexJsp();
	        });
	    });
	    //打开首页
	    function getIndexJsp(){
	    	$(".content-right").load("${basePath}/manage/main/index.jsp");
	    }
	</script>    
	<script>
	    var arr1=['home-icon','per-info-icon','quit-icon'];
	    $(".link-container a").click(function(){
	    	for(var i=0; i<arr1.length;i++){
	    		 $(".link-container a").eq(i).css({"background-image":"url(${basePath}/images/main/"+arr1[i]+".png)",
		    		 "color":"#fff"});
	    	}
	    	var index=$(".link-container").index($(this).parent());
	    	$(".link-container a").eq(index).css({"background-image":"url(${basePath}/images/main/"+arr1[index]+"2.png)",
		    	"color":"#a7dffa"});
	    });
	    
	</script> 
    
</body>
	<style type="text/css">
	.alert_div{
		background: rgb(0, 0, 0) none repeat scroll 0% 100%;
		width: 100%; height: 100%; 
		position: absolute; 
		top: 0px; left: 0px; 
		z-index: 1111; 
		opacity: 0.4;
	}
	.loaders{
		position:absolute;
		left: 51%; 
		top:42%;
		z-index:1122;
		padding: 15px;
	}
	</style> 
</html>
