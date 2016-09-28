<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link type="image/x-icon" rel="shortcut icon" href="${basePath}/images/favicon.ico"/>
	</head>

	<body>
		<center>
			未找到服务器！<br>
			<s:actionerror></s:actionerror><br>
			<input onclick="history.go(-1)" type="button" value="返回">
		</center>
	</body>
</html>
