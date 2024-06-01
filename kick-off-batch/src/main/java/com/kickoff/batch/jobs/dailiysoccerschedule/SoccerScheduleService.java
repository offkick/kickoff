package com.kickoff.batch.jobs.dailiysoccerschedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SoccerScheduleService {
    private final SoccerScheduleFetchService soccerScheduleFetchService;

    @Transactional
    public void competitionInsert(String target)
    {
        soccerScheduleFetchService.competitionInsert(target);
    }

    @Transactional
    public void insertCLMatches()
    {
        soccerScheduleFetchService.createCLSchedules();
    }
}