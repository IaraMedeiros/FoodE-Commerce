package com.mycompany.order;

import com.mycompany.category.Category;
import com.mycompany.category.CategoryNotFoundException;
import com.mycompany.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    @Autowired private OrderService service;

    @GetMapping(value="t/categories")
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "t/categories/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) throws OrderNotFoundException {
        Order order = service.get(id);
        return ResponseEntity.ok().body(order);
    }

}
