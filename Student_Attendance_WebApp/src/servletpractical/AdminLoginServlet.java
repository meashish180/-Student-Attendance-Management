package servletpractical;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import dbutil.DBConnection;

public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM admin WHERE username=? AND password=?")) {

            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("role", "admin");
                session.setAttribute("username", user);
                response.sendRedirect("admin_dashboard.html");
            } else {
                out.println("<script>alert('Invalid username or password');window.location='admin_login.html';</script>");
            }

        } catch (SQLException e) {
            out.println("<h3>Database error: " + e.getMessage() + "</h3>");
        }
    }
}