package FetchCandidatesServlet;

import java.lang.*;
import java.sql.*;
import java.util.*;
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
 * Servlet implementation class FetchCandidatesServlet
 */
@WebServlet("/FetchCandidatesServlet")
public class FetchCandidatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCandidatesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        try {
	            // Load MySQL driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/voting?useSSL=false&serverTimezone=UTC";
	            Connection conn = DriverManager.getConnection(url, "root", "root");

	            // SQL query to fetch candidate names
	            String sql = "SELECT name FROM candidates";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();

	            // Start building HTML response
	            out.println("<ul>");  // Open the unordered list tag

	            // Iterate through the result set and display each candidate's name
	            while (rs.next()) {
	                String candidateName = rs.getString("name");

	                // Display the candidate name with a vote button
	                out.println("<li>");
	                out.println("<strong>" + candidateName + "</strong> ");
	                out.println("<button type='button' onclick='castVote(\"" + candidateName + "\")'>Vote</button>");
	                out.println("</li>");
	            }

	            out.println("</ul>"); // Close the unordered list tag
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            out.close(); // Close the writer
	        }
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
