package in.ashokit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// Capture from form
		String bookId = req.getParameter("bookId");
		String bookName = req.getParameter("bookName");
		String bookPrice = req.getParameter("bookPrice");
		
		System.out.println(bookId);
		System.out.println(bookName);
		System.out.println(bookPrice);
		
		PrintWriter pw = resp.getWriter();
		pw.write("Form Submited");
		
		// Insert into DB table
		
		try {
			Connection con = DriverManager.getConnection("", "", "");
			
			String sql = "insert into books values(?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bookId));
			pstmt.setString(1, bookName);
			pstmt.setDouble(1, Double.parseDouble(bookPrice));
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
