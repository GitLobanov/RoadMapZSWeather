package main.com.weather.jg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.com.weather.jg.dto.LocationDto;
import main.com.weather.jg.model.Location;
import main.com.weather.jg.model.User;
import main.com.weather.jg.service.WeatherApiService;
import main.com.weather.jg.service.LocationService;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;

@WebServlet(name = "MainPageServlet", urlPatterns = {"/","/main"})
public class MainPageServlet extends HttpServlet {

    WeatherApiService weatherApiService;
    LocationService locationService;

    @Override
    public void init() throws ServletException {
        weatherApiService = new WeatherApiService();
        locationService = new LocationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user")==null) {
            resp.sendRedirect("/weather/auth");
            return;
        }

        String city = req.getParameter("city");
        if (city!=null){
            LocationDto locationDto = weatherApiService.getWeatherCurrentDayByCity(city);
            req.getSession().setAttribute("locationDto", locationDto);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String city = req.getParameter("city");
        User user = (User) req.getSession().getAttribute("user");

        if (city != null) {
            Location location = new Location();
            location.setName(city);
            location.setUserFK(user);

            try {
                locationService.add(location);
            } catch (ConstraintViolationException e) {
                req.setAttribute("error", "Вы уже добавили локацию");
            }

        }

        resp.sendRedirect("/weather/");
    }
}
