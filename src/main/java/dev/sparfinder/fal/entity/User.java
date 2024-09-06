package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String name;

    private String picture;

    @Column(name = "created_at")
    private LocalDate createdAt;

    private AccountType accountType;

    public User(String email, String name, String picture, LocalDate createdAt) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.createdAt = createdAt;
    }
}
