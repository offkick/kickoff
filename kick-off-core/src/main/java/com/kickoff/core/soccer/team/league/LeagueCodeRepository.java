package com.kickoff.core.soccer.team.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueCodeRepository extends JpaRepository<LeagueCode, Long> {
}
