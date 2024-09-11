package dev.sparfinder.fal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sparfinder.fal.entity.Gender;
import dev.sparfinder.fal.entity.Level;
import dev.sparfinder.fal.entity.Stance;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoxerProfileRequest {

    @JsonProperty("number_of_fights")
    @NotEmpty(message = "Number of fights is required")
    @Size(min = 0, max = 1000, message = "Number of fights must be between 1 and 1000")
    private int numberOfFights;

    @JsonProperty("weight_kg")
    private Integer weightKg = 0;

    @JsonProperty("height_cm")
    private Integer heightCm = 0;

    private Gender gender;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    private Stance stance;

    @JsonProperty("profile_picture")

    private String profilePicture;

    private Level level;

    private String country;

    private String city;

}
