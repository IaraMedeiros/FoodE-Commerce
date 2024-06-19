package com.mycompany.repositories;

import com.mycompany.entities.Shipping;
import com.mycompany.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShippingRepository extends CrudRepository<Shipping, Integer> {
    public Long countById(Integer id);
}
