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
        
         Cart userCart = existingUser.getCart();

         session.saveOrUpdate(existingUser);

         return userCart;
     }

     return null;
 }


 @Override
 @Transactional
 public Cart updateCart(int userId, int productId, int quantity) {
     
     return null;
 }

 @Override
 @Transactional
 public boolean removeFromCart(String username, Long productId) {
    
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
     
     return 0.0;
 }

 @Override
 @Transactional
 public Cart addCart(Cart cart) {
     
     this.sessionFactory.getCurrentSession().save(cart);
     return cart;
 }

 @Override
 @Transactional
 public List<Cart> getCarts() {
     
     return this.sessionFactory.getCurrentSession().createQuery("from CART", Cart.class).list();
 }

 @Override
 @Transactional
 public void updateCart(Cart cart) {
    
     this.sessionFactory.getCurrentSession().update(cart);
 }

 @Override
 @Transactional
 public void deleteCart(Cart cart) {
    
     this.sessionFactory.getCurrentSession().delete(cart);
 }

 @Override
 @Transactional
 public Cart getCartByUsername(String username) {
     Session session = this.sessionFactory.getCurrentSession();

    
     User user = (User) session.createQuery("from User where username = :username")
             .setParameter("username", username)
             .uniqueResult();

   
     if (user != null) {
         return user.getCart();
     }

     return null;
 }
}
