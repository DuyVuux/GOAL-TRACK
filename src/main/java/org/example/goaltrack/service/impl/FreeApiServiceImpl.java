package org.example.goaltrack.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import org.example.goaltrack.model.external.FreeApiResponse;
import org.example.goaltrack.model.external.League;
import org.example.goaltrack.model.external.PopularLeague;
import org.example.goaltrack.service.FreeApiService;

import java.io.IOException;
import java.util.List;

public class FreeApiServiceImpl implements FreeApiService {

    @Override
    public List<League> fetchLeague() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://free-api-live-football-data.p.rapidapi.com/football-popular-leagues")
                .get()
                .addHeader("x-rapidapi-key", "abd0b893d1msh937e78a19be7497p1a8a2ejsn53911e059c33")
                .addHeader("x-rapidapi-host", "free-api-live-football-data.p.rapidapi.com")
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        try (ResponseBody body = response.body()) {
            var leagueResp = om.readValue(body.string(), new TypeReference<FreeApiResponse<PopularLeague>>() {});
            return leagueResp.getResponse().getPopular();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
