package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coaches")
@NoArgsConstructor
@Getter
@Setter
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym = null;

    @Column(name = "is_owner", nullable = true)
    private boolean isOwner = false;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profilePic;

    public Coach(User user, String name, String profilePic) {
        this.user = user;
        this.name = name;
        this.profilePic = profilePic;
    }
}
