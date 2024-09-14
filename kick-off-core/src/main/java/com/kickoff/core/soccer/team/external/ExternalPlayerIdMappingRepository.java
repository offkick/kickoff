package com.kickoff.core.soccer.team.external;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalPlayerIdMappingRepository extends JpaRepository<ExternalPlayerIdMapping, Long> {
    Optional<ExternalPlayerIdMapping> findByExternalPlayerId(Long id);
}
