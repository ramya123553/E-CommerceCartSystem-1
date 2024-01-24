<!-- userCart.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Cart</title>
</head>
<body>
    <h2>User Cart</h2>
    <c:if test="${not empty cartItems}">
        <table border="1">
            <tr>
                <th>Product Name</th>
                <th>Quantity</th>
            </tr>
            <c:forEach var="cartItem" items="${cartItems}">
                <tr>
                    <td>${cartItem.product.name}</td>
                    <td>${cartItem.quantity}</td>
                </tr>
            </c:forEach>
        </table>
        <p>Total Price: ${totalPrice}</p>
    </c:if>
    <c:if test="${empty cartItems}">
        <p>${msg}</p>
    </c:if>
    <br>
    <a href="user/products">Back to Product List</a>
</body>
</html>
