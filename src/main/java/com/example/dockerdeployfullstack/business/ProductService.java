package com.example.dockerdeployfullstack.business;


import com.example.dockerdeployfullstack.persistence.Entity.Product;
import com.example.dockerdeployfullstack.exceptions.ProductNotFoundException;
import com.example.dockerdeployfullstack.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {

        Product existingProduct = productRepository.findByName(product.getName());

        if (existingProduct != null) {
            throw new ProductNotFoundException("Product already exists with name: " + product.getName());
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setDescription(productDetails.getDescription());
        product.setImage(productDetails.getImage());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}