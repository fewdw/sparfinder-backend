package dev.sparfinder.fal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sparfinder.fal.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateGymEntity {

    private String name;
    @JsonProperty("profile_pic")
    private String profilePic;
    private String rules;
    private String location;
    private Country country;
    private String city;
}
