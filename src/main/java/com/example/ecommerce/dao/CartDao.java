package com.example.ecommerce.dao;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.User;

import java.util.List;

public interface CartDao {

	Cart addToCart(User user, int productId, int quantity);

    Cart updateCart(int userId, int productId, int quantity);

    boolean removeFromCart(String username, Long productId);

    Cart viewCart(int userId);

    double calculateTotalBill(int userId);

    Cart addCart(Cart cart);

    List<Cart> getCarts();

    void updateCart(Cart cart);

    void deleteCart(Cart cart);
    
    Cart getCartByUsername(String username);
}
