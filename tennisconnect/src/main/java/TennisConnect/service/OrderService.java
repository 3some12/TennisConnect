package TennisConnect.service;

import TennisConnect.model.Order;
import TennisConnect.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserUserId(userId);
    }

    public Order createOrder(Order order) {
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus("pending");
        return orderRepository.save(order);
    }
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}