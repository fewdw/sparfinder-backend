package dev.sparfinder.fal.response;

import dev.sparfinder.fal.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoxerProfileResponse {
    private Long id;
    private String name;
    private AccountType accountType;
}
