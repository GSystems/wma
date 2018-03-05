package com.eu.gsys.wma.web.service;

import com.eu.gsys.wma.web.domain.entities.ProductEntity;
import com.eu.gsys.wma.web.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Iterable <ProductEntity> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Integer id) {
        return null;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(ProductEntity productEntity) {
        productRepository.delete(productEntity);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
