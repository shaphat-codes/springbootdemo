package com.boot.demo.service;

import com.boot.demo.entity.Product;
import com.boot.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(
            ProductRepository productRepository
    )
    {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }


    public ResponseEntity<List<Product>> fetchAllProducts()
    {
        return ResponseEntity.ok(productRepository.findAll());
    }


    // Get a product by ID
    public ResponseEntity<Optional<Product>>
    fetchProductById(Long id)
    {
        Optional<Product> product
                = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    //  Update a single product
    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct)
    {
        if (id == null) {
            throw new IllegalArgumentException(
                    "ID cannot be null");
        }
        Product Existingproduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        Existingproduct.setName(updatedProduct.getName());
        Existingproduct.setPrice(updatedProduct.getPrice());
        Existingproduct.setQuantity(
                updatedProduct.getQuantity());
        Product savedEntity
                = productRepository.save(Existingproduct);
        return ResponseEntity.ok(savedEntity);
    }


    public ResponseEntity<String> deleteProduct(Long id)
    {
        productRepository.deleteById(id);
        return ResponseEntity.ok(
                "Product Deleted Successfully");
    }



}
