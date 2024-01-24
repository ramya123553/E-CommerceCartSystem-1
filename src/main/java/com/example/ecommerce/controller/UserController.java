// UserController.java

package com.example.ecommerce.controller;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.User;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @GetMapping("/")
    public String userlogin(Model model) {
        return "userLogin";
    }
    @GetMapping("/index")
    public String showIndex(Model model) {
    	String welcomeMessage = "Welcome to our E-commerce Store!";
        model.addAttribute("welcomeMessage", welcomeMessage);

        return "index";
    }
    @GetMapping("/products")
    public String showProductList(Model model) {
    	List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        return "product";
    }
    @GetMapping("/cart")
    public String showShoppingCart(Model model) {
        String username = "user123"; 
        Cart userCart = cartService.getCartByUsername(username);
       
        if (userCart != null) {
            model.addAttribute("cartItems", userCart.getProducts());
            model.addAttribute("totalBill", userCart.calculateTotalBill());
        } else {
            model.addAttribute("msg", "Cart is empty");
        }

        return "shoppingCart";
    }


    @GetMapping("/total-bill")
    public String showTotalBill(Model model) {
       
        String username = "user123"; 
        Cart userCart = cartService.getCartByUsername(username);
        
       
        if (userCart != null) {
            model.addAttribute("totalBill", userCart.calculateTotalBill());
        } else {
            model.addAttribute("msg", "Cart is empty");
        }

        return "totalBill";
    }

    @RequestMapping(value = "userloginvalidate", method = RequestMethod.POST)
    public ModelAndView userlogin(@RequestParam("username") String username,
                                  @RequestParam("password") String pass, Model model, HttpServletResponse res) {

        User u = this.userService.checkLogin(username, pass);

        if (username.equals(u.getUsername())) {
            res.addCookie(new Cookie("username", u.getUsername()));
            ModelAndView mView = new ModelAndView("index");
            mView.addObject("user", u);
            List<Product> products = this.productService.getProducts();

            if (products.isEmpty()) {
                mView.addObject("msg", "No products are available");
            } else {
                mView.addObject("products", products);
            }
            return mView;

        } else {
            ModelAndView mView = new ModelAndView("userLogin");
            mView.addObject("msg", "Please enter correct email and password");
            return mView;
        }
    }

    @GetMapping("/user/cart")
    public ModelAndView getCartDetail(@CookieValue(value = "username", defaultValue = "") String username) {
        Cart userCart = cartService.getCartByUsername(username);

        ModelAndView mView = new ModelAndView("userCart");

        if (userCart != null) {
            mView.addObject("cartItems", userCart.getProducts());
            mView.addObject("totalPrice", userCart.calculateTotalBill());
        } else {
            mView.addObject("msg", "Cart is empty");
        }

        return mView;
    }

    @PostMapping("/newuserregister")
    public ModelAndView registerNewUser(@RequestParam("username") String username,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("address") String address,
                                        Model model) {
        User registeredUser = userService.registerNewUser(username, email, password, address);

        if (registeredUser != null) {
            ModelAndView mView = new ModelAndView("userLogin");
            mView.addObject("msg", "Registration successful. Please login.");
            return mView;
        } else {
            ModelAndView mView = new ModelAndView("register");
            mView.addObject("msg", "Registration failed. Please try again.");
            return mView;
        }
    }

    @PostMapping("/someEndpoint")
    public String someEndpoint() {
        return "redirect:/somePage";
    }
}
