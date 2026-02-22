package com.rohit.springjpademo.service;

import com.rohit.springjpademo.dto.product.ProductRequestDto;
import com.rohit.springjpademo.dto.product.ProductResponseDto;
import com.rohit.springjpademo.entity.Product;
import com.rohit.springjpademo.mapper.ProductMapper;
import com.rohit.springjpademo.repo.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository productRepository,ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    //Basic Crud Operations
    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(mapper::toDto).toList();
    }

    public ProductResponseDto findById(Long id) {
        Product product = productRepository.findById(id).orElseGet(() -> null);
        return mapper.toDto(product);
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = mapper.toEntity(productRequestDto)    ;
        Product savedProduct= productRepository.save(product);
        return mapper.toDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product product =productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("product not found"));

        // Only update fields exposed in DTO
        product.setName(productRequestDto.name());
        product.setPrice(productRequestDto.price());
        product.setQuantity(productRequestDto.quantity());

        // internalCode remains unchanged
        return mapper.toDto(productRepository.save(product));
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    //Derived Operations
    public List<ProductResponseDto> findByName(String name){
        List<Product> productList = productRepository.findByName(name);
        return productList.stream().map(mapper::toDto).toList();
    }

    public List<ProductResponseDto> findByPriceGreaterThan(Double price) {
        List<Product> productList = productRepository.findByPriceGreaterThan(price);
        return productList.stream().map(mapper::toDto).toList();
    }

    //JPQL Query
    public List<ProductResponseDto> findLowStocks(Integer quantity) {
        return productRepository.findLowStocks(quantity).stream().map(mapper::toDto).toList();
    }

    //Native Query
    public List<ProductResponseDto> findHighValueProduct(Double price) {
        return productRepository.findHighValueProducts(price).stream().map(mapper::toDto).toList();
    }
    
    //paging
    public Page<ProductResponseDto> findProductsforPage(int page, int size) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        return  products.map(mapper::toDto);
    }

    //filter+paging+sorting
    public Page<ProductResponseDto> search(String name, int page, int size,String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> pageByNameContaining = productRepository.findByNameContaining(name, pageable);
        return pageByNameContaining.map(mapper::toDto);
    }

    public Page<ProductResponseDto> findExpensiveProducts(Double price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
        Page<Product> expensiveProducts = productRepository.findExpensiveProducts(price, pageable);
        return expensiveProducts.map(mapper::toDto);
    }

}
