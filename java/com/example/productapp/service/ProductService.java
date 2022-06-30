package com.example.productapp.service;

import com.example.productapp.model.Product;
import com.example.productapp.repository.ProductRepository;
import com.example.productapp.repository.ProductRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProducts(){
        logger.info("Get all products request received");
        return this.productRepository.getAllProducts();
    }
    public Product findProductByName(String product_name){
        logger.info("Find product by name request sent");
        return this.productRepository.findProductByName(product_name);
    }
    public void deleteProductByName(String product_name){
        logger.info("Delete product by name sent");
        this.productRepository.deleteProductByName(product_name);
    }

    public void deleteProductById(String product_id){
        logger.info("Delete product by Id request sent");
        this.productRepository.deleteProductById(product_id);
    }
    public void addProduct(Product product){
        logger.info("Add product request sent");
        this.productRepository.addProduct(product);}

    public void updateProduct(Product product){
        logger.info("Update product request sent");
        this.productRepository.updateProduct(product);
    }

}
