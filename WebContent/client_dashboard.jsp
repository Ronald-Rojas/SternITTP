<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
	import="java.util.Calendar"
    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="CSS/client.css" rel="stylesheet" type="text/css" />
<title>Stern ITTP Client Dashboard</title>
</head>
<body onload="updateTime()">
	<div id="container">
	<img id="stern_logo" src="img/stern_white_rgb.png" alt="STERN LOGO" id="stern_logo">
	
		<div id="digital_clock">
			<script type="text/javascript">
			function formatTime(t){
				if(t < 10)
					return "0"+t;
				return t; 
			}
				function updateTime(){
					var date=new Date();
					var h=date.getHours();
					var m=formatTime(date.getMinutes());
					var s=formatTime(date.getSeconds());
					var doc=document.getElementById("digital_clock");
					doc.innerHTML=h+":"+m+":"+s;
				}		
				var loop=setInterval(function(){updateTime()},500);
			</script>
		</div>
		
		<div id="clock_in_group">
			<form id="clock_in_form" action="clock_in.jsp">
				<input id="cin" type=submit name="clock-in" value="CLOCK IN">
			</form>
		</div>
		
		<div id="clock_out_group">
			<form id="clock_out_form" action="clock_out.jsp">
				<input id="cout" type=submit name="clock-out" value="CLOCK OUT">
			</form>
		</div>
	</div>
</body>
</html>