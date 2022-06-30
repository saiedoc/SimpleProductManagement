package com.example.productapp.repository;

import com.example.productapp.model.Product;
import com.example.productapp.service.ProductService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    @PersistenceContext
    EntityManager entityManager;
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p",Product.class);
        return query.getResultList();
    }

    @Override
    public Product findProductByName(String product_name) {
        try{
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE product_name = ?1",Product.class);
        return query.setParameter(1,product_name).getSingleResult();
        }catch(NoResultException ne){
            logger.warn("No Result for such query");
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteProductByName(String product_name) {
        Query query = entityManager.createQuery("DELETE FROM Product WHERE product_name = ?1");
        query.setParameter(1,product_name).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteProductById(String product_id) {
        Query query = entityManager.createQuery("DELETE FROM Product WHERE product_id = ?1");
        query.setParameter(1,product_id).executeUpdate();
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        Query query = entityManager.createQuery("UPDATE Product SET product_name = ?2 , product_price = ?3 WHERE product_id = ?1 ");
        query.setParameter(1,product.getProduct_id()).setParameter(2,product.getProduct_name()).setParameter(3,product.getProduct_price()).executeUpdate();
    }
}
