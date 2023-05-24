package com.example.slicemate.servive.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.payloads.OrderItemDto;
import com.example.slicemate.repository.OrderItemRepository;
import com.example.slicemate.service.OrderItemService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public OrderItemDto itemToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = this.modelMapper.map(orderItem,OrderItemDto.class);
        return orderItemDto;
    }

    public OrderItem dtoToItem(OrderItemDto orderItemDto){
        OrderItem orderItem = this.modelMapper.map(orderItemDto,OrderItem.class);
        return orderItem;
    }
    @Override
    public List<OrderItemDto> getAllOrderItems(String id) {
        if(this.orderItemRepository.existsByUser(id)) {
        	List<OrderItem> items = (List<OrderItem>) this.orderItemRepository.findByUser(id);
        	List<OrderItemDto> itemDtos = items.stream().map(orderItem -> this.itemToDto(orderItem)).collect(Collectors.toList());
        	return itemDtos;
        }
        else throw new ResourceNotFoundException("User with orders", "id", id);
    }

    @Override
    public OrderItemDto saveItem(OrderItemDto orderItemDto) {
    	OrderItem orderItem=this.dtoToItem(orderItemDto);
        if(this.orderItemRepository.existsById(orderItem.getOrderItemId())) {
        	throw new ResourceAlreadyExistsException("order", "id", orderItem.getOrderItemId());
        }
        else {
        	OrderItem savedItem = this.orderItemRepository.save(orderItem);
        	return this.itemToDto(savedItem);
        }
    }

}
