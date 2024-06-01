package com.kickoff.batch.jobs.matchindex;

import com.kickoff.core.soccer.team.league.LeagueGameStatic;
import com.kickoff.core.soccer.team.league.LeagueGameStaticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@RequiredArgsConstructor
public class ItemLeagueGameWriter implements ItemWriter<LeagueGameStatic> {
    private final LeagueGameStaticRepository leagueGameStaticRepository;

    @Override
    public void write(Chunk<? extends LeagueGameStatic> chunk) throws Exception {
        leagueGameStaticRepository.saveAll(chunk);
    }
}
