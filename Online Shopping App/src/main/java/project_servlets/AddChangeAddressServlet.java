package project_servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project_utils.ConnectionProvider;

@WebServlet("/addChangeAddressServlet")
public class AddChangeAddressServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		
		try 
		{
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET address = ?, city = ?, state = ?, country = ? WHERE email = ?");
			ps.setString(1, address);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, country);
			ps.setString(5, email);
			
			ps.executeUpdate();
			
			res.sendRedirect("addChangeAddress.jsp?msg=valid");
			
		} catch (Exception e)
		{
			res.sendRedirect("addChangeAddress.jsp?msg=invalid");
			System.out.println("Exception : " + e.getMessage());
		}
	}
}
