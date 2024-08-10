package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project_utils.ConnectionProvider;

@WebServlet("/signUpServlet")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		String name = req.getParameter("userName");
		String email = req.getParameter("userEmail");
		String mobileNumber = req.getParameter("userMobileNumber");
		String securityQuestion = req.getParameter("userSecurityQuestion");
		String securityAnswer = req.getParameter("userSecurityAnswer");
		String password = req.getParameter("userPassword");
	

		try {
			
			Connection con = ConnectionProvider.getConnection();
			
			PreparedStatement selectQuery = con.prepareStatement("SELECT * FROM users WHERE email=?");
			selectQuery.setString(1, email);
			ResultSet rs = selectQuery.executeQuery();
			
			if(!rs.next()) {
				PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?)");
				
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, mobileNumber);
				ps.setString(4, securityQuestion);
				ps.setString(5, securityAnswer);
				ps.setString(6, password);
				ps.setString(7, "");
				ps.setString(8, "");
				ps.setString(9, "");
				ps.setString(10, "");
				
				int count = ps.executeUpdate();
				if(count > 0) {
					res.sendRedirect("signup.jsp?msg=valid");
				}
			}else {
				res.sendRedirect("signup.jsp?msg=invalid");
			}
			
			
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			res.sendRedirect("signup.jsp?msg=invalid");
		}
		
	}

}
