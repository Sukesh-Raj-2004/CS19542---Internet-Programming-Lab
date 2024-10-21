package User;
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
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username= request.getParameter("username");
		String password=request.getParameter("password");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/voting?useSSL=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"root","root");
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	out. println( "<html><head></head><body onload=\"alert('login successful')\"></body></html>" );
            	Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(60 * 60); // Set cookie expiry time to 1 hour
                response.addCookie(userCookie);
                response.sendRedirect("VotePage.html");
            } 
            else {
            	response.sendRedirect("Login.html?error=invalid");
            }
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
		out.close();
	}

}
