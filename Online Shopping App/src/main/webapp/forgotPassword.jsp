<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Forgot Password</title>
</head>
<body>
	<div id='container'>
		<div class='signup'>
			<form action="forgotPassServlet" method="post">
				<input type="email" name="userEmail" placeholder="Enter Email" required/> 
				<input type="number" name="userMobileNumber" placeholder="Enter Number" required/>

				<select name="userSecurityQuestion" required>
					<option value="What was your first car?">What was your
						first car?</option>
					<option value="What is the name of your first pet?">What
						is the name of your first pet?</option>
					<option value="What elementary school did you attend?">What
						elementary school did you attend?</option>
					<option value="What is the name of your town/city where you born?">What
						is the name of your town/city where you born?</option>
				</select> 
				
				<input type="text" name="userSecurityAnswer" placeholder="Enter Answer" required/> 
				<input type="password" name="userNewPassword" placeholder="Enter New Password..." required/> 
				<input type="submit" value="Update Password" />
			</form>
			<h2>
				<a href="login.jsp">Login</a>
			</h2>
		</div>
		<div class='whyforgotPassword'>

		<%
		String msg = (String) request.getParameter("msg");
		if("done".equals(msg)){
		%>
			<h3 style="color:green">Password Changed Successfully!</h3>
		<%} if("invalid".equals(msg)){ %>
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