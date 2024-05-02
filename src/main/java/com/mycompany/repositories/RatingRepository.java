package com.mycompany.repositories;

import com.mycompany.entities.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    public Long countById(Integer id);
}