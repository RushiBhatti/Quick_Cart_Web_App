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

@WebServlet("/messageUsServlet")
public class MessageUsServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String subject = req.getParameter("subject");
		String body = req.getParameter("messageBody");
		
		try 
		{
			
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO messages(email, subject, body) VALUES(?,?,?)");
			ps.setString(1, email);
			ps.setString(2, subject);
			ps.setString(3, body);
			
			ps.executeUpdate();
			
			res.sendRedirect("messageUs.jsp?msg=success");
			
		} catch (Exception e) 
		{
			System.out.println("Exception : " + e.getMessage());
			res.sendRedirect("messageUs.jsp?msg=failed");
		}
	}
}
