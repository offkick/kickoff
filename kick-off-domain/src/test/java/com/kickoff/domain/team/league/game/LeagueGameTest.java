package com.kickoff.domain.team.league.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class LeagueGameTest {

    @DisplayName("키퍼는 1명이어야 한다.")
    @Test
    public void keeperOne(){

        Assertions.assertThatThrownBy(() -> LeagueGame.builder()

                        .build())
                .hasMessage("키퍼가 1명 아님")
                .isInstanceOf(IllegalArgumentException.class);
    }
}