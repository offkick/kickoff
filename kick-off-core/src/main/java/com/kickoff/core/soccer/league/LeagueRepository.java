package com.kickoff.core.soccer.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    Optional<League> findByLeagueName(String leagueName);
    Optional<League> findByLeagueNameAndSeason(String leagueName, Season season);
}
