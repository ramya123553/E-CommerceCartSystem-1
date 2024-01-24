package com.example.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.models.Product;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<Product> getProducts() {
        return this.sessionFactory.getCurrentSession().createQuery("from PRODUCT").list();
    }

    @Transactional
    public Product addProduct(Product product) {
        this.sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Transactional
    public Product getProduct(int id) {
        return this.sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Transactional
    public Product updateProduct(Product product) {
        this.sessionFactory.getCurrentSession().update(product);
        return product;
    }

    @Transactional
    public boolean deleteProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Object persistenceInstance = session.load(Product.class, id);

        if (persistenceInstance != null) {
            session.delete(persistenceInstance);
            return true;
        }
        return false;
    }
}
