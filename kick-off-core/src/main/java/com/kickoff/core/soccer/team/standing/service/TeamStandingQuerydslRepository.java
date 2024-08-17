package com.kickoff.core.soccer.team.standing.service;

import com.kickoff.core.soccer.team.standing.TeamStandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamStandingQuerydslRepository {
    private final TeamStandingRepository teamStandingRepository;

}
