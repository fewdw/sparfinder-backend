package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String location;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    private int maxParticipants;

    @ManyToMany(mappedBy = "participateList")
    private List<Boxer> participantList;

    @ManyToMany(mappedBy = "waitingList")
    private List<Boxer> waitingList;

    @ManyToMany(mappedBy = "inviteList")
    private List<Boxer> invitedList;




    // add rating system :D
}
