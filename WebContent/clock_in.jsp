<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import="clock.ClockIn,login.ITTPLogin" 
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
		new ClockIn(sternID);
	
	response.sendRedirect("index.jsp");
%>
	