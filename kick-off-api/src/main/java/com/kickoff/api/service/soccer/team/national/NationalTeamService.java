package com.kickoff.api.service.soccer.team.national;

import com.kickoff.api.service.soccer.team.national.dto.CreateNationalTeamServiceRequest;
import com.kickoff.core.soccer.team.national.NationalTeam;
import com.kickoff.core.soccer.team.national.NationalTeamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NationalTeamService {
    private final NationalTeamRepository nationalTeamRepository;

    public Long createNationalTeam(CreateNationalTeamServiceRequest request)
    {
        NationalTeam nationalTeam = NationalTeam.builder()
                .nationalTeamName(request.nationalTeamName())
                .teamType(request.teamType())
                .build();

        return nationalTeamRepository.save(nationalTeam).getNationalTeamId();
    }
}
