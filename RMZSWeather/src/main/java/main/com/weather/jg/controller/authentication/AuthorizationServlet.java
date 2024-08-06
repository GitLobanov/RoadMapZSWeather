package main.com.weather.jg.controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.com.weather.jg.model.User;
import main.com.weather.jg.service.AuthenticationService;

import java.io.IOException;

@WebServlet(name = "AuthorizationServlet", urlPatterns = "/auth")
public class AuthorizationServlet extends HttpServlet {

    public AuthenticationService authenticationService;

    @Override
    public void init() throws ServletException {
        authenticationService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("authorization.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");


        User user = authenticationService.login(login, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/weather/");
        } else {
            req.getRequestDispatcher("authorization.jsp").forward(req, resp);
        }

    }
}
