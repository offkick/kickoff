package com.kickoff.domain.soccer.player;

import com.kickoff.domain.soccer.player.dto.PlayerSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepositoryCustom {
    Page<Player> searchPage(PlayerSearchCondition condition, Pageable pageable);
}
