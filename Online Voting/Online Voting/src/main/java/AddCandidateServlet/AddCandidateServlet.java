package AddCandidateServlet;

import java.lang.*;
import java.sql.*;
import java.awt.Window;
import java.io.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCandidateServlet
 */
@WebServlet("/AddCandidateServlet")
public class AddCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String candidateName = request.getParameter("candidateName");
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/voting?useSSL=false&serverTimezone=UTC";
	        Connection conn = DriverManager.getConnection(url, "root", "root");
	        
	        String sql = "INSERT INTO candidates (name) VALUES (?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, candidateName);
	        
	        stmt.executeUpdate();
	        
	        response.sendRedirect("adminPage.html"); // Redirect back to the admin page after successful addition
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}


