package com.slicemate.orderservice.service.impl;

import com.slicemate.orderservice.clients.CartServiceClient;
import com.slicemate.orderservice.clients.FoodServiceClient;
import com.slicemate.orderservice.clients.UserServiceClient;
import com.slicemate.orderservice.dto.CartItemDTO;
import com.slicemate.orderservice.dto.OrderDTO;
import com.slicemate.orderservice.dto.OrderItemDTO;
import com.slicemate.orderservice.entity.Order;
import com.slicemate.orderservice.entity.OrderItem;
import com.slicemate.orderservice.entity.OrderStatus;
import com.slicemate.orderservice.mapper.OrderMapper;
import com.slicemate.orderservice.repository.OrderRepository;
import com.slicemate.orderservice.service.OrderService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@Builder
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private FoodServiceClient foodServiceClient;
    @Autowired
    private CartServiceClient cartServiceClient;


    @Override
    public OrderDTO createOrder(Long userId) {
        if(!userServiceClient.existsByUserId(userId)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found");
        }

        ResponseEntity<List<CartItemDTO>> cartItemsResponse = cartServiceClient.getCartByUserId(userId);
        List<CartItemDTO> cartItems = cartItemsResponse.getBody();
        if(cartItems == null || cartItems.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cart is empty");
        }
        List<OrderItemDTO> orderItemDTOS = cartItems.stream().map(cartItemDTO -> {
            if(!foodServiceClient.existsByFoodId(cartItemDTO.getFoodItemId())){
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Food item not found");
            }
            return cartItemDTO;
        }).map(OrderMapper::cartToOrderItemDTO).toList();

        List<OrderItem> orderItems = orderItemDTOS.stream().map(OrderMapper::orderItemDTOToOrderItem).toList();
        double totalPrice = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();

        Order order = Order.builder()
                .userId(userId)
                .orderStatus(OrderStatus.PENDING)
                .totalPrice(totalPrice)
                .orderItemList(orderItems)
                .build();

        Order savedOrder =  orderRepository.save(order);
        return OrderDTO.builder()
                .status(savedOrder.getOrderStatus())
                .totalPrice(savedOrder.getTotalPrice())
                .orderItems(savedOrder.getOrderItemList().stream().map(OrderMapper::orderItemToOrderItemDTO).toList())
                .userId(userId)
                .orderDate(savedOrder.getOrderDate())
                .build();////revist this area to create a perfect dto mapper
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream().map((order -> {
            return OrderDTO.builder()
                    .status(order.getOrderStatus())
                    .totalPrice(order.getTotalPrice())
                    .orderItems(order.getOrderItemList().stream().map(OrderMapper::orderItemToOrderItemDTO).toList())
                    .userId(userId)
                    .orderDate(order.getOrderDate())
                    .build();
        })).toList();
    }
}
