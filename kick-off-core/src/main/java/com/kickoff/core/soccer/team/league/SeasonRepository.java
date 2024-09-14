package com.kickoff.core.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    Optional<Season> findByYears(String years);

    @Query("SELECT MAX(s.years) FROM Season s GROUP BY s.years ORDER BY s.years DESC")
    Long findMaxYears();
}
