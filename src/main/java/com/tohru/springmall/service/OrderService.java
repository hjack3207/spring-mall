package com.tohru.springmall.service;

import com.tohru.springmall.dto.CreateOrderRequest;
import com.tohru.springmall.dto.OrderQueryParams;
import com.tohru.springmall.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
