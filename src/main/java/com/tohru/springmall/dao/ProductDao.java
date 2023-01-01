package com.tohru.springmall.dao;

import com.tohru.springmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
