package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdDataRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query(value = "select * from boot.products order by views desc limit 3", nativeQuery = true)
    List<Product> threeMostViewed();
}
