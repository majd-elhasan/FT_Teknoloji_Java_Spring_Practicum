package dev.patika.ft_teknoloji_java_spring_practicum_case.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Size(min = 3,max = 50)
    String name;

    @Size(min = 3,max =50)
    String surname;

    @Size(min = 3,max =50)
    @Email
    String email;

    @Size(min = 7,max =15)
    String phoneNumber;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @JsonIgnore
    List<ProductComment> productComments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != 0 && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
