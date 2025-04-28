import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("PRODUCTNAME");
        String description = request.getParameter("DESCRIPTION"); 
        String category = request.getParameter("CATEGORY");
        double price = Double.parseDouble(request.getParameter("PRICE"));
        int stockQuantity = Integer.parseInt(request.getParameter("STOCK_QUANTITY"));
        String imageUrl = request.getParameter("IMAGE_URL");

        // Updated SQL query with CURRENT_TIMESTAMP for UPDATED_AT
        String sql = "UPDATE \"PRODUCTS\" SET \"PRODUCTNAME\"=?, \"DESCRIPTION\"=?, \"CATEGORY\"=?, \"PRICE\"=?, \"STOCK_QUANTITY\"=?, \"IMAGE_URL\"=?, \"UPDATED_AT\"=CURRENT_TIMESTAMP WHERE \"PRODUCT_ID\"=?";

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Client", "nbuser", "nbuser");

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setString(2, description);
            ps.setString(3, category);
            ps.setDouble(4, price);
            ps.setInt(5, stockQuantity);
            ps.setString(6, imageUrl);
            ps.setInt(7, id);  // Only 7 values to match the 7 placeholders

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("/JSP/AdminPanel.jsp"); // or redirect back to staff list
            } else {
                response.getWriter().println("No record updated.");
            }

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
