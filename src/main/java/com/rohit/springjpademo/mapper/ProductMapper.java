package com.rohit.springjpademo.mapper;

import com.rohit.springjpademo.dto.*;
import com.rohit.springjpademo.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDto productRequestDto) {
        return new Product(
                null,
                productRequestDto.name(),
                productRequestDto.price(),
                productRequestDto.quantity(),
                "INTERNAL"+ LocalDateTime.now()
        );
    }

    public ProductResponseDto toDto(Product product) {
        if(product == null)
            return null;

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}
