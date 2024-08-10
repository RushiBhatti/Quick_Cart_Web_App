<%@page import="project_utils.ConnectionProvider"%>
<%@page import="java.sql.*"%>

<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<div style="color: white; text-align: center; font-size: 30px;">
		Home <i class="fa fa-institution"></i>
	</div>
	<table>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Category</th>
				<th scope="col"><i class="fa fa-inr"></i> Price</th>
				<th scope="col">Add to cart <i class='fas fa-cart-plus'></i></th>
			</tr>
		</thead>
		<tbody>
			
			<%
			String searchText = request.getParameter("searchText");
			int result = 0;
			try{
				Connection con = ConnectionProvider.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM ospjava.products WHERE name like '%"+searchText+"%' or category like '%"+searchText+"%' and active='yes'");
				while(rs.next())
				{
					result = 1;
			%>
			
			<tr>
				<td><%=rs.getString(1) %></td>
				<td><%=rs.getString(2) %></td>
				<td><%=rs.getString(3) %></td>
				<td><%=rs.getString(4) %> <i class="fa fa-inr"></i></td>
				<td><a href="addToCartServlet?id=<%=rs.getString(1) %>">Add to cart <i class='fas fa-cart-plus'></i></a></td>
			</tr>
			
			<%
				}
			%>
			
		</tbody>
	</table>

	<%
	 if(result == 0){ 
	%>
	<h1 style="color: white; text-align: center;">No results found!</h1>
	<%
		}
			} catch(Exception e){
				System.out.println("Exception" + e);
				e.printStackTrace();
			}
			%>
	<br>
	<br>
	<br>
	<div class="footer">
		<p>All right reserved by RB Bhatti</p>
	</div>

</body>
</html>