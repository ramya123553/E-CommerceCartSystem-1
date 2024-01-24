package com.example.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.models.User;

import java.util.List;

@Repository
public class UserDao {
	

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }

    @Transactional
    public org.apache.catalina.User saveUser(org.apache.catalina.User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
        System.out.println("User added" + ((User) user).getId());
        return user;
    }
    @Transactional
    public User saveUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
        System.out.println("User added" + user.getId());
        return user;
    }


    @Transactional
    public User getUser(String username, String password) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username", User.class);
        query.setParameter("username", username);

        try {
            User user = query.getSingleResult();
            System.out.println(user.getPassword());
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return null; // Return null if password does not match
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null; // Return null if no user found or other exception
        }
    }

    @Transactional
    public boolean userExists(String username) {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(*) from CUSTOMER where username = :username", Long.class);
        query.setParameter("username", username);
        return query.getSingleResult() > 0;
    }
}
