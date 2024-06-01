package com.kickoff.batch.jobs.matchindex;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class ItemLeagueTeamWriter implements ItemWriter<LeagueTeamDTO> {

    @Override
    public void write(Chunk<? extends LeagueTeamDTO> chunk) throws Exception {
        System.out.println(chunk.getItems());
    }
}
