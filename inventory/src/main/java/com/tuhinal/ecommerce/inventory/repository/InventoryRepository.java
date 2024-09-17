package com.tuhinal.ecommerce.inventory.repository;

import com.tuhinal.ecommerce.inventory.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

/*    @Override
    @Query(value = "select * from product_info pi where pi.enabled is true ", nativeQuery = true)
    Page<Inventory> findAll(Pageable pageablfe);*/

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
