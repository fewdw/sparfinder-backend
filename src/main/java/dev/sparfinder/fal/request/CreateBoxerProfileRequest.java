package dev.sparfinder.fal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sparfinder.fal.Validation.ValidEnum;
import dev.sparfinder.fal.entity.Country;
import dev.sparfinder.fal.entity.Gender;
import dev.sparfinder.fal.entity.Level;
import dev.sparfinder.fal.entity.Stance;
import jakarta.validation.constraints.*;
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
    @Min(value = 1, message = "Number of fights must be at least 1")
    @Max(value = 100, message = "Number of fights must be at most 100")
    private Integer numberOfFights;

    @JsonProperty("weight_kg")
    @Min(value = 0, message = "Weight must be at least 0")
    @Max(value = 300, message = "Weight must be at most 300")
    private int weightKg = 0;

    @JsonProperty("height_cm")
    @Min(value = 1, message = "Height must be a positive number")
    @Max(value = 300, message = "Height must be at most 300")
    private int heightCm = 0;

    @ValidEnum(enumClass = Gender.class, message = "Invalid stance value")
    private Gender gender;

    @JsonProperty("birth_date")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @ValidEnum(enumClass = Stance.class, message = "Invalid stance value")
    private Stance stance;

    @ValidEnum(enumClass = Level.class, message = "Invalid stance value")
    private Level level;

    @ValidEnum(enumClass = Country.class, message = "Invalid stance value")
    private Country country;

    @NotEmpty
    private String city;

}
