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

@WebServlet("/paymentForOrderServlet")
public class PaymentForOrderServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("email");
		
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String mobileNumber = req.getParameter("mobileNumber");
		String paymentMethod = req.getParameter("paymentMethod");
		String transactionId = "";
		transactionId = req.getParameter("transactionId");
		String status = "bill";
		
		try {
			
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET address = ?, city = ?, state = ?, country = ?, mobileNumber = ? WHERE email = ?");
			ps.setString(1, address);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, country);
			ps.setString(5, mobileNumber);
			ps.setString(6, email);
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = con.prepareStatement("UPDATE cart SET address = ?, city = ?, state = ?, country = ?, mobileNumber = ?, orderDate = now(), deliveryDate = DATE_ADD(orderDate, INTERVAL 7 DAY), paymentMethod = ?, transactionId = ?, status = ? WHERE email = ? AND address is NULL");
			ps2.setString(1, address);
			ps2.setString(2, city);
			ps2.setString(3, state);
			ps2.setString(4, country);
			ps2.setString(5, mobileNumber);
			ps2.setString(6, paymentMethod);
			ps2.setString(7, transactionId);
			ps2.setString(8, status);
			ps2.setString(9, email);
			
			ps2.executeUpdate();
			
			res.sendRedirect("bill.jsp");
			
			
		}catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		
	}
}
