package com.kickoff.core.soccer.team.league.service;

import com.kickoff.core.soccer.team.league.SeasonRepository;
import com.kickoff.core.soccer.team.league.service.dto.SeasonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Transactional(readOnly = true)
    public List<SeasonDTO> findLatestSeason()
    {
        // TODO PL, LALIGA .. 모든 리그의 현재 최신 리그 정보를 조회한다..
        return null;
    }
}
