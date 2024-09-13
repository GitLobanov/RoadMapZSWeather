package main.com.weather.jg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.com.weather.jg.dto.LocationDto;
import main.com.weather.jg.service.LocationService;
import main.com.weather.jg.service.WeatherApiService;

import java.io.IOException;

@WebServlet(name = "FiveDayWeatherServlet", urlPatterns = {"/fiveday"})
public class DetailedWeatherServlet extends HttpServlet {

    private WeatherApiService weatherApiService;
    private LocationService locationService;

    @Override
    public void init() throws ServletException {
        weatherApiService = new WeatherApiService();
        locationService = new LocationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String city = req.getParameter("city");

        if (city == null) {
            resp.sendRedirect("profile");
        } else {

            LocationDto locationDto = weatherApiService.getWeatherWeekByCity(city);
            req.setAttribute("locationDto", locationDto);

            req.getRequestDispatcher("city_detailed_weather.jsp").forward(req, resp);
        }

    }
}
