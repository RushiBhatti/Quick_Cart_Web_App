<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Signup</title>
</head>
<body>

	<div id='container'>

		<div class='signup'>

			<form action="signUpServlet" method="post">
				<input type="text" name="userName" placeholder="Enter Name" required/> 
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
				<input type="password" name="userPassword" placeholder="Enter Password" required/> 
				<input type="submit" value="Sign up" />
			</form>

			<h2>
				<a href="login.jsp">Login</a>
			</h2>
		</div>

		<div class='whysign'>

			<%
  String msg = (String)request.getParameter("msg");
  if("valid".equals(msg)){
  %>
			<h1 style="color: green">Successfully Registered!</h1>
  <%}if("invalid".equals(msg)){ %>
			<h1>Registration Failed! Try Again..!</h1>
  <%} %>


			<h2>Quick Cart</h2>
			<p>The Online Shopping System is the application that allows the
				users to shop online without going to the shops to buy them.
				<br>This Project is developed by RB Bhatti.
				<br>TechStack : HTML, CSS, Servlets, JSP, JDBC, MySQL
			</p>
		</div>
	</div>

</body>
</html>