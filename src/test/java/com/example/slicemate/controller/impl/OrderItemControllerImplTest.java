package com.example.slicemate.controller.impl;

import com.example.slicemate.entity.CartItem;
import com.example.slicemate.entity.FoodItem;
import com.example.slicemate.entity.User;
import com.example.slicemate.payloads.OrderItemDto;
import com.example.slicemate.service.OrderItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.slicemate.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderItemControllerImplTest {

    @Mock
    private OrderItemService orderItemService;
    @InjectMocks
    private OrderItemControllerImpl orderItemController;
    @Test
    void getAllOrders() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        OrderItemDto orderItemDto = OrderItemDto.builder().foodItem(foodItem).user(user).orderedAt("some time")
                .address("some address").qty(1).price(295.0).build();
        List<OrderItemDto> items = Mockito.mock(List.class) ;
        when(orderItemService.getAllOrderItems(1)).thenReturn(items);
        ResponseEntity<List<OrderItemDto>> resp = orderItemController.getAllOrders(1);
        assertEquals(HttpStatus.OK,resp.getStatusCode());

    }

    @Test
    void addOrder() {
        FoodItem foodItem = FoodItem.builder().name("pizaa")
                .price(230.0).Category("non veg").description("Awesome")
                .imageUrl("somethingurl").build();
        User user = User.builder().name("Biley").email("example1@gmail.com").password("hello@123").role(USER).build();
        OrderItemDto orderItemDto = OrderItemDto.builder().foodItem(foodItem).user(user).orderedAt("some time")
                .address("some address").qty(1).price(295.0).build();
        when(orderItemService.saveItem(Mockito.any(OrderItemDto.class),Mockito.anyInt(),Mockito.anyInt())).thenReturn(orderItemDto);
        ResponseEntity<OrderItemDto> resp = orderItemController.addOrder(orderItemDto,1,2);
        assertEquals(HttpStatus.CREATED,resp.getStatusCode());
    }
}