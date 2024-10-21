package CheckElectionStatusServlet;

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
 * Servlet implementation class CheckElectionStatusServlet
 */
@WebServlet("/CheckElectionStatusServlet")
public class CheckElectionStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckElectionStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isActive = false;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/voting?useSSL=false&serverTimezone=UTC";
	        Connection conn = DriverManager.getConnection(url, "root", "root");

	        String sql = "SELECT status FROM election_status WHERE id = 1"; // Assuming there's only one row
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	        	String status = rs.getString("status"); // Get the 'status' column from the result set
	            isActive = "active".equals(status);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Send the result as plain text
	    response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();
	    out.print(isActive ? "active" : "inactive");
	    out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		    }

	}

