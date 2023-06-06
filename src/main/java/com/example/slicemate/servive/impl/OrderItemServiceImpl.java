package com.example.slicemate.servive.impl;

import com.example.slicemate.Exception.ResourceAlreadyExistsException;
import com.example.slicemate.Exception.ResourceNotFoundException;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.OrderItemDto;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.repository.OrderItemRepository;
import com.example.slicemate.repository.UserRepository;
import com.example.slicemate.service.OrderItemService;
import jakarta.persistence.criteria.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoodItemRepository foodRepo;

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
    public List<OrderItemDto> getAllOrderItems(Integer id) {
            User user= this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        	List<OrderItem> items = (List<OrderItem>) this.orderItemRepository.findByUser(user);
        	List<OrderItemDto> itemDtos = items.stream().map(orderItem -> this.itemToDto(orderItem)).collect(Collectors.toList());
        	return itemDtos;
    }

    @Override
    public OrderItemDto saveItem(OrderItemDto orderItemDto, Integer foodId, Integer userId) {
    	OrderItem orderItem=this.dtoToItem(orderItemDto);
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        FoodItem foodItem = this.foodRepo.findById(foodId).orElseThrow(() -> new ResourceNotFoundException("Food item","id",foodId));
        orderItem.setUser(user);
        orderItem.setFoodItem(foodItem);

        OrderItem createdOrder = this.orderItemRepository.save(orderItem);
        return this.itemToDto(createdOrder);

    }

}
