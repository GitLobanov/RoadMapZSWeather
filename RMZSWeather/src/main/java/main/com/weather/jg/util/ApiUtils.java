package main.com.weather.jg.util;

import main.com.weather.jg.keystores.ExternalApi;
import main.com.weather.jg.model.Location;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApiUtils {

    public static String getCurrentDayWeather(String city) {
        return getCustomURI(city, 0, "days");
    }

    public static String getWeekWeatherDaysHours(String city) {
        return getCustomURI(city, 7, "days,hours");
    }


    private static String getCustomURI (String city, int rangeDays, String include) {
        String beginDay = LocalDate.now().minusDays(rangeDays).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StringBuilder URI = new StringBuilder();

        URI.append("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/");
        URI.append(city); URI.append("/");
        URI.append(beginDay); URI.append("/");
        URI.append(endDay);
        URI.append("?key="); URI.append(ExternalApi.getExternalApiKey());
        URI.append("&include="); URI.append(include);
        URI.append("&elements=datetime,temp,windspeed,conditions,icon,pressure,humidity");
        URI.append("&unitGroup=metric");
        URI.append("&lang=ru");

        return URI.toString();
    }

}
