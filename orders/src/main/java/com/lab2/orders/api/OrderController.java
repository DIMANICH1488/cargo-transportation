package com.lab2.orders.api;

import com.lab2.orders.api.dto.OrderItem;
import com.lab2.orders.repo.model.Order;
import com.lab2.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public final class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        List<Order> items = orderService.list();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> read(@PathVariable long id) {
        try {
            Order item = orderService.read(id);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderItem order) {
        try {
            Integer volume = order.getVolume();
            Integer weight = order.getWeight();
            String pointFrom = order.getPointFrom();
            String pointTo = order.getPointTo();
            Integer status = order.getStatus();
            Order item = orderService.create(volume, weight, pointFrom, pointTo, status);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable long id, @RequestBody OrderItem order) {
        try {
            Integer volume = order.getVolume();
            Integer weight = order.getWeight();
            String pointFrom = order.getPointFrom();
            String pointTo = order.getPointTo();
            Integer status = order.getStatus();
            Order item = orderService.update(id, volume, weight, pointFrom, pointTo, status);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderItem> remove(@PathVariable long id) {
        orderService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
