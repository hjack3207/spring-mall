package com.tohru.springmall.service;

import com.tohru.springmall.constant.ProductCategory;
import com.tohru.springmall.dto.ProductRequest;
import com.tohru.springmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
