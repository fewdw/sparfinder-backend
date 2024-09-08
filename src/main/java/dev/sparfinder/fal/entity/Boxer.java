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
    private int numberOfFights = 0;

    @Column(name = "weight_kg", nullable = false)
    private int weightKg;

    @Column(name = "height_cm", nullable = false)
    private String heightCm;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Stance stance;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    private String country;

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

    // ADD RATING LISTS ; BOXERS RATED, EVENTS RATED, GYMS RATED, SELF RATING + NUM OF RATINGS.
}
