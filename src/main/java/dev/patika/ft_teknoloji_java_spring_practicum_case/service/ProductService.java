package dev.patika.ft_teknoloji_java_spring_practicum_case.service;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.Product;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public List<Product> getAllExpiredProducts(){
       return this.repository.findAllByExpLessThanEqual(new Date());}
    public List<Product> getAllNotExpiredProducts(){
        List<Product> products = new ArrayList<>(this.repository.findAllByExpGreaterThan(new Date()));
        products.addAll(this.repository.findAllByExp(null));
        return products;
    }
}
