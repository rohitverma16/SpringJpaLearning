package com.rohit.springjpademo.repo;

import com.rohit.springjpademo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    //Derived Query
    List<Product> findByName(String name);
    List<Product> findByPriceGreaterThan(Double price);

    //JPQL Query
    @Query("SELECT p FROM Product p WHERE p.quantity<=:quantity")
    List<Product> findLowStocks(@Param("quantity") Integer quantity);

    //Native Query
    @Query(value = "SELECT * From products WHERE price>:price", nativeQuery = true)
    List<Product> findHighValueProducts(@Param("price") Double price);

    // Native Update
    @Modifying
    @Query(value = "UPDATE products SET price = :price WHERE id = :id", nativeQuery = true)
    int updatePrice(@Param("id") Long id, @Param("price") Double price);

    //filter+paging+sorting
    Page<Product> findByNameContaining(String name, Pageable pageable);

    //Native Query with Pagination (countQuery as pagination required total count)
    @Query(value = "SELECT * FROM products WHERE price > :price",
            countQuery = "SELECT COUNT(*) FROM products WHERE price > :price",
            nativeQuery = true)
    Page<Product> findExpensiveProducts( @Param("price") Double price, Pageable pageable);

}
