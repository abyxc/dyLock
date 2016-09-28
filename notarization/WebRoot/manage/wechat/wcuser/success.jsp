<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    <title>杭州国立公证处</title>
</head>
<body onload="goMainJsp();">
<header>
    <div class="reminder">
    	<a href="#" onclick="tiaozhuan();">
	        <div class="reminder-icon"></div>
    	</a>
        <p class="reminder-text">注册成功！</p>
    </div>
</header>
<section>
    <div class="container">
        <div class="text">
          	尊敬的用户，您已注册成功！
          	<span class="Num">
          		<span class="Num" id="daoshu">5</span>秒后页面自动跳转，或点击上边绿色图标进行跳转!
          	</span>
        </div>
        <div class="text">
                               如您对本次操作有疑问请联系我们;
            <br/>
                               咨询电话：<span class="Num">0571-85105000（国内公证） </span><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		<span class="Num">0571-85060513（涉外公证） </span><br/>
                               网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 址：<a href="http://www.hz-notary.com">www.hz-notary.com</a>
        </div>
    </div>
    <form action="${basePath}/manage/wechat/wcuser/toMain.action" method="post" id="Form">
    	<input type="hidden" name="code" id="code" value="${code}"/>
    </form>
</section>
<script>
	var i = 5;  
	function remainTime(){  
	    if(i >= 0){
		    document.getElementById('daoshu').innerHTML=i--;  
	    }
	    if(i == -1){
	    	tiaozhuan();
	    }
	    setTimeout("remainTime()",1000);  
	}  
	remainTime();

	function tiaozhuan(){
		$("#Form").submit();	
	}
	
    $(function(){
        var W=$(".reminder-icon").css("width");
        $(".reminder-icon").css("height",W);
    });
</script>
</body>
</html>