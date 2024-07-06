package com.kickoff.batch.jobs.daily;

import com.kickoff.batch.config.feign.SoccerApiFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoccerApiClient {
    private final SoccerApiFeign soccerApiFeign;

}
