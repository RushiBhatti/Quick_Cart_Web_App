
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Login</title>
</head>
<body>
	<div id='container'>
		<div class='signup'>
		
			<form action="logInServlet" method="post">
				<input type="email" name="userEmail" placeholder="Enter Email" required/>
				<input type="password" name="userPassword" placeholder="Enter Password" required/>
				<input type="submit" value="Login"/>
			</form>

			<h2>
				<a href="signup.jsp">SignUp</a>
			</h2>
			<h2>
				<a href="forgotPassword.jsp">Forgot Password?</a>
			</h2>
		</div>
		<div class='whysignLogin'>
		
		<%
		String msg = (String) request.getParameter("msg");
		if("notExist".equals(msg)){
		%>
			<h3 style="color:red">Incorrect UserName or Password!</h3>
		<%} if("error".equals(msg)){%>
			<h3 style="color:red">Some thing Went Wrong! Try Again !</h3>
		<%} %>
			<h2>Quick Cart</h2>
			<p>The Online Shopping System is the application that allows the
				users to shop online without going to the shops to buy them.
				<br>This Project is developed by RB Bhatti.
				<br>TechStack : HTML, CSS, Servlets, JSP, JDBC, MySQL</p>
		</div>
	</div>

</body>
</html>