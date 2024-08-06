package main.com.weather.jg.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.com.weather.jg.model.FiveDayForecastApi;
import main.com.weather.jg.service.FiveDayForecastService;
import main.com.weather.jg.service.LocationService;

import java.io.IOException;

@WebServlet(name = "FiveDayWeatherServlet", urlPatterns = {"/fiveday"})
public class FiveDayForecastServlet extends HttpServlet {

    private FiveDayForecastService fiveDayForecastService;
    private LocationService locationService;

    @Override
    public void init() throws ServletException {
        fiveDayForecastService = new FiveDayForecastService();
        locationService = new LocationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String city = req.getParameter("city");

        if (city == null) {
            resp.sendRedirect("profile");
        } else {

            FiveDayForecastApi fiveDayForecast = fiveDayForecastService.getByCity(city);
            req.getSession().setAttribute("fiveDayForecast", fiveDayForecast);

            req.getRequestDispatcher("fiveday.jsp").forward(req, resp);
        }

    }
}
