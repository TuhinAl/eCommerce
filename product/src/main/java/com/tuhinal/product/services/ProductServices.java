package com.tuhinal.product.services;

import com.tuhinal.product.dto.ProductDto;
import com.tuhinal.product.dto.req_dto.ProductSearchDto;
import com.tuhinal.product.entity.Product;
import com.tuhinal.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tuhinal.product.utils.TransformUtil.copyProp;

@Service
public class ProductServices {

    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
        Product product = copyProp(productDto, Product.class);
        Product persistedProduct = productRepository.save(product);
        return copyProp(persistedProduct, ProductDto.class);
    }

    public Page<ProductDto> search(ProductSearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

         Page<Product> allProducts = productRepository.findAll(pageable);
        List<ProductDto> list = allProducts.stream().map(product -> copyProp(product, ProductDto.class)).toList();
        return new PageImpl<>(list, pageable, searchDto.getSize());
    }
}
