package com.kickoff.core.soccer.team.external;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalTeamIdMappingRepository extends JpaRepository<ExternalTeamIdMapping, String> {
    Optional<ExternalTeamIdMapping> findByExternalTeamId(Long id);
    Optional<ExternalTeamIdMapping> findByExternalTeamIdAndTeamIdAndExternalApiName(Long externalId, Long teamId, ExternalApiName externalApiName);
}
