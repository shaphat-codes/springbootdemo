package com.boot.demo.controller;

import com.boot.demo.entity.Product;
import com.boot.demo.repository.ProductRepository;

import com.boot.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository)
    {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // Create a new product
    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product)
    {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }


//    // Get all products
//    @GetMapping("/products")
//    public List<Product> getAllProducts()
//    {
//        return productService.getAllProducts();
//    }


}
