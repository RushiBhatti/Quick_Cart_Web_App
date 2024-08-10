<%@page import="project_utils.ConnectionProvider"%>
<%@page import="java.sql.*"%>

<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/messageUs.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>Message Us</title>
</head>
<body>
	<div style="color: white; text-align: center; font-size: 30px;">
		Message Us <i class='fas fa-comment-alt'></i>
	</div>

	<%
	String msg = request.getParameter("msg");
	
	if("success".equals(msg))
	{
	%>
	<h3 style="text-align: center; color: yellow;">Message
		successfully sent. Our team will contact you soon!</h3>
	<%} %>
	
	<%
	if("failed".equals(msg))
	{
	%>
	<h3 style="text-align: center;">Some thing Went Wrong! Try Again!</h3>
	<%} %>
	
	<form action="messageUsServlet" method="post">
	
		<input class="input-style" type="text" name="subject" placeholder="Enter Subject" required/>
		<hr>
		<textarea class="input-style" name="messageBody" placeholder="Enter Message" required></textarea>
		<hr>
	
		<button class="button" type="submit">Send Message</button>
	</form>

	<br>
	<br>
	<br>
</body>
</html>