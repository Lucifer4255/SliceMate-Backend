package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.CartItemController;
import com.example.slicemate.payloads.ApiResponse;
import com.example.slicemate.payloads.CartItemDto;
import com.example.slicemate.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemControllerImpl implements CartItemController {
    @Autowired
    private CartItemService cartItemService;
    //fetch Cart elements
    @GetMapping("/{id}")
    public ResponseEntity<List<CartItemDto>> showCartItems(@PathVariable Integer id){
//        cartItemService.showCart(id);
    	
        return ResponseEntity.ok(this.cartItemService.showCart(id));
    }

    //addCartItems
    @PostMapping("/addtoCart/user/{userId}/food/{foodId}")
    public ResponseEntity<CartItemDto> addToCart(@RequestBody @Valid CartItemDto cartItemDto, @PathVariable Integer userId, @PathVariable Integer foodId){
        CartItemDto addedItem = this.cartItemService.addCartItem(cartItemDto,userId,foodId);
        return new ResponseEntity<>(addedItem,HttpStatus.CREATED);
    }
    @PutMapping("/updateItem/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@RequestBody CartItemDto cartItemDto,@PathVariable Integer id){
    	CartItemDto updatedItem =this.cartItemService.updateItem(cartItemDto,id);
    	return new ResponseEntity<>(updatedItem,HttpStatus.OK);
    }
    //deleteitem

    @DeleteMapping("/deleteitem/{id}")
    public ResponseEntity<ApiResponse> deleteItem(@PathVariable Integer id) {
        this.cartItemService.deletecartItem(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Item deleted successfully",true),HttpStatus.OK);
    }

    @DeleteMapping("/emptyCart/{id}")
    public ResponseEntity<ApiResponse> emptyCart(@PathVariable Integer id) {
        cartItemService.deleteAllItems(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("All Items deleted successfully",true),HttpStatus.OK);
    }


}
