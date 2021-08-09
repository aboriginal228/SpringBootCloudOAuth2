package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Filter;
import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.repositories.ProdDataRepo;
import com.geekbrains.demoboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProdDataRepo productRepository;

    @Autowired
    public void setProductRepository(ProdDataRepo productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProducts(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec, pageable).getContent();
    }

    public List<Product> get3MostViewed() {return productRepository.threeMostViewed(); }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }

}
