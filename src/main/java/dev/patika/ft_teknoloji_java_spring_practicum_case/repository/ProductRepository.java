package dev.patika.ft_teknoloji_java_spring_practicum_case.repository;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByExpGreaterThan(Date exp);
    List<Product> findAllByExpLessThanEqual(Date exp);
    List<Product> findAllByExp(Date date);
}
