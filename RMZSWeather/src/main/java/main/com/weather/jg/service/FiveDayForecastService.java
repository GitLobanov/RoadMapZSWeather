package main.com.weather.jg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.com.weather.jg.model.FiveDayForecastApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FiveDayForecastService {

    private static String API = "&appid=ae92c5ab6534cc98bc90100a213832ac";
    private static String UNITS = "&units=metric";
    private static String LANG = "&lang=ru";
    private static String URL = "http://api.openweathermap.org/data/2.5/forecast?q=";


    public FiveDayForecastApi getByCity (String city) {
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
            FiveDayForecastApi location = mapper.readValue(responseBody, FiveDayForecastApi.class);
            location.setCity(city);


            return location;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
