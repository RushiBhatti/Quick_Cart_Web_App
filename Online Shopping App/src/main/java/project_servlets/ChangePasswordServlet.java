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

@WebServlet("/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		
		if(!(newPassword.equals(confirmPassword)))
		{
			res.sendRedirect("changePassword.jsp?msg=notMatch");
		}else 
		{
			try 
			{
				Connection con = ConnectionProvider.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM users WHERE email = '"+email+"' AND password = '"+oldPassword+"'");
				
				if(rs.next()) 
				{
					st.executeUpdate("UPDATE users SET password = '"+newPassword+"' WHERE email = '"+email+"'");
					res.sendRedirect("changePassword.jsp?msg=success");
				}else 
				{
					res.sendRedirect("changePassword.jsp?msg=wrong");	
				}
				
			}catch (Exception e) {
				System.out.println("Exception : " + e.getMessage());
				res.sendRedirect("changePassword.jsp?msg=invalid");
			}
		}
	}
}
