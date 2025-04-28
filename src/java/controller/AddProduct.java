package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {

    // adjust these if your URL/user/pass differ
    private static final String JDBC_URL    = "jdbc:derby://localhost:1527/Client";
    private static final String JDBC_USER   = "nbuser";
    private static final String JDBC_PASS   = "nbuser";
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // pull values from form
        String name        = request.getParameter("productName");
        String description = request.getParameter("description");
        String category    = request.getParameter("category");
        String priceStr    = request.getParameter("price");
        String stockStr    = request.getParameter("stockQuantity");
        String imageUrl    = request.getParameter("imageUrl");

        // parse numeric
        double price = 0;
        int    stock = 0;
        try {
            price = Double.parseDouble(priceStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            // invalid input → back to form with error
            request.setAttribute("error", "Price and stock must be numbers.");
            request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            // Insert (timestamps defaulted by DB)
            String sql = 
              "INSERT INTO \"NBUSER\".\"PRODUCTS\" "
            + "(PRODUCTNAME, DESCRIPTION, CATEGORY, PRICE, STOCK_QUANTITY, IMAGE_URL) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, description);
            pst.setString(3, category);
            pst.setDouble(4, price);
            pst.setInt(5, stock);
            pst.setString(6, imageUrl);

            int inserted = pst.executeUpdate();
            if (inserted == 1) {
                // success → back to list
                response.sendRedirect( "/product/JSP/AdminPanel.jsp");
            } else {
                throw new SQLException("No row inserted");
            }
        } catch (Exception e) {
            throw new ServletException("Error creating product", e);
        } finally {
            try { if (pst  != null) pst.close();  } catch (Exception ignore){}
            try { if (conn != null) conn.close(); } catch (Exception ignore){}
        }
    }

    // you can also support GET to show the form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("AddProduct.jsp").forward(request, response);
    }
}
