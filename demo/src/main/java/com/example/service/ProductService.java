package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private  final ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo)
    {
        this.productRepo = productRepo;
    }
    public void insert(Product product)
    {
        productRepo.save(product);
    }
    public List<Product> getProducts()
    {
        Optional<List<Product>> products = Optional.of(productRepo.findAll());
        if (products.isPresent())
            return products.get();
        return new ArrayList<>();
    }

    public void deleteProduct(Long id)
    {
        productRepo.deleteById(id);
    }
}
