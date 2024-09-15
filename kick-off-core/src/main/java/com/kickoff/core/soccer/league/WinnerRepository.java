package com.kickoff.core.soccer.league;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
