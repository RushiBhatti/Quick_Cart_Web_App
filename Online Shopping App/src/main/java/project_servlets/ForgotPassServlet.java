package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project_utils.ConnectionProvider;

@WebServlet("/forgotPassServlet")
public class ForgotPassServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		String email = req.getParameter("userEmail");
		String mobileNumber = req.getParameter("userMobileNumber");
		String securityQuestion = req.getParameter("userSecurityQuestion");
		String securityAnswer = req.getParameter("userSecurityAnswer");
		String newPassword = req.getParameter("userNewPassword");
	

		try {
			
			Connection con = ConnectionProvider.getConnection();
			
			String query = "SELECT * FROM users WHERE email = '"+email+"' AND mobileNumber='"+mobileNumber+"' "
					+ "AND securityQuestion = '"+securityQuestion+"' AND answer= '"+securityAnswer+"'";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()) {
				String updateQuery = "UPDATE users SET password='"+newPassword+"' WHERE email='"+email+"'";
				Statement st2 = con.createStatement();
				int count = st2.executeUpdate(updateQuery);
				
				if(count > 0) {
					res.sendRedirect("forgotPassword.jsp?msg=done");
				}
			}else {
				res.sendRedirect("forgotPassword.jsp?msg=invalid");
			}
			
			
			con.close();
		}
		catch(Exception e) {
			System.out.println("Exception : " + e);
			e.printStackTrace();
			res.sendRedirect("forgotPassword.jsp?msg=invalid");
		}
		
	}

}
