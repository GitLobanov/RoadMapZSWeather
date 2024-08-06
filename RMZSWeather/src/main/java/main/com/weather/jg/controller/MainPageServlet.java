package main.com.weather.jg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.com.weather.jg.model.CurrentWeatherApi;
import main.com.weather.jg.model.Location;
import main.com.weather.jg.model.User;
import main.com.weather.jg.service.CurrentWeatherService;
import main.com.weather.jg.service.LocationService;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "MainPageServlet", urlPatterns = "/")
public class MainPageServlet extends HttpServlet {

    CurrentWeatherService currentWeatherService;
    LocationService locationService;

    @Override
    public void init() throws ServletException {
        currentWeatherService = new CurrentWeatherService();
        locationService = new LocationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user")==null) {
            resp.sendRedirect("/weather/auth");
        } else{
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String city = req.getParameter("city");
        String lon = req.getParameter("lon");
        String lat = req.getParameter("lat");


        CurrentWeatherApi currentWeatherApi = currentWeatherService.getByCity(city);
        User user = (User) req.getSession().getAttribute("user");

        if (lon !=null && lat !=null && city != null) {
            Location location = new Location();
            location.setName(city);
            location.setLatitude(new BigDecimal(lat));
            location.setLongitude(new BigDecimal(lon));
            location.setUserFK(user);

            try {
                locationService.add(location);
            } catch (ConstraintViolationException e) {
                req.setAttribute("error", "Вы уже добавили локацию");
            }

        }


        if (currentWeatherApi==null) {

        } else {
            currentWeatherApi.setAdded(locationService.isHaveByCityAndUser(currentWeatherApi.getName(),user));
        }


        req.getSession().setAttribute("currentWeatherApi", currentWeatherApi);
        resp.sendRedirect("/weather/");
    }
}
