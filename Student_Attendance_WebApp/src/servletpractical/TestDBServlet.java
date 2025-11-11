package servletpractical;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import dbutil.DBConnection;

public class TestDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            out.println("<h3>Database connection successful!</h3>");
        } catch (SQLException e) {
            out.println("<h3>Database connection failed: " + e.getMessage() + "</h3>");
        }
    }
}