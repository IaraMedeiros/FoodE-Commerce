package com.mycompany.OrderItem;
import com.mycompany.order.Order;
import com.mycompany.order.OrderNotFoundException;
import com.mycompany.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository repo;

    public List<OrderItem> listAll() {
        return (List<OrderItem>) repo.findAll();
    }

    public void save(OrderItem orderItem) {
        repo.save(orderItem);
    }

    public OrderItem get(Integer id) throws OrderItemNotFoundException {
        Optional<OrderItem> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new OrderItemNotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws OrderItemNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new OrderItemNotFoundException("Could not find any categories with ID " + id);
        }
        repo.deleteById(id);
    }
}


