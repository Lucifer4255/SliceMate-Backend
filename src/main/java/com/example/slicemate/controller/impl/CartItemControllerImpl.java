package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.CartItemController;
import com.example.slicemate.entity.CartItem;
import com.example.slicemate.payloads.ApiResponse;
import com.example.slicemate.payloads.CartItemDto;
import com.example.slicemate.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemControllerImpl implements CartItemController {
    @Autowired
    private CartItemService cartItemService;
    //fetch Cart elements
    @GetMapping("/{id}")
    public ResponseEntity<List<CartItemDto>> showCartItems(@PathVariable String id){
//        cartItemService.showCart(id);
    	
        return ResponseEntity.ok(this.cartItemService.showCart(id));
    }

    //addCartItems
    @PostMapping("/addtoCart")
    public ResponseEntity<CartItemDto> addToCart(@RequestBody CartItemDto cartItemDto){
        CartItemDto addedItem = this.cartItemService.addCartItem(cartItemDto);
        return new ResponseEntity<>(addedItem,HttpStatus.CREATED);
    }

    //deleteItem

    @DeleteMapping("/deleteitem/{id}")
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable Integer id) {
        this.cartItemService.deletecartItem(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Item deleted successfully",true),HttpStatus.OK);
    }

    @DeleteMapping("/emptyCart/{id}")
    public ResponseEntity<ApiResponse> emptyCart(@PathVariable String id) {
        cartItemService.deleteAllItems(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("All Items deleted successfully",true),HttpStatus.OK);
    }


}
