package dev.patika.ft_teknoloji_java_spring_practicum_case.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseComment {
    long id ;
    String comment;
    Date commentDate;
    long product_id;
    long user_id;
}
