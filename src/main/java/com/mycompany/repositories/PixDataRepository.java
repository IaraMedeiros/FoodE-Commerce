package com.mycompany.repositories;

import com.mycompany.entities.Order;
import com.mycompany.entities.PixData;
import org.springframework.data.repository.CrudRepository;

public interface PixDataRepository extends CrudRepository<PixData, Integer> {
    public Long countById(Long id);
}
