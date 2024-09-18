package dev.sparfinder.fal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sparfinder.fal.Validation.ValidEnum;
import dev.sparfinder.fal.entity.Country;
import dev.sparfinder.fal.entity.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateGymEntity {

    @NotBlank
    @Length(min = 3, max = 50)
    private String name;

    @NotNull(message = "Profile picture is required")
    private MultipartFile profilePic;

    @NotBlank
    @Length(min = 3, max = 1000)
    private String rules;

    @NotBlank
    @Length(min = 3, max = 300)
    private String location;

    @NotNull(message = "Country is required")
    @ValidEnum(enumClass = Country.class, message = "Invalid country value")
    private Country country;
}
