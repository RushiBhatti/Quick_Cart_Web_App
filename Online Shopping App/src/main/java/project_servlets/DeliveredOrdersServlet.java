package project_servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project_utils.ConnectionProvider;

@WebServlet("/deliveredOrdersServlet")
public class DeliveredOrdersServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String status = "Delivered";
		
		try
		{
			
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE cart SET status = '"+status+"' WHERE product_id = '"+id+"' AND email = '"+email+"' AND address IS NOT NULL");
			res.sendRedirect("admin/ordersReceived.jsp?msg=delivered");
		} catch (Exception e) 
		{
			System.out.println("Exception : " + e.getMessage());
			res.sendRedirect("admin/ordersReceived.jsp?msg=failed");
		}
	}
}