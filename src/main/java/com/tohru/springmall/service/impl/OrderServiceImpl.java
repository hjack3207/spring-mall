package com.tohru.springmall.service.impl;

import com.tohru.springmall.dao.OrderDao;
import com.tohru.springmall.dao.ProductDao;
import com.tohru.springmall.dao.UserDao;
import com.tohru.springmall.dto.BuyItem;
import com.tohru.springmall.dto.CreateOrderRequest;
import com.tohru.springmall.model.Order;
import com.tohru.springmall.model.OrderItem;
import com.tohru.springmall.model.Product;
import com.tohru.springmall.model.User;
import com.tohru.springmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);

        if(user == null) {
            log.warn("該userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查商品是否存在 庫存是否足夠
            if(product == null) {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if(product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存不足，無法購買。剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

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
