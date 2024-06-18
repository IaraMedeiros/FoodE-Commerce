package com.mycompany.services;
import com.mycompany.exceptions.OrderNotFoundException;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repo;

    public List<Order> listAll() {
        return (List<Order>) repo.findAll();
    }

    public void save(Order order) {
        repo.save(order);
    }

    public Order get(Integer id) throws OrderNotFoundException {
        Optional<Order> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new OrderNotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws OrderNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new OrderNotFoundException("Could not find any categories with ID " + id);
        }
        repo.deleteById(id);
    }

    public List<Order> activeOrders(){
        return repo.AllActiveOrders();
    }
    public List<Order> pastOrders(){
        return repo.AllPastOrders();
    }
}


