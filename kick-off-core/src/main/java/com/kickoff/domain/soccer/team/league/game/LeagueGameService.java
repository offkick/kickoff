package com.kickoff.domain.soccer.team.league.game;

import com.kickoff.domain.soccer.team.league.League;
import com.kickoff.domain.soccer.team.league.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LeagueGameService {

    private final LeagueGameRepository leagueGameRepository;

    public void save(LeagueGame leagueGame)
    {
        leagueGameRepository.save(leagueGame);
    }

    public List<LeagueGame> findByLeagueNameAndSeason(League leagueGame, Season season)
    {
        return leagueGameRepository.findAllByLeagueAndSeason(leagueGame, season);
    }

    public void deleteAll(List<LeagueGame> leagueGames)
    {
        leagueGameRepository.deleteAll(leagueGames);
    }
}
