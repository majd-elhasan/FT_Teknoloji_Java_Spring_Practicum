package dev.patika.ft_teknoloji_java_spring_practicum_case.repository;

import dev.patika.ft_teknoloji_java_spring_practicum_case.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);
}
