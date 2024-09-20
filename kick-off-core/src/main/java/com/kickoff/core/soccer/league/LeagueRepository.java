package com.kickoff.core.soccer.league;

import com.kickoff.core.soccer.league.dto.LatestLeagueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    Optional<League> findByLeagueName(String leagueName);
    Optional<League> findByLeagueNameAndSeason(String leagueName, Season season);

    @Query("SELECT new com.kickoff.core.soccer.league.dto.LatestLeagueDTO(l.leagueName, MAX(l.leagueId) ) " +
            "from League l " +
            "where l.startDate = (select max(l2.startDate) from League l2 where l2.leagueName = l.leagueName)" +
            " GROUP BY l.leagueName")
    List<LatestLeagueDTO> findLatestLeague();
}
