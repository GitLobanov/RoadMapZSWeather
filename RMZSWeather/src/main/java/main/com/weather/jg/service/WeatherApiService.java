package main.com.weather.jg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.com.weather.jg.dto.LocationDto;
import main.com.weather.jg.util.ApiUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WeatherApiService {

    private LocationService locationService = new LocationService();

    public LocationDto getWeatherCurrentDayByCity(String city) {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(ApiUtils.getCurrentDayWeather(city)))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            LocationDto location = mapper.readValue(responseBody, LocationDto.class);

            return location;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LocationDto getWeatherWeekByCity(String city) {
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(ApiUtils.getWeekWeatherDaysHours(city)))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            LocationDto location = mapper.readValue(responseBody, LocationDto.class);

            return location;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
