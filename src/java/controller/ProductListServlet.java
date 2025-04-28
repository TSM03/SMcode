/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.util.List;

/**
 *
 * @author tsm11
 */
@WebServlet(name = "ProductListServlet", urlPatterns = {"/ProductListServlet"})
public class ProductListServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        ProductDAO dao = new ProductDAO();
        List<Product> productList = dao.getAllProducts();
        
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("<%= request.getContextPath() %>/AdminPanel.jsp");
        dispatcher.forward(request, response);
    }
}