package com.mycompany.category;
import com.mycompany.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public List<Category> listAll() {
        return (List<Category>) repo.findAll();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category get(Integer id) throws CategoryNotFoundException {
        Optional<Category> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CategoryNotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws CategoryNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new CategoryNotFoundException("Could not find any categories with ID " + id);
        }
        repo.deleteById(id);
    }
}


