package com.manoj.ecomm.service;

import com.manoj.ecomm.dto.OrderDTO;
import com.manoj.ecomm.dto.OrderItemDTO;
import com.manoj.ecomm.model.OrderItem;
import com.manoj.ecomm.model.Orders;
import com.manoj.ecomm.model.Product;
import com.manoj.ecomm.model.User;
import com.manoj.ecomm.repo.OrderRepository;
import com.manoj.ecomm.repo.ProductRepository;
import com.manoj.ecomm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {

      User user= userRepository.findById(userId)
              .orElseThrow(()-> new RuntimeException("User not found"));
        Orders order=new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("panding");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems=new ArrayList<>();

        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();

        for(Map.Entry<Long, Integer> entry:productQuantities.entrySet()){
           Product product= productRepository.findById(entry.getKey())
                   .orElseThrow(()->new RuntimeException("Product Not found"));
           OrderItem orderItem=new OrderItem();
           orderItem.setOrder(order);
           orderItem.setProduct(product);
           orderItem.setQuantity(entry.getValue());
           orderItems.add(orderItem);
           orderItemDTOS.add(new OrderItemDTO(product.getName(),product.getPrice(),entry.getValue()));
        }
        order.setOrderItems(orderItems);
        Orders saveOrder= orderRepository.save(order);

        return new OrderDTO(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getStatus(),
                saveOrder.getOrderDate(),orderItemDTOS);

    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders= orderRepository.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders) {
      List<OrderItemDTO> OrderItems= orders.getOrderItems().stream().
                map(iteam->new OrderItemDTO(
                        iteam.getProduct().getName(),
                        iteam.getProduct().getPrice(),
                        iteam.getQuantity())).collect(Collectors.toList());
      return new OrderDTO(
              orders.getId(),
              orders.getTotalAmount(),
              orders.getStatus(),
              orders.getOrderDate(),
              orders.getUser()!=null ? orders.getUser().getName() : "unknow",
              orders.getUser()!=null ? orders.getUser().getEmail() : "Unknow",
              OrderItems
      );
    }

    public List<OrderDTO> getorderByUser(Long userId) {
        Optional<User> userOP=userRepository.findById(userId);
        if(userOP.isEmpty()){
            throw new RuntimeException("user not fount");
        }
        User user=userOP.get();
         List<Orders> ordersList=orderRepository.findByUser(user);
         return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
