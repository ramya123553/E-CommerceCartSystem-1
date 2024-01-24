package com.example.ecommerce.dao;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

 @Autowired
 private SessionFactory sessionFactory;

 public void setSessionFactory(SessionFactory sf) {
     this.sessionFactory = sf;
 }

 @Override
 @Transactional
 public Cart addToCart(User user, int productId, int quantity) {
     Session session = this.sessionFactory.getCurrentSession();

     // Fetch the user by ID
     User existingUser = session.get(User.class, user.getId());

     if (existingUser != null) {
         // Access the associated cart
         Cart userCart = existingUser.getCart();

         // Your logic to add the product to the cart
         // (Make sure to handle null checks and product addition logic)

         session.saveOrUpdate(existingUser);

         return userCart;
     }

     return null;
 }


 @Override
 @Transactional
 public Cart updateCart(int userId, int productId, int quantity) {
     // Implement your logic to update the product quantity in the user's cart
     // You may need to fetch the cart, find the product, and update its quantity
     // Adjust the logic based on your actual data model
     return null;
 }

 @Override
 @Transactional
 public boolean removeFromCart(String username, Long productId) {
     // Implement your logic to remove the product from the user's cart
     // Adjust the logic based on your actual data model
     return false;
 }
 @Override
 @Transactional
 public Cart viewCart(int userId) {
     Session session = this.sessionFactory.getCurrentSession();

     // Fetch the user by ID
     User user = session.get(User.class, userId);

     if (user != null) {
         return user.getCart();
     }

     return null;
 }



 @Override
 @Transactional
 public double calculateTotalBill(int userId) {
     // Implement your logic to calculate the total bill for the user's cart
     // Adjust the logic based on your actual data model
     return 0.0;
 }

 @Override
 @Transactional
 public Cart addCart(Cart cart) {
     // Implement your logic to add a new cart
     // Adjust the logic based on your actual data model
     this.sessionFactory.getCurrentSession().save(cart);
     return cart;
 }

 @Override
 @Transactional
 public List<Cart> getCarts() {
     // Implement your logic to fetch and return all carts
     // Adjust the logic based on your actual data model
     return this.sessionFactory.getCurrentSession().createQuery("from CART", Cart.class).list();
 }

 @Override
 @Transactional
 public void updateCart(Cart cart) {
     // Implement your logic to update the cart
     // Adjust the logic based on your actual data model
     this.sessionFactory.getCurrentSession().update(cart);
 }

 @Override
 @Transactional
 public void deleteCart(Cart cart) {
     // Implement your logic to delete the cart
     // Adjust the logic based on your actual data model
     this.sessionFactory.getCurrentSession().delete(cart);
 }

 @Override
 @Transactional
 public Cart getCartByUsername(String username) {
     Session session = this.sessionFactory.getCurrentSession();

     // Assuming you have a method to retrieve a user by 
     User user = (User) session.createQuery("from User where username = :username")
             .setParameter("username", username)
             .uniqueResult();

     // Assuming the User entity has a direct association to the Cart entity
     // Adjust this based on your actual data model
     if (user != null) {
         return user.getCart();
     }

     return null;
 }
}