package com.tuhinal.product.repository;

import com.tuhinal.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Override
    @Query(value = "select * from product_info pi where pi.enabled is true ", nativeQuery = true)
    Page<Product> findAll(Pageable pageablfe);
}
