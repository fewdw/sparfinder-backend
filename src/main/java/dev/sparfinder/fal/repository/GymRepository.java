package dev.sparfinder.fal.repository;

import dev.sparfinder.fal.entity.Gym;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

    Optional<Gym> findByNameAndAddress(String name, @NotBlank @Length(min = 3, max = 300) String location);
}
