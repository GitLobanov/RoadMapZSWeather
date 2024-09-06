package main.com.weather.jg.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.com.weather.jg.keystores.ExternalApi;
import main.com.weather.jg.model.CurrentWeatherApi;
import main.com.weather.jg.model.Location;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class CurrentWeatherService {

    private static String API;
    private static String UNITS = "&units=metric";
    private static String LANG = "&lang=ru";
    private static String URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public CurrentWeatherService() {
        API = "&appid=" + ExternalApi.getExternalApiKey();
    }


    public CurrentWeatherApi getByCity (String city) {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(URL+city+UNITS+LANG+API))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            CurrentWeatherApi location = mapper.readValue(responseBody, CurrentWeatherApi.class);

            return location;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
