package com.mycompany.product;
import com.mycompany.category.Category;
import com.mycompany.category.CategoryNotFoundException;
import com.mycompany.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any products with ID " + id);
    }

    public void delete(Integer id) throws ProductNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ProductNotFoundException("Could not find any products with ID " + id);
        }
        repo.deleteById(id);
    }
}


