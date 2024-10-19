package com.kickoff.batch.config.feign.api;

import lombok.*;
@Getter
@ToString
@AllArgsConstructor
public class Scorers {
    private final Long id;
    private final String name;
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String nationality;
    private final String section;
    private final String position = null;
    private final String shirtNumber = null;
    private final String lastUpdated;
}

