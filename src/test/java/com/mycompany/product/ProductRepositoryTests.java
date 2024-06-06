package com.mycompany.product;

import com.mycompany.entities.Category;
import com.mycompany.repositories.CategoryRepository;
import com.mycompany.entities.Product;
import com.mycompany.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
       public void testAddNew() throws IOException {

           // Carregar a imagem do arquivo
            File file = new File("C:\\Users\\iara3\\Downloads\\imagens projeto\\xburger.jpeg");
           byte[] imgBytes = Files.readAllBytes(file.toPath());

           Optional<Category> optionalCategory = categoryRepository.findById(1);
           Category category = optionalCategory.get();

           Product product = new Product(null,"X burger","Pão de brioche, um hamburger de 90g e queijo prato", 16.00, imgBytes,
                   category);

           Set<Product> products = category.getProducts();
           products.add(product);

           // Salvar o produto no banco de dados
           Product savedProduct = repo.save(product);

           Product retrievedProduct = repo.findById(savedProduct.getId()).orElse(null);


           // Verificar se o produto foi salvo com sucesso
           assertThat(savedProduct).isNotNull();
           assertThat(savedProduct.getId()).isNotNull();
           assertThat(savedProduct.getId()).isGreaterThan(0);
           assertThat(retrievedProduct.getImg().length).isEqualTo(imgBytes.length);

       }


    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();

        for(Product product : products){
            System.out.println(product);
        }
    }

    @Test
    public void testUpdate() throws IOException {
        Integer productId = 5;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();

        File file = new File("C:\\Users\\iara3\\Downloads\\imagens projeto\\xburger.jpeg");
        byte[] imgBytes = Files.readAllBytes(file.toPath());

        product.setImg(imgBytes);
        repo.save(product);

        Product updatedProduct = repo.findById(productId).get();

    }

    @Test
    public void testUpdate2() throws IOException {
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();

        Optional<Category> optionalCategory = categoryRepository.findById(2);
        Category category = optionalCategory.get();

        product.setCategory(category);
        repo.save(product);

        Product updatedProduct = repo.findById(productId).get();

    }

    @Test
    public void testDelete(){
        Integer productId = 4;
        repo.deleteById(productId);
        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}



