package dev.patika.ft_teknoloji_java_spring_practicum_case.controller;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.Product;
import dev.patika.ft_teknoloji_java_spring_practicum_case.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping("expired")
    public ResponseEntity<List<Product>> getAllExpiredProducts(){
        return ResponseEntity.ok(service.getAllExpiredProducts());
    }
    @GetMapping("valid")
    public ResponseEntity<List<Product>> getAllNotExpiredProduct(){
        return ResponseEntity.ok(service.getAllNotExpiredProducts());
    }
}
