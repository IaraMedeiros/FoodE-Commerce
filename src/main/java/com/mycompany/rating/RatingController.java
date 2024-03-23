package com.mycompany.rating;

import com.mycompany.product.Product;
import com.mycompany.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RatingController {

    @Autowired private RatingService service;

    @GetMapping(value="/t/ratings")
    public ResponseEntity<List<Rating>> findAll(){
        List<Rating> list = service.listAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "t/ratings/{id}")
    public ResponseEntity<Rating> findById(@PathVariable Integer id) throws RatingNotFoundException {
        Rating rating = service.get(id);
        return ResponseEntity.ok().body(rating);
    }


}
