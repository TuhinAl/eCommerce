package com.tuhinal.ecommerce.repository;

import com.tuhinal.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Override
    @Query(value = "select * from order_info oi where oi.enabled is true ", nativeQuery = true)
    Page<Order> findAll(Pageable pageablfe);
}
