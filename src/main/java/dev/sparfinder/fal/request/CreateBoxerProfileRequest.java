package dev.sparfinder.fal.request;

import dev.sparfinder.fal.entity.Gender;
import dev.sparfinder.fal.entity.Level;
import dev.sparfinder.fal.entity.Stance;
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
    private int numberOfFights;
    private Integer weightKg = 0;
    private Integer heightCm = 0;
    private Gender gender;
    private LocalDate birthDate;
    private Stance stance;
    private Level level;
    private String country;
    private String city;
}
