<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	 	<meta charset="UTF-8">
   	 	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    	<title>杭州国立公证处</title>
    	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    	<link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
    	<link href="${basePath}/images/title.ico" rel="shortcut icon"/>
	</head>
	<body>
	<header>
   		<div class="reminder">
        <div class="reminder-icon"></div>
        	<p class="reminder-text">提交成功！</p>
    	</div>
	</header>
	<section>
    	<div class="container">
        	<div class="text">
            	尊敬的用户，您在线申办已提交成功！申办号为：<br/><span class="Num">${uploadMain.order_num }</span><br/>
            	我们将尽快审批完您的公证请求，将在2个工作日内我们将以短信、电话或者手机app的消息提醒等方式告知您是否通过及具体来我处办理时间。
        	</div>
        	<div class="text">
           	 如您对本次申办订单有疑问请选择拨打：<span class="Num">0556-00000000</span>取得帮助，谢谢！
        	</div>
    	</div>
	</section>
	<script>
    	$(function(){
        	var W=$(".reminder-icon").css("width");
        	$(".reminder-icon").css("height",W);
    	});
	</script>
</body>
</html>