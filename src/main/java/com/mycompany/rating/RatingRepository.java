package com.mycompany.rating;

import com.mycompany.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    public Long countById(Integer id);
}