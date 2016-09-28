<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head lang="en">
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no">
    <title>杭州市国立公证处</title>
    <link rel="stylesheet" href="../cssjsimg/css/wx-index.css"/>
    <script src="../cssjsimg/js/jquery-1.9.1.min.js"></script>
    <link href="${basePath}/images/title.ico" rel="shortcut icon"/>
</head>
<body>
<header class="bid-header">
    请选择办理的公证事项
</header>
<nav class="bid-nav">
    注：公证书第一份为正本，第二份起为副本；如果您需要副本，请选择数量，默认只提供一份正本；正本和副本具备同等法律效应。
</nav>
<article>
    <form action="${basePath}/manage/wechat/wcuploadMain/toFillRecord.action" method="post" id="Form">
    	<s:hidden name="gz_type"/>
        <table class="bid-table" cellpadding="0" cellspacing="0">
        	<s:iterator value="types" status="sta">
            	<tr><td><input type="radio"  class="box" id="box${sta.index}" name="uploadMain.gz_type_id" value="${id}"/><label for="box${sta.index}"></label><label for="box${sta.index}"><span class="c">${gz_name}</span></label></td></tr>
            </s:iterator>
        </table>
    </form>
</article>
<footer class="bid-footer">
    <a href="#" class="bid-footer-next" onclick="tijiao();">下一步</a>
</footer>
</body>
<SCRIPT type="text/javascript">
	function tijiao(){
		var item = $(":radio:checked"); 
		var len=item.length;
		if(len==0){ 
			alert('请选择公证类型！'); 
			return false;
		} 
		$("#Form").submit();
	}
</SCRIPT>
</html>