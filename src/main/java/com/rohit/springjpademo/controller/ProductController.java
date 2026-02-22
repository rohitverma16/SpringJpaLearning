package com.rohit.springjpademo.controller;

import com.rohit.springjpademo.dto.product.ProductRequestDto;
import com.rohit.springjpademo.dto.product.ProductResponseDto;
import com.rohit.springjpademo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.findById(id);
        if(productResponseDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.create(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.update(id,productRequestDto);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/lowStocks")
    public ResponseEntity<Void> deleteProduct() {
        List<ProductResponseDto> lowStocks = productService.findLowStocks(0);
        lowStocks.forEach(p -> productService.deleteById(p.id()));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponseDto>> getProductByName(@PathVariable String name) {
        List<ProductResponseDto> products = productService.findByName(name);
        if(products == null || products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/greaterThan/{price}")
    public ResponseEntity<List<ProductResponseDto>> getExpensiveProducts(@PathVariable Double price) {
        List<ProductResponseDto> highValueProduct = productService.findHighValueProduct(price);
        //List<ProductResponseDto> byPriceGreaterThan = productService.findByPriceGreaterThan(price);
        if(highValueProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(highValueProduct);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponseDto>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Page<ProductResponseDto> productsforPage = productService.findProductsforPage(page, size);
        return ResponseEntity.ok(productsforPage);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto>> searchByName(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {
        Page<ProductResponseDto> productByName = productService.search(name, page, size, sortBy);
        return ResponseEntity.ok(productByName);
    }

}
