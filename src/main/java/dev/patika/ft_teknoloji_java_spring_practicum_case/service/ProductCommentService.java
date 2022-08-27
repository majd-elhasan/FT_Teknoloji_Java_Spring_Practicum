package dev.patika.ft_teknoloji_java_spring_practicum_case.service;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.Product;
import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.ProductComment;
import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.User;
import dev.patika.ft_teknoloji_java_spring_practicum_case.exception.NotFoundException;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.ProductCommentRepository;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.ProductRepository;
import dev.patika.ft_teknoloji_java_spring_practicum_case.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCommentService {
    private final ProductCommentRepository repository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductCommentService(ProductCommentRepository repository,ProductRepository productRepository,UserRepository userRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    public List<ProductComment> findAllByProductId(long id,Date minDate,Date maxDate){
        Product product= productRepository.findById(id).orElseThrow(()->new NotFoundException("a product with id : "+id+" Not Found"));
        if(minDate== null && maxDate!=null)
            return repository.findAllByProductAndCommentDateLessThanEqualOrderByCommentDate(product,maxDate);
        else if (minDate != null && maxDate == null) {
            return repository.findAllByProductAndCommentDateGreaterThanEqualAndCommentDateLessThanEqualOrderByCommentDate(product,minDate,new Date());
        } else if (minDate != null && maxDate != null) {
            return repository.findAllByProductAndCommentDateGreaterThanEqualAndCommentDateLessThanEqualOrderByCommentDate(product,minDate,maxDate);
        }
        return repository.findAllByProductOrderByCommentDate(product);
    }
    public List<ProductComment> findAllByUserId(long id,Date minDate,Date maxDate){
        User user= userRepository.findById(id).orElseThrow(()->new NotFoundException("a user with id : "+id+" Not Found"));
        if(minDate== null && maxDate!=null)
            return repository.findAllByUserAndCommentDateLessThanEqualOrderByCommentDate(user,maxDate);
        else if (minDate != null && maxDate == null) {
            return repository.findAllByUserAndCommentDateGreaterThanEqualAndCommentDateLessThanEqual(user,minDate,new Date());
        } else if (minDate != null && maxDate != null) {
            return repository.findAllByUserAndCommentDateGreaterThanEqualAndCommentDateLessThanEqual(user,minDate,maxDate);
        }
        return repository.findAllByUserOrderByCommentDate(user);
    }
}
