package dev.sparfinder.fal.repository;

import dev.sparfinder.fal.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
