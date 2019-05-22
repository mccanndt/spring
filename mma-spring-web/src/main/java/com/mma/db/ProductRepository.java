package com.mma.db;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mma.business.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	Optional<Product> findByCode(String code);
}
