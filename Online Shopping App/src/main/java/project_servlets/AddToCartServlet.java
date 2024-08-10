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

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html"); 
		
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		String product_id = req.getParameter("id");
		int quantity = 1;
		int product_price = 0;
		int product_total = 0;
		int cart_total = 0;
		int temp = 0;
		
		try {
			
			Connection con = ConnectionProvider.getConnection();
			Statement st = con.createStatement();
			
			// getting product price
			ResultSet rs = st.executeQuery("SELECT * FROM products WHERE id = '" + product_id + "'");
			while(rs.next()) {
				product_price = rs.getInt(4);
				product_total = product_price;
			}
			
			// rs1.next() executes only if product already exists in user cart!
			ResultSet rs1 = st.executeQuery("SELECT * FROM cart WHERE product_id = '"+product_id+"' AND email = '"+email+"' AND address is NULL");
			while(rs1.next()) {
				cart_total = rs1.getInt(5);
				cart_total = cart_total + product_total;
				quantity = rs1.getInt(3);
				quantity = quantity + 1;
				temp = 1;
			}
			if(temp == 1) {
				st.executeUpdate("UPDATE cart SET total = '"+cart_total+"', quantity = '"+quantity+"' WHERE "
						+ "product_id = '"+product_id+"' AND email = '"+email+"' AND address IS NULL");
				
				res.sendRedirect("home.jsp?msg=exist");
			}
			
			// when adding product to cart 1st time
			if(temp == 0) {
				PreparedStatement ps = con.prepareStatement("INSERT INTO cart (email, product_id, quantity, price, total)"
						+ " VALUES (?,?,?,?,?)");
				
				ps.setString(1, email);
				ps.setString(2, product_id);
				ps.setInt(3, quantity);
				ps.setInt(4, product_price);
				ps.setInt(5, product_total);
				
				ps.executeUpdate();
				
				res.sendRedirect("home.jsp?msg=added");
			}
			
		}catch(Exception e) {
			System.out.println("Exception in AddToCartservlet : " + e);
			e.printStackTrace();
			res.sendRedirect("home.jsp?msg=invalid");
		}
		
	}

}
