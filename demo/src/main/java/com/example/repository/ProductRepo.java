package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("select p  from Product p where p.name = ?1")
    public Product findpro(String name);
    @Override
    @EntityGraph(value = "loadbrands")
    public List<Product> findAll();
}
