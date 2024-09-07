package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    private int numberOfFights;

    private int weightKg;

    private String heightCm;

    private String name;

    private Gender gender;

    private LocalDate birthDate;

    private Stance stance;

    private String profilePicture;

    private Level level;

    @ManyToOne
    @JoinColumn("gym_id")
    private Gym gym;

    private String country;

    private String city;

    @ManyToMany
    @JoinTable(
            name = "boxer_waitlist_events",
            joinColumns = @JoinColumn(name = "boxer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> waitingList;

    @ManyToMany
    @JoinTable(
            name = "boxer_invitelist_events",
            joinColumns = @JoinColumn(name = "boxer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> inviteList;

    @ManyToMany
    @JoinTable(
            name = "boxer_participate_events",
            joinColumns = @JoinColumn(name = "boxer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> participateList;

    // ADD RATING LISTS ; BOXERS RATED, EVENTS RATED, GYMS RATED, SELF RATING + NUM OF RATINGS.
}
