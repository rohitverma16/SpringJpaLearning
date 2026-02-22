package com.rohit.springjpademo.dto.product;

public record ProductResponseDto(Long id, String name, Double price, Integer quantity) {
}
