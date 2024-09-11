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
public class UserInfoResponse {
    private String id;
    private String email;
    private String name;
    private String profilePic;
    private AccountType accountType;
}
