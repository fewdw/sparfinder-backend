package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birthDate;

    private LocalTime startTime;

    private int duration;

    private String name;

    private String description;

    private String location;

    private boolean isPrivate;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    private int maxParticipants;

    @ManyToMany(mappedBy = "participateList")
    private Set<Boxer> participantList;

    @ManyToMany(mappedBy = "waitingList")
    private Set<Boxer> waitingList;

    @ManyToMany(mappedBy = "inviteList")
    private Set<Boxer> invitedList;




    // add rating system :D
}
