package com.mycompany.category;

import com.mycompany.entities.Category;
import com.mycompany.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository repo;

    @Test
    public void testAddNew() throws IOException{

        Category category = new Category(null, "Entradas");

        Category savedCategory = repo.save(category);

        Category retrievedCategory = repo.findById(savedCategory.getId()).orElse(null);


        // Verificar se o produto foi salvo com sucesso
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Category> categories = repo.findAll();

        for(Category category : categories){
            System.out.println(category);
        }
    }

    @Test
    public void testUpdate(){
        Integer categoryId = 1;
        Optional<Category> optionalCategory = repo.findById(categoryId);
        Category category = optionalCategory.get();
        category.setName("Bebidas");
        repo.save(category);

        Category updatedCategory = repo.findById(categoryId).get();
        Assertions.assertThat(updatedCategory.getName()).isEqualTo("Bebidas");


    }

    @Test
    public void testDelete(){
        Integer categoryId = 2;
        repo.deleteById(categoryId);
        Optional<Category> optionalCategory = repo.findById(categoryId);
        Assertions.assertThat(optionalCategory).isNotPresent();
    }
}

