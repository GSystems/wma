package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.infrastructure.entities.ProductEntity;

public interface ProductService {

	Iterable<ProductEntity> listAllProducts();

	ProductEntity getProductById(Integer id);

	ProductEntity saveProduct(ProductEntity product);

	void deleteProduct(Integer id);
}
