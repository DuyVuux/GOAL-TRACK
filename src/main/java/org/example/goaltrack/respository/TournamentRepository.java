package org.example.goaltrack.respository;

import org.example.goaltrack.model.football.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
}
