package AddVoterServlet;
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
 * Servlet implementation class AddVoterServlet
 */
@WebServlet("/AddVoterServlet")
public class AddVoterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVoterServlet() {
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
		String voterName = request.getParameter("voterName");
        String voterPassword = request.getParameter("voterPassword");

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/voting?useSSL=false&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "root");
            
            // SQL query to insert a new voter (name and password)
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, voterName);        // Set voter name
            stmt.setString(2, voterPassword);    // Set voter password
            
            // Execute the update
            stmt.executeUpdate();
            
            response.sendRedirect("adminPage.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
