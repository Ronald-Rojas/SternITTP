<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="report.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="CSS/admin.css" rel="stylesheet" type="text/css" />
		<title> Stern ITTP </title>
	</head>
<body>
	<div id="container">
		<img id="stern_logo" src="img/stern_white_rgb.png" alt="STERN LOGO" id="stern_logo">
		<div id="search_hours">
		<form action="user_report.jsp" method="post">
			<div id="stern_id">
				<input type="text" name="stern_id" placeholder="ENTER STERN ID">
			</div>
			
			<div id="start_time">
				<input type="date" name="sdate" placeholder="BEGIN SEARCH DATE RANGE">
			</div>
			
			<div id="end_time">
				<input type="date" name="edate" placeholder="END SEARCH DATE RANGE">
			</div>
			
			<div id="submit">
				<input type="submit" name="search_button" value="LOOKUP IT USER HOURS">
			</div>
			<hr>
		</form>
			<form action="master_report.jsp" method="post">
				<div id="start_time">
					<input type="date" name="sdate" placeholder="BEGIN SEARCH DATE RANGE">
				</div>
			
				<div id="end_time">
					<input type="date" name="edate" placeholder="END SEARCH DATE RANGE">
				</div>
				<div id="submitAll">
					<input type="submit" name="generate_report" value="GENERATE MASTER REPORT">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
