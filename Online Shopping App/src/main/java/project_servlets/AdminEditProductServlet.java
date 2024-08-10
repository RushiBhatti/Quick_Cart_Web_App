package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project_utils.ConnectionProvider;

@WebServlet("/adminEditProductServlet")
public class AdminEditProductServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String category = req.getParameter("category");
		String price = req.getParameter("price");
		String active = req.getParameter("active");
		
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE products SET name = ?, category=?, price=?, active=? WHERE id=?");
			ps.setString(1, name);
			ps.setString(2, category);
			ps.setString(3, price);
			ps.setString(4, active);
			ps.setString(5, id);
			int count = ps.executeUpdate();
			if(count > 0) {
				// Successfully updated!
				if(active.equals("no")) {
					Statement st = con.createStatement();
					st.executeUpdate("DELETE FROM cart WHERE product_id = '"+ id +"' AND address IS NULL");
				}
				res.sendRedirect("admin/allProductEditProduct.jsp?msg=done");
			}else {
				// update failed!
				res.sendRedirect("admin/allProductEditProduct.jsp?msg=failed");
			}
		}
		catch(Exception e) {
			res.sendRedirect("admin/allProductEditProduct.jsp?msg=failed");
		}
	}

}
