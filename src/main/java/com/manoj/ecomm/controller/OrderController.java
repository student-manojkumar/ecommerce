package com.manoj.ecomm.controller;

import com.manoj.ecomm.dto.OrderDTO;
import com.manoj.ecomm.model.OrderRequest;
import com.manoj.ecomm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest)
    {
        return orderService.placeOrder(userId,orderRequest.getProductQuantities(),orderRequest.getTotalAmount());
    }
    @GetMapping("/all_order")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("user/{userId}")
    public List<OrderDTO> orderByUser(@PathVariable Long userId){

        return orderService.getorderByUser(userId);
    }
}
