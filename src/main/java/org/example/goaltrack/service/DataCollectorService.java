package org.example.goaltrack.service;

import org.example.goaltrack.model.external.League;
import org.example.goaltrack.model.football.Tournament;
import org.example.goaltrack.respository.TournamentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DataCollectorService {
    Runnable job;
    FreeApiService dataApiService;
    volatile DataFrame df = new DataFrame();
    TournamentRepository repository;

    @Scheduled(fixedDelay = 600L, timeUnit = TimeUnit.SECONDS)
    void collect() {
        df.leagueList = dataApiService.fetchLeague();
        index();
    }

    void index() {
        List<Tournament> tournament = new ArrayList<>();
        df.leagueList.forEach(l -> tournament.add(convertLeagueToTournament(l)));
        repository.saveAll(tournament);
    }

    static Tournament convertLeagueToTournament(League league) {
        Tournament t = new Tournament();
        t.setId(league.getId());
        t.setName(league.getName());
        return t;
    }

    static class DataFrame {
        List<League> leagueList;
    }
}
