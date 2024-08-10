<%@page import="java.sql.*" %>
<%@page import="project_utils.ConnectionProvider"%>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Cart</title>
<style>
h3 {
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
	<div style="color: white; text-align: center; font-size: 30px;">
		My Cart <i class='fas fa-cart-arrow-down'></i>
	</div>
	
	
	<%
	String msg = request.getParameter("msg");
	if ("notPossible".equals(msg)){
	%>
	<h3 class="alert">There is only one Quantity! So click on remove!</h3>
	<% } %>
	
	<%
	if ("incr".equals(msg)){
	%>
	<h3 class="alert">Quantity Increased Successfully!</h3>
	<% } %>
	
	<%
	if ("decr".equals(msg)){
	%>
	<h3 class="alert">Quantity Decreased Successfully!</h3>
	<% } %>

	<%
	if ("removed".equals(msg)){
	%>
	<h3 class="alert">Product Successfully Removed!</h3>
	<% } %>
	
	
	<table>
		<thead>

		<%

		int total = 0;
		int sno = 0;
		try{
			
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			ResultSet rs1 = st.executeQuery("SELECT sum(total) FROM cart WHERE email='"+email+"' AND address is NULL");
			while(rs1.next()){
				total = rs1.getInt(1);
			}
			
		%>

			<tr>
				<th scope="col" style="background-color: yellow;">Total:  <%= total %><i
					class="fa fa-inr"></i>
				</th>
				<%if(total > 0){ %>
				<th scope="col"><a href="addressPaymentForOrder.jsp">Proceed to order</a></th>
				<%} %>
			</tr>
		</thead>
		<thead>
			
			<tr>
				<th scope="col">S.No</th>
				<th scope="col">Product Name</th>
				<th scope="col">Category</th>
				<th scope="col"><i class="fa fa-inr"></i> price</th>
				<th scope="col">Quantity</th>
				<th scope="col">Sub Total</th>
				<th scope="col">Remove <i class='fas fa-trash-alt'></i></th>
			</tr>
		</thead>
		<tbody>

		<%
		ResultSet rs2 = st.executeQuery("SELECT * FROM products INNER JOIN cart ON products.id = cart.product_id AND cart.email = '"+email+"' AND cart.address is NULL");
		while(rs2.next())
		{
		%>
			<tr>

			<%
			sno = sno + 1; // serial No
			%>
			
				<td><%out.print(sno); %></td>
				<td><%=rs2.getString(2) %></td>
				<td><%=rs2.getString(3) %></td>
				<td><i class="fa fa-inr"></i><%=rs2.getString(4) %></td>
				<td><a href="incDecQuantityServlet?id=<%=rs2.getString(1) %>&quantity=incr">+<i class='fas fa-plus-circle'></i></a><%=rs2.getString(8) %> <a
					href="incDecQuantityServlet?id=<%=rs2.getString(1) %>&quantity=decr">-<i class='fas fa-minus-circle'></i></a></td>
				<td><i class="fa fa-inr"></i><%=rs2.getString(10) %></td>
				<td><a href="removeFromCartServlet?id=<%=rs2.getString(1) %>">Remove <i class='fas fa-trash-alt'></i></a></td>
			</tr>

		<%
				}
			
		}catch(Exception e){
			System.out.println("Exception Occured!");
			System.out.println("Exception : " + e.getMessage());
		}
		%>

		</tbody>
	</table>
	<br>
	<br>
	<br>

</body>
</html>