package com.mycompany;

import com.mycompany.entities.User;
import com.mycompany.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    void testAddNew() {

        User user = repo.save(
                new User(null, "email@test.com", "999999", "John", "12345678901")
        );

        assertThat(user.getId()).isNotNull();
    }

    @Test
    void testListAll() {

        repo.save(new User(null, "a@test.com", "111", "A", "111"));
        repo.save(new User(null, "b@test.com", "222", "B", "222"));

        assertThat(repo.findAll()).isNotEmpty();
    }

    @Test
    void testUpdate() {

        User user = repo.save(
                new User(null, "old@test.com", "123", "Old", "123")
        );

        user.setName("New Name");
        repo.save(user);

        assertThat(repo.findById(user.getId()).get().getName())
                .isEqualTo("New Name");
    }

    @Test
    void testGet() {

        User user = repo.save(
                new User(null, "get@test.com", "123", "Get", "123")
        );

        assertThat(repo.findById(user.getId())).isPresent();
    }

    @Test
    void testDelete() {

        User user = repo.save(
                new User(null, "del@test.com", "123", "Del", "123")
        );

        repo.deleteById(user.getId());

        assertThat(repo.findById(user.getId())).isNotPresent();
    }
}