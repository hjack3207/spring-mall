package com.tohru.springmall.service.impl;

import com.tohru.springmall.dao.OrderDao;
import com.tohru.springmall.dao.ProductDao;
import com.tohru.springmall.dto.BuyItem;
import com.tohru.springmall.dto.CreateOrderRequest;
import com.tohru.springmall.model.OrderItem;
import com.tohru.springmall.model.Product;
import com.tohru.springmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總價錢
            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount = totalAmount + amount;

            //轉換 BuyItem to OderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(totalAmount);

            orderItemList.add(orderItem);
        }


        //創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
