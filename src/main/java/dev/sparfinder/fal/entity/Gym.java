package dev.sparfinder.fal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "gyms")
@NoArgsConstructor
@Getter
@Setter
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "gym")
    private List<Coach> coaches;

    @OneToMany(mappedBy = "gym")
    private List<Boxer> boxers;

    @OneToMany(mappedBy = "gym")
    private List<Event> events;

    @Column(name = "profile_pic", nullable = false)
    private String profilePic;

    private String rules;

    private String location;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String city;

}
