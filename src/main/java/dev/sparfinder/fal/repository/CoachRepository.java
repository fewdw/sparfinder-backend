package dev.sparfinder.fal.repository;

import dev.sparfinder.fal.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    Optional<Coach> findByUserId(String userId);
}
