package com.example.ecommerce.dao;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product; 

import com.example.ecommerce.models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public abstract class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Cart addToCart(User user, int productId, int quantity) throws CloneNotSupportedException {
        Session session = this.sessionFactory.getCurrentSession();

        // Fetch the user by ID
        User existingUser = session.get(User.class, user.getId());

        if (existingUser != null) {
            // Fetch the product by ID (assuming you have a method to retrieve a product)
            Product product = session.get(Product.class, productId);

            if (product != null && product.isAvailableStatus()) {
                // Clone the product using the Prototype Pattern
                Product clonedProduct = product.clone();

                // Set quantity
                clonedProduct.setQuantity(quantity);

                // Add the cloned product to the user's cart
                existingUser.getCart().addProduct(clonedProduct);

                session.saveOrUpdate(existingUser);

                return existingUser.getCart();
            }
        }

        return null;
    }

    @Override
    @Transactional
    public Cart updateCart(int userId, int productId, int quantity) {
        Session session = this.sessionFactory.getCurrentSession();

        // Fetch the user by ID
        User user = session.get(User.class, userId);

        if (user != null) {
            // Update the quantity of the product in the cart
            user.getCart().updateProductQuantity(productId, quantity);

            session.saveOrUpdate(user);

            return user.getCart();
        }

        return null;
    }

    @Override
    @Transactional
    public boolean removeFromCart(String username, Long productId) {
        Session session = this.sessionFactory.getCurrentSession();

        // Fetch the user by username
        User user = (User) session.createQuery("from User where username = :username")
                .setParameter("username", username)
                .uniqueResult();

        if (user != null) {
            // Remove the product from the cart
            return user.getCart().removeProduct(productId);
        }

        return false;
    }

   

    @Override
    @Transactional
    public double calculateTotalBill(int userId) {
        Session session = this.sessionFactory.getCurrentSession();

        // Fetch the user by ID
        User user = session.get(User.class, userId);

        if (user != null) {
            // Retrieve the cart associated with the user
            Cart cart = user.getCart();

            if (cart != null) {
                List<Product> products = cart.getProducts();

                // Calculate the total bill by summing up the prices of products in the cart
                double totalBill = products.stream()
                        .mapToDouble(product ->
                                product.getPrice() * (1.0 - product.getDiscountValue() / 100.0) * product.getQuantity())
                        .sum();

                return totalBill;
            }
        }

        return 0.0; // Return 0 if user or cart is not found
    }

    @Override
    @Transactional
    public Cart addCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();

        // Check if the cart is already associated with a user
        if (cart.getCustomer() == null) {
            throw new IllegalArgumentException("Cart must be associated with a user.");
        }

        // Check if the user exists in the database
        User existingUser = session.get(User.class, cart.getCustomer().getId());
        if (existingUser == null) {
            throw new IllegalArgumentException("User does not exist. Cannot associate cart.");
        }

        // Set the user's cart
        existingUser.setCart(cart);

        // Save or update the user to persist the association
        session.saveOrUpdate(existingUser);

        return cart;
    }


    @Override
    @Transactional
    public List<Cart> getCarts() {
        Session session = this.sessionFactory.getCurrentSession();

        // Use HQL (Hibernate Query Language) to fetch all carts
        List<Cart> carts = session.createQuery("FROM CART", Cart.class).list();

        return carts;
    }

    @Override
    @Transactional
    public void updateCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();

        // Fetch the cart from the database using its ID
        Cart existingCart = session.get(Cart.class, cart.getId());

        if (existingCart != null) {
            // Update relevant information of the existing cart
            // For example, you might want to update the products in the cart

            // Here, assuming that the Cart entity has a method to update products
            existingCart.setProducts(cart.getProducts());

            // Save or update the cart in the database
            session.saveOrUpdate(existingCart);
        }
    }

    @Override
    @Transactional
    public void deleteCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();

        // Fetch the cart from the database using its ID
        Cart existingCart = session.get(Cart.class, cart.getId());

        if (existingCart != null) {
            // Delete the cart from the database
            session.delete(existingCart);
        }
    }


    @Override
    @Transactional
    public Cart getCartByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();

        // Assuming you have a method to retrieve a user by username
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



    
     
    
    
