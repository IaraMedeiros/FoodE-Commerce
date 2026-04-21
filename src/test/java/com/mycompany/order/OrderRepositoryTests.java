package com.mycompany.order;

import com.mycompany.entities.Order;
import com.mycompany.entities.OrderItem;
import com.mycompany.entities.Product;
import com.mycompany.entities.User;
import com.mycompany.entities.enums.OrderStatus;
import com.mycompany.repositories.OrderItemRepository;
import com.mycompany.repositories.OrderRepository;
import com.mycompany.repositories.ProductRepository;
import com.mycompany.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class OrderRepositoryTests {

    @Autowired private OrderRepository orderRepo;
    @Autowired
    private OrderItemRepository orderItemRepo;
    @Autowired private ProductRepository productRepository;
    @Autowired private UserRepository userRepository;

    @Test
    void testAddNew() {

        User user = userRepository.save(
                new User(null, "John", "999999999", "John Doe", "12345678901")
        );

        Product p1 = productRepository.save(
                new Product(null, "Product 1", "Desc", 10.0, null, null)
        );

        Product p2 = productRepository.save(
                new Product(null, "Product 2", "Desc", 20.0, null, null)
        );

        Order order = new Order(null);
        order.setMoment(Instant.now());
        order.setOrderStatus(OrderStatus.PAID);
        order.setCostumer(user);

        order = orderRepo.save(order);

        OrderItem item1 = new OrderItem(order, p1, 2, p1.getPrice() * 2);
        OrderItem item2 = new OrderItem(order, p2, 1, p2.getPrice());

        orderItemRepo.save(item1);
        orderItemRepo.save(item2);

        order.getItems().add(item1);
        order.getItems().add(item2);

        order.setValor(order.getSubTotal());

        Order saved = orderRepo.save(order);

        assertThat(saved).isNotNull();
    }

    @Test
    void testDelete() {

        Order order = orderRepo.save(new Order(null));

        orderRepo.deleteById(order.getId());

        assertThat(orderRepo.findById(order.getId())).isNotPresent();
    }
}