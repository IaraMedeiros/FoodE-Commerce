package com.mycompany.repositories;

import com.mycompany.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Long countById(Integer id);
}