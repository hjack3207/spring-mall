package com.tohru.springmall.service;

import com.tohru.springmall.dto.CreateOrderRequest;
import com.tohru.springmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
