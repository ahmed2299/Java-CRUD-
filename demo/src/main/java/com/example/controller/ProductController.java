package com.example.controller;

import com.example.dto.ProductDto;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
   private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAll()
    {
        List<Product> products = productService.getProducts();
//        List<ProductDto> productDtos =  products.parallelStream().map(this::mapDTOToEntity).filter(p-> !p.getName().equalsIgnoreCase("Product 2")).limit(1).collect(Collectors.toList());
        List<ProductDto> productDtos =  products.parallelStream().map(this::mapDTOToEntity).collect(Collectors.toList());

        return new ResponseEntity<>(productDtos, new HttpHeaders(), HttpStatus.OK);
    }
    private ProductDto mapDTOToEntity(Product p) {
        ProductDto entity = new ProductDto();
        entity.setId(p.getId());
        entity.setName(p.getName());
        // Set other properties if needed
        return entity;
    }
    @PostMapping("/save")
    public ResponseEntity<String> insert(@RequestBody Product product)
    {
        productService.insert(product);
        return new ResponseEntity<>("done", new HttpHeaders(), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        productService.deleteProduct(id);
        return new ResponseEntity<>("deleted successfully", new HttpHeaders(), HttpStatus.OK);
    }

}
