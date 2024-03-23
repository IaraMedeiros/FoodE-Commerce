package com.mycompany.category;

import com.mycompany.user.User;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    public Long countById(Integer id);
}