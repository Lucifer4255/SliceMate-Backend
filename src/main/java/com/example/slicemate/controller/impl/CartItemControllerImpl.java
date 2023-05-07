package com.example.slicemate.controller.impl;

import com.example.slicemate.controller.CartItemController;
import com.example.slicemate.entity.CartItem;
import com.example.slicemate.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemControllerImpl implements CartItemController {
    @Autowired
    private CartItemService cartItemService;
    //fetch Cart elements
    @GetMapping("/{id}")
    public List<CartItem> showCartItems(@PathVariable String id){
        return cartItemService.showCart(id);
    }

    //addCartItems
    @PostMapping("/addtoCart")
    public void addToCart(@RequestBody CartItem cartItem){
        cartItemService.addCartItem(cartItem);
    }

    //deleteitem

    @DeleteMapping("/deleteitem/{id}")
    public void deleteItem(@PathVariable Integer id) {
        cartItemService.deletecartItem(id);
    }

    @DeleteMapping("/emptyCart/{id}")
    public void emptyCart(String id) {
        cartItemService.deleteAllItems(id);
    }


}
