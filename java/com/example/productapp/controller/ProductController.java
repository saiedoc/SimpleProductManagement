package com.example.productapp.controller;

import com.example.productapp.model.Product;
import com.example.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String productPageTest(){
        return "webpages/html/Products";
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @RequestMapping(value = "/products/find", method = RequestMethod.GET)
    @ResponseBody
    public Product findProductByName(@RequestBody String product_name){
        return this.productService.findProductByName(product_name);
    }

    @RequestMapping(value = "/products/deleteByName" , method = RequestMethod.DELETE)
    @ResponseBody

    public void deleteProductByName(@RequestBody String product_name){
        this.productService.deleteProductByName(product_name);
    }

    @RequestMapping(value = "/products/deleteById" , method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProductById(@RequestBody String id){
        System.out.println(id);
        this.productService.deleteProductById(id);
    }

    @RequestMapping(value = "/products/add" , method = RequestMethod.POST)
    @ResponseBody
    public void addProduct(@RequestBody Product product){this.productService.addProduct(product);}

    @RequestMapping(value = "/products/update", method = RequestMethod.PUT)
    @ResponseBody
    public void updateProduct(@RequestBody Product product){this.productService.updateProduct(product);}


}
