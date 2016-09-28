<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
	<script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    <title>杭州市国立公证处</title>
</head>
<body>
<header>
    <h1 class="notarization-money">￥${objArr[0]}</h1>
</header>
<section>
    <ul class="goods-ul">
        <li>收款方：<span>杭州国立公证处</span></li>
        <li>品&nbsp;&nbsp;&nbsp;类：<span>${objArr[1]}</span></li>
    </ul>
</section>
<p class="choose-type">支付方式</p>
<section>
    <div class="pay-container">
        <span class="pay-type">微信支付</span>

        <div class="input-group">
            <input type="radio" id="weixin" class="wx" name="pay" checked />
            <label for="weixin"></label>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <button class="buy-now" onclick="payMoney('${uploadMain.id}')">立即支付</button>
    </div>
</section>

<script>
	function payMoney(id){
		alert("此功能开发中！谢谢");
	}
</script>

</body>
</html>