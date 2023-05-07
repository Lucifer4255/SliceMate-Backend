package com.example.slicemate.servive.impl;

import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.repository.OrderItemRepository;
import com.example.slicemate.service.OrderItemService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> getAllOrderItems(String id) {
        List<OrderItem> items = new ArrayList<>();
        orderItemRepository.findByUser(id).forEach(item->items.add(item));
        return items;
    }

    @Override
    public void saveItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

}
