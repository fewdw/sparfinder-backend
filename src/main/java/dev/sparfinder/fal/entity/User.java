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
    private String id;

    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(name = "profile_pic", nullable = false)
    private String profilePic;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Boxer boxer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Coach coach;

    public User(String id, String email, String name, String profilePic, LocalDate createdAt, AccountType accountType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profilePic = profilePic;
        this.createdAt = createdAt;
        this.accountType = accountType;
    }
}
