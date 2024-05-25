package com.kickoff.core.soccer.team.league.game;

import com.kickoff.core.soccer.player.Player;
import com.kickoff.core.soccer.player.dto.PlayerSearchCondition;
import com.kickoff.core.soccer.team.league.game.dto.FindLeagueGameResponse;
import com.kickoff.core.soccer.team.league.game.dto.GameSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueGameRepositoryCustom {
    Page<FindLeagueGameResponse> searchGame(GameSearchCondition condition, Pageable pageable);
}
