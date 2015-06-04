<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
	import="clock.ClockOut,login.ITTPLogin"
%>

<%
	new ClockOut(ITTPLogin.getUserId());
	response.sendRedirect("index.jsp");
%>

<html>
<body>
</body>
</html>

    