package in.ashokit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import in.ashokit.dao.BookDAO;
import in.ashokit.dto.BookDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// Capture for data

			String name = req.getParameter("bookName");
			int bookId = Integer.parseInt(req.getParameter("bookId"));
			double bookPrice = Double.parseDouble(req.getParameter("bookPrice"));

			BookDTO dto = new BookDTO();
			dto.setBookId(bookId);
			dto.setBookName(name);
			dto.setBookPrice(bookPrice);
			
			// call dao method by giving form data

			BookDAO dao = new BookDAO();
			boolean status = dao.saveBook(dto);

			// send response to client

			String response = null;
			if (status) {
				response = "Record Inserted";
			} else {
				response = "Insertion Failed";
			}

			PrintWriter pw = resp.getWriter();
			pw.append(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
