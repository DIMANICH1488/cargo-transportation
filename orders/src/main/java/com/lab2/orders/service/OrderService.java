package com.lab2.orders.service;

import com.lab2.orders.repo.OrderRepo;
import com.lab2.orders.repo.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class OrderService {

    private final OrderRepo orderRepo;

    public List<Order> list() {
        final List<Order> items = orderRepo.findAll();
        return items;
    }

    public Order read(long id) throws IllegalArgumentException {
        final Optional<Order> item = orderRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("Order unknown");
        }
        return item.get();
    }

    public Order create(Integer volume, Integer weight, String pointFrom, String pointTo, Integer status) {
        final Order order = new Order(volume, weight, pointFrom, pointTo, status);
        final Order item = orderRepo.save(order);
        return item;
    }

    public Order update(long id, Integer volume, Integer weight, String pointFrom, String pointTo, Integer status) throws IllegalArgumentException {
        final Optional<Order> item = orderRepo.findById(id);
        if (item.isEmpty()) {
            throw new IllegalArgumentException("Order unknown");
        }
        Order order = item.get();
        if (volume != null && volume > 0) {
            order.setVolume(volume);
        }
        if (weight != null && weight > 0) {
            order.setWeight(weight);
        }
        if (pointFrom != null && !pointFrom.isBlank()) {
            order.setPointFrom(pointFrom);
        }
        if (pointTo != null && !pointTo.isBlank()) {
            order.setPointTo(pointTo);
        }
        if (status != null) {
            order.setStatus(status );
        }
        orderRepo.save(order);
        return order;
    }

    public void remove(long id) {
        orderRepo.deleteById(id);
    }
}
