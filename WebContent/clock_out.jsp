<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
	import="clock.*,login.*"
%>

<%
	Cookie[] cookies=null;
	cookies=request.getCookies();
	String sternID=null;
	if(cookies != null){
		for(int i= 0; i < cookies.length; ++i){
			if(cookies[i].getName().equals("stern_id")){
				sternID=cookies[i].getValue();
				break;
			}
		}
	}
	if(sternID != null)
		new ClockOut(sternID);
	response.sendRedirect("index.jsp");
%>

<html>
<body>
</body>
</html>

    