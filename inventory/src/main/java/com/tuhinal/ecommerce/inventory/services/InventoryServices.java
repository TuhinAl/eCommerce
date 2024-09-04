package com.tuhinal.ecommerce.inventory.services;

import com.tuhinal.ecommerce.inventory.dto.InventoryDto;
import com.tuhinal.ecommerce.inventory.dto.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.inventory.dto.req_dto.InventorySearchDto;
import com.tuhinal.ecommerce.inventory.entity.Inventory;
import com.tuhinal.ecommerce.inventory.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.tuhinal.ecommerce.inventory.utils.TransformUtil.copyProp;

@Service
public class InventoryServices {

    private final InventoryRepository inventoryRepository;

    public InventoryServices(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public InventoryDto save(InventoryDto inventoryDto) {
        Inventory inventory = copyProp(inventoryDto, Inventory.class);
        Inventory persistedInventory = inventoryRepository.save(inventory);
        return copyProp(persistedInventory, InventoryDto.class);
    }


    @Transactional
    public Boolean isInStock(InventoryCheckDto inventoryCheckDto) {

        if (Objects.nonNull(inventoryCheckDto.getSkuCode()) && Objects.nonNull(inventoryCheckDto.getQuantity())) {
            return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqualAndEnabled(inventoryCheckDto.getSkuCode(),
                    inventoryCheckDto.getQuantity(), Boolean.TRUE);
        } else return false;
    }

    public Page<InventoryDto> search(InventorySearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

         Page<Inventory> allInventory = inventoryRepository.findAll(pageable);
        List<InventoryDto> list = allInventory.stream().map(inventory -> copyProp(inventory, InventoryDto.class)).toList();
        return new PageImpl<>(list, pageable, searchDto.getSize());
    }
}
