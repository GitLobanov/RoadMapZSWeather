package main.com.weather.jg.util;

import main.com.weather.jg.keystores.ExternalApi;
import main.com.weather.jg.model.Location;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApiUtils {

    public static String getCurrentDayWeather(String city) {

        String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StringBuilder URI = new StringBuilder();

        URI.append("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/");
        URI.append(city);
        URI.append("/");
        URI.append(currentDay);
        URI.append("?key=" + ExternalApi.getExternalApiKey());
        URI.append("&include=days");
        URI.append("&elements=datetime,temp,windspeed,conditions,icon,pressure,humidity");
        URI.append("&unitGroup=metric");
        URI.append("&lang=ru");

        return URI.toString();
    }

}
