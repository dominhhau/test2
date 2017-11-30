package jwd1718;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.GuestBookEntry;


@WebServlet("/GuestBook")
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GuestBook() {
        super();
        
    }
    
    public void init(ServletConfig config) throws ServletException
    {
    	super.init(config);
    	
    	try
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    		
    	}
    	catch(ClassNotFoundException e)
    	{
    		throw new ServletException(e);
    	}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
		Connection c = null;
		try
		{
			String url="jdbc:mysql://localhost/web";
			String username="root";
			String password="minhhau19081995";
			
			c = (Connection) DriverManager.getConnection(url, username, password);
			Statement stmt = (Statement) c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from guestbook");
			
			while(rs.next())
			{
				GuestBookEntry entry = new GuestBookEntry
						(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("message") );
				
				entries.add(entry);
			}
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
		
		request.setAttribute("entries", entries);
		request.getRequestDispatcher("/WEB-INF/GuestBook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
