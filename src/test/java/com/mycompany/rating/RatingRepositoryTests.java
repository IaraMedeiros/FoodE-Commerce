package com.mycompany.rating;

import com.mycompany.entities.Rating;
import com.mycompany.entities.User;
import com.mycompany.repositories.RatingRepository;
import com.mycompany.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RatingRepositoryTests {
    @Autowired
    private RatingRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew() throws IOException{
        Optional<User> optUser = userRepository.findById(1);
        User user = optUser.get();

        Rating rating = new Rating(null, 4.0, "Comida saborosa, mas atrasou a entrega", Instant.now(), user);
        Set<Rating> ratingSet = user.getRatings();
        ratingSet.add(rating);
        user.setRatings(ratingSet);

        Rating savedRating = repo.save(rating);

        Rating retrievedRating = repo.findById(savedRating.getId()).orElse(null);


        // Verificar se o produto foi salvo com sucesso
        assertThat(savedRating).isNotNull();
        assertThat(savedRating.getId()).isNotNull();
        assertThat(savedRating.getEstrelas()).isLessThanOrEqualTo(5);
        assertThat(savedRating.getEstrelas()).isGreaterThan(-1);
        assertThat(savedRating.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Rating> ratings = repo.findAll();

        for(Rating rating : ratings){
            System.out.println(rating);
        }
    }

    @Test
    public void testUpdate(){
        Integer ratingId = 1;
        Optional<Rating> optionalRating = repo.findById(ratingId);
        Rating rating = optionalRating.get();
        rating.setEstrelas(3.5);
        repo.save(rating);

        Rating updatedRating = repo.findById(ratingId).get();
        Assertions.assertThat(updatedRating.getEstrelas()).isEqualTo(3.5);


    }

    @Test
    public void testDelete(){
        Integer ratingId = 2;
        repo.deleteById(ratingId);
        Optional<Rating> optionalRating = repo.findById(ratingId);
        Assertions.assertThat(optionalRating).isNotPresent();
    }
}

