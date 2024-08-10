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

@WebServlet("/incDecQuantityServlet")
public class IncDecQuantityServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		String id = req.getParameter("id");
		String incdec = req.getParameter("quantity");
		int price = 0;
		int total = 0;
		int quantity = 0;
		int final_total = 0;
		
		try {
			
			Connection connection = ConnectionProvider.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cart WHERE email = '"+email+"' AND product_id = '"+id+"' AND address is NULL");
			while(rs.next()) {
				price = rs.getInt(4);
				total = rs.getInt(5);
				quantity = rs.getInt(3);
			}
			
			if(quantity == 1 && incdec.equals("decr")) {
				res.sendRedirect("myCart.jsp?msg=notPossible");
			}
			else if (quantity != 1 && incdec.equals("decr")) {
				total = total - price;
				quantity = quantity - 1;
				st.executeUpdate("UPDATE cart SET total = '"+total+"', quantity = '"+quantity+"' WHERE email = '"+email+"' AND product_id = '"+id+"' AND address is NULL");
				res.sendRedirect("myCart.jsp?msg=decr");
			}
			else {
				total = total + price;
				quantity = quantity + 1;
				st.executeUpdate("UPDATE cart SET total = '"+total+"', quantity = '"+quantity+"' WHERE email = '"+email+"' AND product_id = '"+id+"' AND address is NULL");
				res.sendRedirect("myCart.jsp?msg=incr");
			}
			
		}catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
	}
}
