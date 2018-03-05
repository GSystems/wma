package com.eu.gsys.wma.web.service;

import com.eu.gsys.wma.web.domain.entities.ProductEntity;

public interface ProductService {

    Iterable<ProductEntity> listAllProducts();
    ProductEntity getProductById(Integer id);
    ProductEntity saveProduct(ProductEntity product);
    void deleteProduct(ProductEntity id);
}
