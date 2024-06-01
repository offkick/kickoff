package com.kickoff.batch.jobs.matchindex;

import com.kickoff.core.soccer.team.league.LeagueTeam;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LeagueTeamDTO {
    private String teamName;

    public static LeagueTeamDTO of(LeagueTeam leagueTeam)
    {
        return new LeagueTeamDTO(leagueTeam.getLeagueTeamName());
    }
}
