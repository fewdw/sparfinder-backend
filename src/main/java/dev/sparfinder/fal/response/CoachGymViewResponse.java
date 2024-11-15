package dev.sparfinder.fal.response;

import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CoachGymViewResponse {

    private Long id;
    private String name;
    private String profilePic;
    private String rules;
    private String address;
    private Country country;
}
