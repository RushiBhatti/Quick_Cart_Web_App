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

@WebServlet("/changeMobileNumberServlet")
public class ChangeMobileNumberServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String mobileNumber = req.getParameter("newMobileNumber");
		String password = req.getParameter("password");
		
		try
		{
			
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE email = '"+email+"' AND password = '"+password+"'");
				
			if(rs.next())
			{
				st.executeUpdate("UPDATE users SET mobileNumber = '"+mobileNumber+"' WHERE email = '"+email+"'");
				res.sendRedirect("changeMobileNumber.jsp?msg=success");
			}else
			{
				res.sendRedirect("changeMobileNumber.jsp?msg=failed");				
			}
			
		} catch (Exception e)
		{
			System.out.println("Exception : " + e.getMessage());
		}
	}
}
