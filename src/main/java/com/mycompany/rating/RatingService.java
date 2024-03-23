package com.mycompany.rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository repo;

    public List<Rating> listAll() {
        return (List<Rating>) repo.findAll();
    }

    public void save(Rating rating) {
        repo.save(rating);
    }

    public Rating get(Integer id) throws RatingNotFoundException {
        Optional<Rating> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new RatingNotFoundException("Could not find any products with ID " + id);
    }

    public void delete(Integer id) throws RatingNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new RatingNotFoundException("Could not find any products with ID " + id);
        }
        repo.deleteById(id);
    }
}


