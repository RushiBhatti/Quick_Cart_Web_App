package project_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project_utils.ConnectionProvider;

@WebServlet("/addNewProductServlet")
public class AdminAddNewProductServlet extends HttpServlet {
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
			PreparedStatement ps = con.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, category);
			ps.setString(4, price);
			ps.setString(5, active);
			
			int count = ps.executeUpdate();
			if(count > 0) {
				res.sendRedirect("admin/addNewProduct.jsp?msg=done");
			}else {
				res.sendRedirect("admin/addNewProduct.jsp?msg=failed");
			}
		}
		catch(Exception e) {
			res.sendRedirect("addNewProduct.jsp?msg=failed");
		}
	}

}
