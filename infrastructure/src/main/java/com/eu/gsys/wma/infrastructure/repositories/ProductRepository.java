package com.eu.gsys.wma.infrastructure.repositories;

import com.eu.gsys.wma.infrastructure.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
