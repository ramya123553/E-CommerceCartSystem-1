package com.example.ecommerce.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dao.ProductDao;
import com.example.ecommerce.models.Product;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductDao productDao; // Updated field name to follow Java naming conventions

    public List<Product> getProducts() {
        return this.productDao.getProducts();
    }

    public Product addProduct(Product product) {
        return this.productDao.addProduct(product);
    }

    public Product getProduct(int id) {
        return this.productDao.getProduct(id);
    }

    public Product updateProduct(int id, Product product) {
        product.setId(id);
        return this.productDao.updateProduct(product);
    }

    public boolean deleteProduct(int id) {
        return this.productDao.deleteProduct(id); // Updated method name
    }
}
