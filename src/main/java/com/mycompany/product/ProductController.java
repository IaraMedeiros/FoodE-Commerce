package com.mycompany.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired private ProductService service;

    @GetMapping(value="/t/products")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "t/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) throws ProductNotFoundException {
        Product product = service.get(id);
        return ResponseEntity.ok().body(product);
    }


}
