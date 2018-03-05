package com.eu.gsys.wma.web.domain.repositories;

import com.eu.gsys.wma.web.domain.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
