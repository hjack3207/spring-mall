package com.tohru.springmall.service.impl;

import com.tohru.springmall.dao.ProductDao;
import com.tohru.springmall.dto.ProductRequest;
import com.tohru.springmall.model.Product;
import com.tohru.springmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
