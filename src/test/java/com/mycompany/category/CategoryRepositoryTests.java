package com.mycompany.category;

import com.mycompany.entities.Category;
import com.mycompany.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@EntityScan(basePackages = "com.mycompany.entities")
@EnableJpaRepositories(basePackages = "com.mycompany.repositories")
class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository repo;

    @Test
    void testAddNew() {

        Category category = new Category(null, "Entradas");

        Category saved = repo.save(category);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    void testUpdate() {

        Category category = repo.save(new Category(null, "Entradas"));

        category.setName("Bebidas");
        repo.save(category);

        Category updated = repo.findById(category.getId()).orElseThrow();

        assertThat(updated.getName()).isEqualTo("Bebidas");
    }

    @Test
    void testDelete() {

        Category category = repo.save(new Category(null, "Entradas"));

        repo.deleteById(category.getId());

        assertThat(repo.findById(category.getId())).isNotPresent();
    }
}