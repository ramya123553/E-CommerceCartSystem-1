// CartService.java

package com.example.ecommerce.service;

import com.example.ecommerce.dao.CartDao;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    public Cart addToCart(User user, Product product, int quantity) {
        return cartDao.addToCart(user, product.getId(), quantity);
    }

    public Cart updateCart(int userId, int productId, int quantity) {
        return cartDao.updateCart(userId, productId, quantity);
    }

    public boolean removeFromCart(String username, Long productId) {
        return cartDao.removeFromCart(username, productId);
    }

    public Cart viewCart(int userId) {
        return cartDao.viewCart(userId);
    }

    public double calculateTotalBill(int userId) {
        return cartDao.calculateTotalBill(userId);
    }

    public Cart addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    public List<Cart> getCarts() {
        return cartDao.getCarts();
    }

    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    public void deleteCart(Cart cart) {
        cartDao.deleteCart(cart);
    }

    public Cart getCartByUsername(String username) {
        
        return cartDao.getCartByUsername(username);
    }
}
