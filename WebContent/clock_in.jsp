<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import="clock.ClockIn,login.ITTPLogin" 
  %>

<% 
	new ClockIn(ITTPLogin.getUserId());
	response.sendRedirect("index.jsp");
%>
	