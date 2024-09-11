package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "boxers")
@NoArgsConstructor
@Getter
@Setter
public class Boxer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "number_of_fights")
    private Integer numberOfFights = 0;

    @Column(name = "weight_kg")
    private Integer weightKg = 0;

    @Column(name = "height_cm")
    private Integer heightCm = 0;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Stance stance;

    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(nullable = true)
    private String city;

    @Column(name = "waiting_list")
    @ManyToMany
    @JoinTable(
            name = "boxer_waitlist_events",
            joinColumns = @JoinColumn(name = "boxer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> waitingList;

    @Column(name = "invite_list")
    @ManyToMany
    @JoinTable(
            name = "boxer_invitelist_events",
            joinColumns = @JoinColumn(name = "boxer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> inviteList;

    @Column(name = "participate_events")
    @ManyToMany
    @JoinTable(
            name = "boxer_participate_events",
            joinColumns = @JoinColumn(name = "boxer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> participateList;

    public Boxer(User user, Integer numberOfFights, Integer weightKg, Integer heightCm, String name, Gender gender, LocalDate birthDate, Stance stance, String profilePicture, Level level, Country country, String city) {
        this.user = user;
        this.numberOfFights = numberOfFights;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.stance = stance;
        this.profilePicture = profilePicture;
        this.level = level;
        this.country = country;
        this.city = city;
    }

    // ADD RATING LISTS ; BOXERS RATED, EVENTS RATED, GYMS RATED, SELF RATING + NUM OF RATINGS.
}
