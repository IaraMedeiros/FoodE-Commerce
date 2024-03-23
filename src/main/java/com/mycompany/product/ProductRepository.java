package com.mycompany.product;

import com.mycompany.category.Category;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    public Long countById(Integer id);
}