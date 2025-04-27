
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%
    Product p = (Product) request.getAttribute("product");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= p.getName() %> Details</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/ProductDetails.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
</head>
<body>
    <section id="header" class="header">   
            <a href="GuestHome.jsp"><h2 style="font-weight: bolder; font-size: 3rem; color: black;">GLOWY DAYS</h2></a>
            <div class="navbar">
                <a href="">Home</a>
                <a href="#" class="active">Product</a> 
                <a href="">About Us</a>               
                <a href="">Contact Us</a>                             
            </div>
            <div class="icons">
                <div class="search-wrapper">
                    <i class="fa-solid fa-magnifying-glass" id="search-icon"></i>
                    <input type="text" id="search-box" name="query" placeholder="Search by ID or Name" />
                </div>
                <a href="<%= request.getContextPath() %>/CartServlet" class="cart-icon fa-solid fa-cart-shopping">
                    <%
                        Integer cartSize = (Integer) session.getAttribute("cartSize");
                        if (cartSize != null && cartSize > 0) {
                    %>
                        <span class="cart-badge"><%= cartSize %></span>
                    <%
                        }
                    %>
                </a>    
                <a href="AddNewUser.jsp" class="fa-regular fa-user"></a>
            </div>
        </section>
    
    <div class="product-detail-container">
        <h1><%= p.getName() %></h1>
        <img src="<%= request.getContextPath() %>/ProductImages/<%= p.getImageUrl() %>" alt="<%= p.getName() %>" width="300">
        <h2>Price: RM<%= String.format("%.2f", p.getPrice()) %></h2>
        <p><%= p.getDescription() %></p>

        <form action="<%= request.getContextPath() %>/CartServlet" method="POST">
            <input type="hidden" name="PRODUCT_ID" value="<%= p.getId() %>" />
            <input type="hidden" name="PRODUCTNAME" value="<%= p.getName() %>" />
            
            <input type="hidden" name="PRICE" value="<%= p.getPrice() %>" />
            <button type="submit">Add to Cart</button>
        </form>

        <a href="<%= request.getContextPath() %>/ProductServlet">← Back to Product List</a>
    </div>
    
</body>
    <script src="../JavaScript/main.js"></script>
</html>
