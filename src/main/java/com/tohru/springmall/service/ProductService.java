package com.tohru.springmall.service;

import com.tohru.springmall.dto.ProductRequest;
import com.tohru.springmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

}
