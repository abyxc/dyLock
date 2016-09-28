<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/config.jsp"%>
<html>
  
  <body>
  <%
	if(session.getAttribute("loginUser") != null)
	  	session.removeAttribute("loginUser");
	if(session.getAttribute("sessionPriCodes") != null)
		session.removeAttribute("sessionPriCodes");	
	if(session.getAttribute("sessionPriUrls") != null)
		session.removeAttribute("sessionPriUrls");
   %>
  <script type="text/javascript">
	  window.top.location.href="${basePath}/login.jsp";
  </script>
  </body>
  
</html>
