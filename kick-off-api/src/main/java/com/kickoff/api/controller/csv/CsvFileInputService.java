package com.kickoff.api.controller.csv;


import com.kickoff.domain.soccer.player.Player;
import com.kickoff.domain.soccer.player.PlayerPosition;
import com.kickoff.domain.soccer.player.PlayerRepository;
import com.kickoff.domain.soccer.team.league.LeagueTeamRepository;
import com.kickoff.domain.team.league.LeagueTeam;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.kickoff.domain.soccer.player.PlayerPosition.*;


@RequiredArgsConstructor
@Service
public class CsvFileInputService {

    private final LeagueTeamRepository leagueTeamRepository;
    private final PlayerRepository playerRepository;

    public List<String[]> readLineByLineExample() throws Exception
    {
        Path path = Paths.get(
                ClassLoader.getSystemResource("init.csv").toURI());

        return readLineByLine(path);
    }

    public List<String[]> readLineByLine(Path filePath) throws Exception
    {
        List<String[]> list = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
        }
        return list;
    }

    @Transactional
    public void init()
    {
        try {
            List<String[]> strings = readLineByLineExample();
            for (int i = 0; i < strings.size(); i++) {
                if (i == 0) {
                    continue;
                }
                String[] a = strings.get(i);
                PlayerCsvVO playerCsvVO = new PlayerCsvVO();
                playerCsvVO.setPlayerName(a[1]);
                playerCsvVO.setPosition(a[2]);
                playerCsvVO.setTeam(a[4]);
                LeagueTeam leagueTeam = leagueTeamRepository.findByLeagueTeamName(a[4]).orElseThrow();
                Player player = Player.builder()
                        .leagueTeam(leagueTeam)
                        .playerName(a[1])
                        .position(convertPosition(a[2]))
                        .build();

                playerRepository.save(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PlayerPosition convertPosition(String csvPosition) {
        return switch (csvPosition) {
            case "MID" -> MID_FIELDER;
            case "STR" -> FORWARD;
            case "DEF" -> DEFENDER;
            case "GK" -> KEEPER;
            default -> throw new RuntimeException("~");
        };
    }
}
