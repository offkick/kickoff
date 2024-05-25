package com.kickoff.core.soccer.player;

import com.kickoff.core.soccer.player.dto.FindPlayerResponse;
import com.kickoff.core.soccer.player.dto.PlayerSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepositoryCustom {
    Page<FindPlayerResponse> searchPlayer(PlayerSearchCondition condition, Pageable pageable);
}
