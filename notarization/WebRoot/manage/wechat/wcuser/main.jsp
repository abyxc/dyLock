<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>杭州国立公证</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<article>
    <div class="index-all">
        <div class="index-know">
            申办须知
        </div>
        <div class="index-first">
            1.业务范围：涉外及国内常用公证业务<br/>
            2.申办流程：填写申办信息》提交申办》等待审核》
            在线支付》线下领取公证书<br/>
            3.公证书领取方式：携带申办公证事项所要求的证据
            资料原件到公证处签字并领取公证书
            <br/><br/>
        </div>
        <div class="index-serve">公证服务</div>

    </div>
    <div class="index-second">
        <div class="index-bid">
            <a href="${basePath}/manage/wechat/wcuploadMain/toCheck.action" id="online-bid">在线申办</a>
            <a href="${basePath}/manage/wechat/wcbid/list.action" id="my-bid">我的申办</a>
        </div>
    </div>
</article>
<section>
    <div class="pop-up"></div>
    <div class="register-tip">
        <p class="tip">系统检测出您尚未注册，请点击下方按钮前往注册</p>
        <a href="#" onclick="tijiao();" class="register-link">立即注册</a>
    </div>
    <form action="${basePath}/manage/wechat/wcuser/toRegister.action" method="post" id="Form">
		<input type="hidden" name="code" value="${code}"/>
	</form>
</section>
<script>
	window.onload = function () {
		var code = '${code}';
		if(code.length > 0){
			 $(".pop-up").delay(100).fadeIn(400);
			 $(".register-tip").delay(140).fadeIn(1000);
		}
	}

	function tijiao(){
		$("#Form").submit();
	}
</script>
</body>
</html>