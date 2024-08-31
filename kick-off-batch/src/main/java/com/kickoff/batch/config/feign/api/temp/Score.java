package com.kickoff.batch.config.feign.api.temp;

import com.kickoff.batch.config.feign.api.MatchesResultDetailResponse;

public record Score(
        String winner,
        String duration,
        FullTime fullTime,
        HalfTime halfTime
) {
    public record FullTime(String home, String away) {}
    public record HalfTime(int home, int away) {}
}
