package com.manoj.ecomm.controller;

import com.manoj.ecomm.model.Product;
import com.manoj.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

     @Autowired
      ProductService productService;

     @GetMapping
    public  List<Product> getAllProduct(){

         return productService.getAllProduct();
     }

     @GetMapping("/{id}")
     public Product getProductById(@PathVariable long id){
         return productService.getProductById(id);

     }

     @PostMapping
     public Product addProduct(@RequestBody Product product){

        return productService.addProduct(product);
     }

     @DeleteMapping("/{id}")
     public void deleteProduct(@PathVariable Long id){

          productService.deleteProduct(id);
     }
}
