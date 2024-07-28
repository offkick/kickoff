package com.kickoff.api.controller.soccer.player.dto;

import java.util.List;

public record AllPlayerResponse(
    List<FindPlayerApiResponse> apiResponseList
) {
}
