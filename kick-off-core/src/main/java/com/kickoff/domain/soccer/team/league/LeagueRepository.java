package com.kickoff.domain.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Long> {
    Optional<League> findByLeagueNameLike(String leagueName);
}
