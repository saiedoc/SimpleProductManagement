package com.example.productapp.repository;

import com.example.productapp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository {

    List<Product> getAllProducts();
    Product findProductByName(String product_name);
    void deleteProductByName(String product_name);
    void deleteProductById(String product_id);
    void addProduct(Product product);
    void updateProduct(Product product);


}
