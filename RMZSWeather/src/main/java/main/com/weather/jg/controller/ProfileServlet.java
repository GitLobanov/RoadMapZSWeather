package main.com.weather.jg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.com.weather.jg.dto.LocationDto;
import main.com.weather.jg.model.Location;
import main.com.weather.jg.service.CurrentWeatherService;
import main.com.weather.jg.service.LocationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    CurrentWeatherService currentWeatherService;
    LocationService locationService;

    @Override
    public void init() throws ServletException {
        currentWeatherService = new CurrentWeatherService();
        locationService = new LocationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Location> locations = locationService.findAllLocations();
        List<LocationDto> locationDtos = new ArrayList<>();

        Iterator<Location> iterator = locations.iterator();

        try {
            while (iterator.hasNext()) {
                Location location = iterator.next();
                LocationDto locationDto = currentWeatherService.getByCity(location.getName());
                locationDtos.add(locationDto);
            }
        } catch (Exception e){
            req.setAttribute("error", e.getMessage());
        }


        if (!locationDtos.isEmpty()) {
            req.setAttribute("locationDtos", locationDtos);
        }

        req.getRequestDispatcher("profile.jsp").forward(req, resp);


    }


}
