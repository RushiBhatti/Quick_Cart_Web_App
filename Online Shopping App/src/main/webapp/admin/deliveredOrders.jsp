<%@page import="java.sql.*" %>
<%@page import="project_utils.ConnectionProvider"%>
<%@include file="adminHeader.jsp"%>
<%@include file="../footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/ordersReceived-style.css">
<title>Home</title>
<style>
.th-style {
	width: 25%;
}
</style>
</head>
<body>
	<div style="color: white; text-align: center; font-size: 30px;">
		Delivered Orders <i class='fas fa-dolly'></i>
	</div>


	<table id="customers">
		<tr>
			<th>Mobile Number</th>
			<th scope="col">Product Name</th>
			<th scope="col">Quantity</th>
			<th scope="col"><i class="fa fa-inr"></i> Sub Total</th>
			<th>Address</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
			<th scope="col">Order Date</th>
			<th scope="col">Expected Delivery Date</th>
			<th scope="col">Payment Method</th>
			<th scope="col">T-ID</th>
			<th scope="col">Status</th>
		</tr>

		<%
		try
		{	
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cart INNER JOIN products WHERE cart.product_id = products.id AND cart.orderDate IS NOT NULL AND cart.status='Delivered'");
			
			while(rs.next())
			{
		%>

		<tr>
			<td><%=rs.getString(10) %></td>
			<td><%=rs.getString(17) %></td>
			<td><%=rs.getString(3) %></td>
			<td><i class="fa fa-inr"></i> <%=rs.getString(5) %></td>
			<td><%=rs.getString(6) %></td>
			<td><%=rs.getString(7) %></td>
			<td><%=rs.getString(8) %></td>
			<td><%=rs.getString(9) %></td>
			<td><%=rs.getString(11) %></td>
			<td><%=rs.getString(12) %></td>
			<td><%=rs.getString(13) %></td>
			<td><%=rs.getString(14) %></td>
			<td><%=rs.getString(15) %></td>
		</tr>
		
		<%
			}
		}catch(Exception e)
		{
			System.out.println("Exception : " + e.getMessage());
		}
		%>
	</table>
	<br>
	<br>
	<br>

</body>
</html>