<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="CSS/login.css" rel="stylesheet" type="text/css" />
		<title> Stern ITTP </title>
	</head>
<body>
	<div id="container">
		<form onsubmit="calculateTimeWorked()">
			<div id="stern_id">
				<input type="text" name="stern_id" placeholder="enter stern id">
			</div>
			<div id="start_time">
				<input type="datetime-local" name="sdate">
			</div>
			<div id="sday_time">
				<input type="radio" name="dt" value="AM"> <input
					type="radio" name="dt" value="PM">
			</div>
			<div id="end_time">
				<input type="datetime-local" name="edate">
			</div>

			<div id="eday_time">
				<input type="radio" name="dt" value="AM"> <input
					type="radio" name="dt" value="PM">
			</div>
			<div id="submit">
				<input type="button" name="search_button" value="search">
			</div>
		</form>
	</div>
</body>
</html>
