package com.tohru.springmall.service;

import com.tohru.springmall.dto.ProductQueryParams;
import com.tohru.springmall.dto.ProductRequest;
import com.tohru.springmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
