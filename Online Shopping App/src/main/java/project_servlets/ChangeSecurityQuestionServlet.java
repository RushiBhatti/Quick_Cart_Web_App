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

@WebServlet("/changeSecurityQuestionServlet")
public class ChangeSecurityQuestionServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String securityQuestion = req.getParameter("newSecurityQuestion");
		String answer = req.getParameter("newAnswer");
		String password = req.getParameter("password");
		
		try
		{
			
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE email = '"+email+"' AND password = '"+password+"'");
			
			if(rs.next())
			{
				st.executeUpdate("UPDATE users SET securityQuestion = '"+securityQuestion+"', answer = '"+answer+"' WHERE email = '"+email+"'");
				res.sendRedirect("changeSecurityQuestion.jsp?msg=success");
			}else
			{
				res.sendRedirect("changeSecurityQuestion.jsp?msg=failed");				
			}
			
		} catch (Exception e)
		{
			System.out.println("Exception : " + e.getMessage());
		}
		
	}
}
