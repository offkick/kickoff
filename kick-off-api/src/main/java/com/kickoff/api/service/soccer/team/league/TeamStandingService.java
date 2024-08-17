package com.kickoff.api.service.soccer.team.league;

import com.kickoff.core.soccer.standing.TeamStanding;
import com.kickoff.core.soccer.standing.TeamStandingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamStandingService {
    private final TeamStandingRepository teamStandingRepository;

    @Transactional(readOnly = true)
    public List<TeamStanding> teamStandings(Long matchDay, String season)
    {
        if (matchDay == null)
        {
            TeamStanding maxRoundTeamStanding = teamStandingRepository.findBySeason(season).stream()
                    .max(Comparator.comparingLong(TeamStanding::getRound))
                    .orElseThrow(() -> new EntityNotFoundException("없는 season standing"));

            Long maxRound = maxRoundTeamStanding.getRound();
            return teamStandingRepository.findBySeasonAndRound(season, maxRound);
        }
        else
        {
            return teamStandingRepository.findBySeasonAndRound(season, matchDay);
        }
    }
}
