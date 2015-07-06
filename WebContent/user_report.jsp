<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="report.UserReport,report.Entry,java.util.ArrayList"
%>

<%
	String sid=request.getParameter("stern_id");
	String begin_date=request.getParameter("sdate");
	String end_date=request.getParameter("edate");
	
	UserReport user=new UserReport(sid,begin_date,end_date);
	double time=user.getTotalTimeWorked();
	
	ArrayList<Entry> timeSlots= user.getTimeSlotsWorked();
	String [] startTimeStamp=new String[timeSlots.size()],
	          endTimeStamp=new String[timeSlots.size()];
	          
	
	for(int i=0; i < timeSlots.size(); ++i){
		startTimeStamp[i]=timeSlots.get(i).getStartTime();
		endTimeStamp[i]=timeSlots.get(i).getEndTime();
	}
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="CSS/report.css" rel="stylesheet" type="text/css" />
		<title> Stern ITTP </title>
	
	</head>
<script language="javascript">
	function displayTimeEntries(){
		
		var s1='TIME START'; var s2='TIME END';
		
		var table_area=document.getElementById('time_posts');
		var table=document.createElement('table');
		
		var r1=document.createElement('tr');
		var col_h1=document.createElement('th');
		var col_h2=document.createElement('th');
		var col_h1_node=document.createTextNode(s1);
		var col_h2_node=document.createTextNode(s2);
		
		col_h1.appendChild(col_h1_node);
		col_h2.appendChild(col_h2_node);
		r1.appendChild(col_h1);
		r1.appendChild(col_h2);
		table.appendChild(r1);
		
		var st=<%= user.toJavascriptArray(startTimeStamp)%>;
		var et=<%= user.toJavascriptArray(endTimeStamp)%>;
		
		for(var k=0; k < st.length; ++k){
			var r=table.insertRow(k+1);
			var c1=r.insertCell(0);
			var c2=r.insertCell(1);
			c1.innerHTML=st[k]; c2.innerHTML=et[k];
		}
		table.style.border='1px solid black';
		table.style.background='black';
		table_area.appendChild(table);
	}
</script>
<body onload="displayTimeEntries()">
	<div id="res">
		<div id="user_info">
			<%=sid %> worked for <%=time%> hours
		</div>
		<br>
		<div id="time_posts">
		
		</div>
	 </div>
</body>
</html>