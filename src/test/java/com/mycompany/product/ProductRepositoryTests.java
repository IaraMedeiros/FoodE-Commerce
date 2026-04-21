package com.mycompany.product;

import com.mycompany.entities.Category;
import com.mycompany.entities.Product;
import com.mycompany.repositories.CategoryRepository;
import com.mycompany.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@EntityScan(basePackages = "com.mycompany.entities")
@EnableJpaRepositories(basePackages = "com.mycompany.repositories")
class ProductRepositoryTests {

    @Autowired
    private ProductRepository repo;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    void testAddNew() throws Exception {

        Category category = categoryRepository.save(
                new Category(null, "Food")
        );

        byte[] imgBytes = "fake-image".getBytes();

        Product product = new Product(
                null,
                "X Burger",
                "Burger desc",
                16.0,
                imgBytes,
                category
        );

        Product saved = repo.save(product);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getImg()).isNotNull();
    }


    @Test
    void testUpdate() {

        Product product = repo.save(
                new Product(null, "Old", "desc", 10.0, null, null)
        );

        product.setName("New");
        repo.save(product);

        assertThat(repo.findById(product.getId()).get().getName())
                .isEqualTo("New");
    }

    @Test
    void testDelete() {

        Product product = repo.save(
                new Product(null, "ToDelete", "desc", 10.0, null, null)
        );

        repo.deleteById(product.getId());

        assertThat(repo.findById(product.getId())).isNotPresent();
    }
}