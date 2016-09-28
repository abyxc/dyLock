<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="config.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>

	<body>
		<center>
			对不起，系统异常！<br>
			<s:actionerror></s:actionerror><br>
			<input onclick="history.go(-1)" type="button" value="返回">
		</center>
	</body>
</html>
