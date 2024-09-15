package com.kickoff.core.soccer.league.service;

import com.kickoff.core.soccer.league.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

}
