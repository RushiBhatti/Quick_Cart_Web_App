package project_servlets;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/removeFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		String id = req.getParameter("id");
		
		try {
			
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM cart WHERE email = '"+email+"' AND product_id = '"+id+"' AND address is NULL");
			res.sendRedirect("myCart.jsp?msg=removed");
			
		}catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		
		}
		
	}
}
