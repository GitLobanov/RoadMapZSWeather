package main.com.weather.jg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiveDayForecastApi {

    private List <Days> list;
    @JsonIgnore
    private String city;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Days {

        private Weather[] weather;
        private Main main;
        private Wind wind;
        private String dt_txt;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Weather {
            private String main;
            String description;
        }

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

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Wind {
            String speed;
        }


    }
}
