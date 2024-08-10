package project_servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project_utils.ConnectionProvider;

@WebServlet("/continueShoppingServlet")
public class ContinueShoppingServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String status = "processing";
		
		try {
			
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE cart SET status = ? WHERE email = ? AND status = 'bill'");
			
			ps.setString(1, status);
			ps.setString(2, email);
			ps.executeUpdate();
			
			res.sendRedirect("home.jsp");
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
	}
}
