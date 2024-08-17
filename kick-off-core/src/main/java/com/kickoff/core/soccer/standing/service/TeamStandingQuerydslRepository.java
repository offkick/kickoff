package com.kickoff.core.soccer.standing.service;

import com.kickoff.core.soccer.standing.TeamStandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamStandingQuerydslRepository {
    private final TeamStandingRepository teamStandingRepository;

}
