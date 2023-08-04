package com.example.slicemate.service.impl;

import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.OrderItem;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.OrderItemDto;
import com.example.slicemate.repository.FoodItemRepository;
import com.example.slicemate.repository.OrderItemRepository;
import com.example.slicemate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.example.slicemate.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderItemServiceImplTest {
    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private UserRepository userRepo;

    @Mock
    private FoodItemRepository foodRepo;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private OrderItemServiceImpl orderService;
    @Test
    void getAllOrderItems() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        OrderItem orderItem = OrderItem.builder().foodItem(foodItem).user(user).orderedAt("some time")
                .address("some address").qty(1).price(295.0).build();
        OrderItemDto orderItemDto = OrderItemDto.builder().foodItem(foodItem).user(user).orderedAt("some time")
                .address("some address").qty(1).price(295.0).build();
        List<OrderItem> items = Mockito.mock(List.class);
        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
        when(orderItemRepository.findByUser(Mockito.any(User.class))).thenReturn(items);

        List<OrderItemDto> itemDtos = orderService.getAllOrderItems(1);

        assertNotNull(itemDtos);

    }

    @Test
    void saveItem() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        OrderItem orderItem = OrderItem.builder().foodItem(foodItem).user(user).orderedAt("some time")
                .address("some address").qty(1).price(295.0).build();
        OrderItemDto orderItemDto = OrderItemDto.builder().foodItem(foodItem).user(user).orderedAt("some time")
                .address("some address").qty(1).price(295.0).build();
        when(modelMapper.map(orderItemDto, OrderItem.class)).thenReturn(orderItem);
        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
        when(foodRepo.findById(2)).thenReturn(Optional.ofNullable(foodItem));
        when(orderItemRepository.save(Mockito.any(OrderItem.class))).thenReturn(orderItem);
        when(modelMapper.map(orderItem, OrderItemDto.class)).thenReturn(orderItemDto);

        OrderItemDto savedOrder = orderService.saveItem(orderItemDto,1,2);
        assertEquals(orderItemDto,savedOrder);

    }
}