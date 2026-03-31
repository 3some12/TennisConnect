package TennisConnect.controller;

import TennisConnect.model.Order;
import TennisConnect.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 取得所有訂單
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // 根據ID取得訂單
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 根據用戶ID取得訂單
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    // 建立新訂單
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // 更新訂單狀態（教練確認/拒絕）
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return orderService.getOrderById(id)
                .map(existing -> {
                    if (body.containsKey("orderStatus")) {
                        existing.setOrderStatus(body.get("orderStatus"));
                    }
                    return ResponseEntity.ok(orderService.updateOrder(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 刪除訂單
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}