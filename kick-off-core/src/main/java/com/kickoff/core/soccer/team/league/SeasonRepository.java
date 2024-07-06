package com.kickoff.core.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season,Long> {
    Optional<Season> findByYear(String year);
}
