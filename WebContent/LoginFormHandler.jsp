e<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import="login.*"
%>

<% 
	final String U="userid",P="pass";
	String net_id =request.getParameter("stern_id");
	String password=request.getParameter("stern_passphrase");
	Cookie[] cookies=request.getCookies();
	for(int i=0; i < cookies.length; ++i){
		cookies[i].setMaxAge(0);
		response.addCookie(cookies[i]);
	}
	Cookie sternID= new Cookie("stern_id",net_id);
	response.addCookie(sternID);
	ITTPLogin login=new ITTPLogin(net_id,password);
	if(login.verifyID()){
		if(login.getClientType() == ITTPLogin.ClientType.admin)
			response.sendRedirect("admin_dashboard.jsp");
		else{
			response.sendRedirect("client_dashboard.jsp");
		}
	}
	else{
		response.sendRedirect("index.jsp");
	}
%>
<html>
<body>
</body>
</html>