package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project_utils.ConnectionProvider;

@WebServlet("/logInServlet")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");
		
		if(email.equals("admin@gmail.com") && password.equals("admin123")) {
			HttpSession session = req.getSession();
			session.setAttribute("email", email);
			res.sendRedirect("admin/adminHome.jsp");
		}
		else {
			
			try {
				Connection con = ConnectionProvider.getConnection();
				String selectQuery = "SELECT * FROM users WHERE email = ? AND password = ?";
				
				PreparedStatement ps = con.prepareStatement(selectQuery);
				ps.setString(1, email);
				ps.setString(2, password);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					// login successful!
					HttpSession session = req.getSession();
					session.setAttribute("email", email);
					res.sendRedirect("home.jsp");
					
				}
				else {
					// login failed!
					res.sendRedirect("login.jsp?msg=notExist");
				}
				
			}catch(Exception e) {
				System.out.println(e);
				res.sendRedirect("login.jsp?msg=error");
			}
			
		}
		
	}
}
