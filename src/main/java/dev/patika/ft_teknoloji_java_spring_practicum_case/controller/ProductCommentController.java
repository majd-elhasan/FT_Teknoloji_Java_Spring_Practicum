package dev.patika.ft_teknoloji_java_spring_practicum_case.controller;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.ProductComment;
import dev.patika.ft_teknoloji_java_spring_practicum_case.responseEntity.ResponseComment;
import dev.patika.ft_teknoloji_java_spring_practicum_case.service.ProductCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("Comments")
public class ProductCommentController {
    private final ProductCommentService service;

    public ProductCommentController(ProductCommentService service) {
        this.service = service;
    }
    @GetMapping("product")
    public ResponseEntity getAllCommentsRelatedToProduct(@RequestParam long id, @RequestParam(required = false)String minDate, @RequestParam(required = false)String maxDate){
        try {
            Date minDateD = minDate ==null ? null:Date.from(LocalDateTime.parse(minDate
            ).atZone(ZoneId.systemDefault()).toInstant());
            Date maxDateD = maxDate ==null ? null: Date.from(LocalDateTime.parse(maxDate
            ).atZone(ZoneId.systemDefault()).toInstant());
            // dateTime : yyyy-MM-dd 'T' HH:mm:ss
            List<ProductComment> fetchedComments = service.findAllByProductId(id,minDateD,maxDateD);
            return ResponseEntity.ok(responseMapping(fetchedComments) );
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("user")
    public ResponseEntity getAllCommentsRelatedToUser(@RequestParam long id, @RequestParam(required = false)String minDate, @RequestParam(required = false)String maxDate){
       try {
           Date minDateD = minDate ==null ? null:Date.from(LocalDateTime.parse(minDate
           ).atZone(ZoneId.systemDefault()).toInstant());
           Date maxDateD = maxDate ==null ? null:Date.from(LocalDateTime.parse(maxDate
           ).atZone(ZoneId.systemDefault()).toInstant());
           // dateTime : yyyy-MM-dd 'T' HH:mm:ss
           List<ProductComment> fetchedComments = service.findAllByUserId(id,minDateD,maxDateD);
           return ResponseEntity.ok(responseMapping(fetchedComments));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }
    private List<ResponseComment> responseMapping(List<ProductComment> fetchedComments){
        List<ResponseComment> comments = new ArrayList<>();
        for (ProductComment fetchedComment : fetchedComments)
        {
            comments.add(ResponseComment.builder()
                    .id(fetchedComment.getId())
                    .comment(fetchedComment.getComment())
                    .user_id(fetchedComment.getUser().getId())
                    .product_id(fetchedComment.getProduct().getId())
                    .commentDate(fetchedComment.getCommentDate())
                    .build());
        }
        return comments;
    }
}
