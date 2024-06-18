package com.mycompany.repositories;

import com.mycompany.entities.Shipping;
import com.mycompany.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShippingRepository extends CrudRepository<Shipping, Integer> {
    public Long countById(Integer id);

    @Query("SELECT s FROM Shipping s  WHERE s.order.orderStatus = 4 order by s.id")
    public List<Shipping> finalizedShippings();
}
