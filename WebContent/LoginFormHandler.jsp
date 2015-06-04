<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    import="login.ITTPLogin"
%>

<% 
	final String U="userid",P="pass";
	String net_id =request.getParameter("stern_id");
	String password=request.getParameter("stern_passphrase");
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