package com.tohru.springmall.dao;

import com.tohru.springmall.dto.ProductRequest;
import com.tohru.springmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
