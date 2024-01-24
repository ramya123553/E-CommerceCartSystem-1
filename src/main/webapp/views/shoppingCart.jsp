<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .update-form {
            display: inline-block;
            margin-right: 10px;
        }

        .remove-form {
            display: inline-block;
            color: red;
            cursor: pointer;
        }
    </style>
</head>
<body>

<h2>Your Shopping Cart</h2>

<c:if test="${not empty cartItems}">
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through cartItems using JSTL -->
            <c:forEach var="cartItem" items="${cartItems}">
                <tr>
                    <td>${cartItem.product.name}</td>
                    <td>${cartItem.product.price}</td>
                    <td>
                        <form class="update-form" action="/cart/update" method="post">
                            <input type="hidden" name="productId" value="${cartItem.product.id}">
                            <input type="number" name="quantity" value="${cartItem.quantity}" min="1">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form class="remove-form" action="/cart/remove" method="post">
                            <input type="hidden" name="productId" value="${cartItem.product.id}">
                            <input type="submit" value="Remove">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
