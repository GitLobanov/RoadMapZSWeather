package main.com.weather.jg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {

    private String address;
    private Days[] days;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Days {

        String datetime;
        String temp;
        String windspeed;
        String humidity;
        String pressure;
        String conditions;
        String icon;

        Hours[] hours;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Hours {

            String datetime;
            String temp;
            String windspeed;
            String humidity;
            String pressure;
            String conditions;
            String icon;

        }

    }

    boolean isAdded;



}
