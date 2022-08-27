package dev.patika.ft_teknoloji_java_spring_practicum_case.repository;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.Product;
import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.ProductComment;
import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Long> {
    /* fetching processes */
    List<ProductComment> findAllByProductOrderByCommentDate(Product product);
    List<ProductComment> findAllByProductAndCommentDateGreaterThanEqualAndCommentDateLessThanEqualOrderByCommentDate(Product product,Date minDate, Date maxDate);
    List<ProductComment> findAllByProductAndCommentDateLessThanEqualOrderByCommentDate(Product product,Date date);
    List<ProductComment> findAllByUserOrderByCommentDate(User user);
    List<ProductComment> findAllByUserAndCommentDateGreaterThanEqualAndCommentDateLessThanEqual(User user,Date minDate, Date maxDate);
    List<ProductComment> findAllByUserAndCommentDateLessThanEqualOrderByCommentDate(User user,Date date);

    /* saving processes */
}
