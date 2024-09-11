package dev.sparfinder.fal.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class CoachProfile {
    private String email;
    private String name;
    @JsonProperty("profile_pic")
    private String profilePic;
    @JsonProperty("created_at")
    private LocalDate createdAt;
}
