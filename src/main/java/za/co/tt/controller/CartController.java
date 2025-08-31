package za.co.tt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tt.domain.Cart;
import za.co.tt.service.ICartService;
import za.co.tt.service.impl.CartItemServiceImpl;

import java.util.List;

import java.awt.*;

@RestController
@RequestMapping

public class CartController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.createCart(cart));
    }

    @PutMapping
    public ResponseEntity<Cart> updateCart(@PathVariable Long cartId, @RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.updateCart(cartId, cart));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}

