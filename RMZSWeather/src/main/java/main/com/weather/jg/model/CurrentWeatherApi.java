package main.com.weather.jg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ForeignKey;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherApi {

    private Coord coord;
    @Data
    public static class Coord {
        private double lon;
        private double lat;
    }
    private Weather[] weather;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String main;
        String description;
    }
    private Main main;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        String temp;
        String feels_like;
        String temp_min;
        String temp_max;
        String pressure;
        String humidity;
    }
    private Wind wind;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        String speed;
    }
    String name;
    @JsonIgnore
    private boolean isAdded;
}
