package com.kickoff.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StringUtils {
    YN("YN"),
    Y("Y"),
    N("N")
    ;
    private final String message;

}
