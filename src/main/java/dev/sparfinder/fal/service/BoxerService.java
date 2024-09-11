package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.AccountType;
import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.repository.UserRepository;
import dev.sparfinder.fal.response.BoxerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BoxerService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<BoxerProfile> getOwnBoxerProfile(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getAccountType() != AccountType.BOXER){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a boxer");
        }

        return ResponseEntity.ok(new BoxerProfile(
                user.getEmail(),
                user.getName(),
                user.getProfilePic(),
                user.getCreatedAt(),
                user.getBoxer().getNumberOfFights(),
                user.getBoxer().getWeightKg(),
                user.getBoxer().getHeightCm(),
                user.getBoxer().getBirthDate(),
                user.getBoxer().getStance(),
                user.getBoxer().getLevel(),
                user.getBoxer().getCountry(),
                user.getBoxer().getCity()
        ));
    }
}
