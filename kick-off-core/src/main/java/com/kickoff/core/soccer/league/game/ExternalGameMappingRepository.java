package com.kickoff.core.soccer.league.game;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExternalGameMappingRepository extends JpaRepository<ExternalGameMapping, Long> {  
  Optional<ExternalGameMapping> findByExternalGameId(Long id);
  Optional<ExternalGameMapping> findByExternalGameIdAndGameId(Long externalId, Long gameId );

  Optional<ExternalGameMapping> findByGameId(Long gameId);
}
