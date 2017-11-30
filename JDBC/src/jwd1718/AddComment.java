package jwd1718;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddComment() {
        super();
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/GuestBook.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		
		Connection c = null;
		try
		{
			String url="jdbc:mysql://localhost/web";
			String username = "root";
			String password = "minhhau19081995";
			
			String sql = "insert into guestbook(name, message) values(?,?)";
			
			c = (Connection) DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, message);
			pstmt.executeUpdate();
		
		}
		catch(SQLException e)
		{
			throw new ServletException(e);
		}
		finally
		{
			try
			{
				if(c != null)c.close();
			}
			catch(SQLException e)
			{
				throw new ServletException(e);
			}
		}
		
		
		request.getRequestDispatcher("GuestBook").forward(request, response);
		
	}

}
