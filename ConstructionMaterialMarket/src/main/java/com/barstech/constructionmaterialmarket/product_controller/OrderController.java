package com.barstech.constructionmaterialmarket.product_controller;

import com.barstech.constructionmaterialmarket.product_model.Order;
import com.barstech.constructionmaterialmarket.product_service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RestController("/api")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> listOfOrder() {
        List<Order> list = orderService.listOfOrder();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOrder(@RequestBody Order order) {
        if (order != null) {
            orderService.saveOrder(order);
            return new ResponseEntity<>("Order Crated...", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Bad Request.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/orders/{id}/update")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order updateOrder) {
       Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>("Order not Found", HttpStatus.NOT_FOUND);
        } else {
           order.setProducts(updateOrder.getProducts());
            // Diğer alanları güncelleyin
           orderService.saveOrder(order);
            return new ResponseEntity<>("Order is Updated", HttpStatus.OK);
        }
    }

    @GetMapping("/orders/{id}/found")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
       Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    @PostMapping("/orders/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
       Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>("Order Not Found",HttpStatus.NOT_FOUND);
        } else {
           orderService.cancelOrder(order);
            return new ResponseEntity<>("Order is Canceled...", HttpStatus.OK);
        }
    }
}
