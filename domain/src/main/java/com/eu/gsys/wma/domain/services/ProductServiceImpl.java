package com.eu.gsys.wma.domain.services;

import com.eu.gsys.wma.infrastructure.entities.ProductEntity;
import com.eu.gsys.wma.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<ProductEntity> listAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public ProductEntity getProductById(Integer id) {
		return productRepository.findById(id).get();
	}

	@Override
	public ProductEntity saveProduct(ProductEntity product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
