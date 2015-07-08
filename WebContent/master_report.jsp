<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.io.*,report.MasterReport,java.io.IOException"
 %>
 <%
    
    String sid=request.getParameter("stern_id");
	String begin_date=request.getParameter("sdate");
	String end_date=request.getParameter("edate");
	
	String path="";
	String username=System.getProperty("user.name");
	
	if((System.getProperty("os.name").toLowerCase().contains("mac"))
	   || (System.getProperty("os.name").toLowerCase().contains("nix"))){
		path="/Users/"+username+"/Downloads/";
		System.out.println(path);
	}
		
	else if(System.getProperty("os.name").toLowerCase().contains("win")){
		
		path="C:\\Users\\" +username+"\\Downloads\\";
	}
	try{
    	FileOutputStream fileOut = new FileOutputStream(path+"SternMaster.xls");
    	MasterReport sternMasterReport= new MasterReport(begin_date,end_date);
    	sternMasterReport.generateExcelRecords().write(fileOut);
    }catch(IOException e){}
%>