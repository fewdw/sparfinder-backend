package dev.sparfinder.fal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sparfinder.fal.entity.Country;
import dev.sparfinder.fal.entity.Level;
import dev.sparfinder.fal.entity.Stance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BoxerProfile {

    private String email;
    private String name;
    @JsonProperty("profile_pic")
    private String profilePic;
    @JsonProperty("created_at")
    private LocalDate createdAt;
    @JsonProperty("number_of_fights")
    private Integer numberOfFights;
    @JsonProperty("weight_kg")
    private Integer weightKg;
    @JsonProperty("height_cm")
    private Integer heightCm;
    @JsonProperty("birth_date")
    private LocalDate birthDate;
    private Stance stance;
    private Level level;
    private Country country;
    private String city;

}
