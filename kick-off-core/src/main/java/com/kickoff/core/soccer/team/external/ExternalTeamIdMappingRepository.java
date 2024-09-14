package com.kickoff.core.soccer.team.external;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExternalTeamIdMappingRepository extends JpaRepository<ExternalTeamIdMapping, String> {
    Optional<ExternalTeamIdMapping> findByExternalTeamId(Long id);

    @Query("select teamId from ExternalTeamIdMapping where externalTeamId = :externalTeamId")
    Optional<Long> findTeamIdByExternalTeamId(@Param("externalTeamId") Long externalTeamId);

    Optional<ExternalTeamIdMapping> findByExternalTeamIdAndSeason(Long id, String season);
    Optional<ExternalTeamIdMapping> findByExternalTeamIdAndTeamIdAndExternalApiName(Long externalId, Long teamId, ExternalApiName externalApiName);
}
