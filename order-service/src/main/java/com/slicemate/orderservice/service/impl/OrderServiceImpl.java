package com.slicemate.orderservice.service.impl;

import com.slicemate.orderservice.clients.CartServiceClient;
import com.slicemate.orderservice.clients.FoodServiceClient;
import com.slicemate.orderservice.clients.PaymentServiceClient;
import com.slicemate.orderservice.clients.UserServiceClient;
import com.slicemate.orderservice.dto.CartItemDTO;
import com.slicemate.orderservice.dto.OrderDTO;
import com.slicemate.orderservice.dto.OrderItemDTO;
import com.slicemate.orderservice.dto.PaymentDTO;
import com.slicemate.orderservice.entity.Order;
import com.slicemate.orderservice.entity.OrderItem;
import com.slicemate.orderservice.entity.OrderStatus;
import com.slicemate.orderservice.mapper.OrderMapper;
import com.slicemate.orderservice.repository.OrderRepository;
import com.slicemate.orderservice.service.OrderService;
import lombok.Builder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue paymentQueue() {
        return QueueBuilder.durable("payment_queue")
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "payment_queue_dlq")
                .build();
    }

    @Bean
    public Queue orderUpdateQueue() {
        return QueueBuilder.durable("order_update_queue")
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "order_update_queue_dlq")
                .build();
    }

    @Bean
    public Queue paymentDLQ() {
        return new Queue("payment_queue_dlq", true);
    }

    @Bean
    public Queue orderUpdateDLQ() {
        return new Queue("order_update_queue_dlq", true);
    }

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
        List<OrderItemDTO> orderItemDTOS = cartItems.stream().peek(cartItemDTO -> {
            if(!foodServiceClient.existsByFoodId(cartItemDTO.getFoodItemId())){
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Food item not found");
            }
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
                .id(savedOrder.getOrderId())
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
                    .id(order.getOrderId())
                    .status(order.getOrderStatus())
                    .totalPrice(order.getTotalPrice())
                    .orderItems(order.getOrderItemList().stream().map(OrderMapper::orderItemToOrderItemDTO).toList())
                    .userId(userId)
                    .orderDate(order.getOrderDate())
                    .build();
        })).toList();
    }

    @RabbitListener(queues = "order_update_queue")
    public void updateOrderStatus(PaymentDTO paymentDTO) {
        Order order = orderRepository.findById(paymentDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (paymentDTO.getPaymentStatus().equals("SUCCESS")) {
            order.setOrderStatus(OrderStatus.COMPLETED);
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
        }
        orderRepository.save(order);
    }


    @Override
    public OrderDTO placeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Order not found");
        }
        PaymentDTO paymentDTO = PaymentDTO.builder()
                .orderId(orderId)
                .amount(order.getTotalPrice())
                .build();
        rabbitTemplate.convertAndSend("payment_queue", paymentDTO);

        orderRepository.save(order);
        return OrderDTO.builder()
                .id(order.getOrderId())
                .status(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .orderItems(order.getOrderItemList().stream().map(OrderMapper::orderItemToOrderItemDTO).toList())
                .userId(order.getUserId())
                .orderDate(order.getOrderDate())
                .build();
    }
}
