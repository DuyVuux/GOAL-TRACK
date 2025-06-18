package org.example.goaltrack.service;

import org.example.goaltrack.model.external.League;

import java.util.List;

public interface FreeApiService {
    List<League> fetchLeague();
}
