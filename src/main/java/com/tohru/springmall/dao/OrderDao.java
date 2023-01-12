package com.tohru.springmall.dao;

import com.tohru.springmall.dto.OrderQueryParams;
import com.tohru.springmall.model.Order;
import com.tohru.springmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
