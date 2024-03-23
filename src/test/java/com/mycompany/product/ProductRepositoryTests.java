package com.mycompany.product;

import com.mycompany.category.Category;
import com.mycompany.category.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private CategoryRepository categoryRepository;

   @Test
    public void testAddNew() throws IOException{

       Optional<Category> optionalCategory = categoryRepository.findById(1);
       Category category = optionalCategory.get();

        Product product = new Product(null,"Guaraná 600ml","Garrafa de guaraná contendo 600ml", 6.00,
                category);

       Set<Product> products = category.getProducts();
       products.add(product);


       Product savedProduct = repo.save(product);

        Product retrievedProduct = repo.findById(savedProduct.getId()).orElse(null);


        // Verificar se o produto foi salvo com sucesso
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();

        for(Product product : products){
            System.out.println(product);
        }
    }

    @Test
    public void testUpdate(){
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();
        product.setName("Coca-cola 350ml");
        repo.save(product);

        Product updatedProduct = repo.findById(productId).get();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo("Coca-cola 350ml");


    }

    @Test
    public void testDelete(){
        Integer productId = 2;
        repo.deleteById(productId);
        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}

