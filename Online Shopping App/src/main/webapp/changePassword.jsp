<%@page import="project_utils.ConnectionProvider"%>
<%@page import="java.sql.*"%>

<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/changeDetails.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Change Password</title>
</head>
<body>
	<%
	String msg = request.getParameter("msg");
	
	if("notMatch".equals(msg))
	{
	%>
	<h3 class="alert">New password and Confirm password does not
		match!</h3>
	<%} %>
	<%
	if("wrong".equals(msg))
	{
	%>
	<h3 class="alert">Your old Password is wrong!</h3>
	<%} %>
	<%
	if("success".equals(msg))
	{
	%>
	<h3 class="alert">Password change successfully!</h3>
	<%} %>
	<%
	if("invalid".equals(msg))
	{
	%>
	<h3 class="alert">Some thing went wrong! Try again!</h3>
	<%} %>


	<form action="changePasswordServlet" method="post">
	<h3>Enter Old Password</h3>
	<input class="input-style" type="password" name="oldPassword" placeholder="Enter Old Password" required/>
	<hr>
	<h3>Enter New Password</h3>
	<input class="input-style" type="password" name="newPassword" placeholder="Enter New Password" required/>
	<hr>
	<h3>Enter Confirm Password</h3>
	<input class="input-style" type="password" name="confirmPassword" placeholder="Cofirm Password" required/>
	<hr>
	
	<button class="button" type="submit">Save<i class='far fa-arrow-alt-circle-right'></i></button>
	</form>
</body>
<br>
<br>
<br>
</html>