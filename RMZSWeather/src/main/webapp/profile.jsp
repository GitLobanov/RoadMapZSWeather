<%@ page import="main.com.weather.jg.service.AuthenticationService" %>
<%@ page import="main.com.weather.jg.model.User" %>
<%@ page import="main.com.weather.jg.model.CurrentWeatherApi" %>
<%@ page import="main.com.weather.jg.service.LocationService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%

    AuthenticationService authenticationService = new AuthenticationService();
    User user = (User) session.getAttribute("user");

    List<CurrentWeatherApi> weatherApiList = null;
    if (session.getAttribute("currentWeatherApi")!=null) {
        weatherApiList = (List<CurrentWeatherApi>) session.getAttribute("weatherApiList");
    }


%>

<jsp:include page="fragments/header.jsp"/>

<main>
    <form action="" method="post" class="search-container">
        <input type="text" name="city" placeholder="Поиск города..." id="search">
        <input type="submit" value="Найти">
    </form>
    <div class="location-list">
        <% if (weatherApiList!=null) {
            Iterator iterator = weatherApiList.iterator();
            while (iterator.hasNext()) {
                CurrentWeatherApi currentWeatherApi = (CurrentWeatherApi) iterator.next();
                %>
                <div class="weather-info">
                    <h2>Город: <%= currentWeatherApi.getName() %></h2>
                    <p>Погода: <%= currentWeatherApi.getMain().getTemp() + ", " + currentWeatherApi.getWeather()[0].getDescription()%></p>
                    <p>Широта: <%= currentWeatherApi.getCoord().getLat() %>° N</p>
                    <p>Долгота: <%= currentWeatherApi.getCoord().getLon() %>° E</p>

                    <form method="get" action="fiveday">
                        <input style="position: absolute;visibility: hidden;" name="city" value="<%= currentWeatherApi.getName()%>">
                        <input class="button-arounder" value="Подробнее" type="submit">
                    </form>
                </div>
                <%
            }
        }
        %>

    </div>
</main>
<footer>
    <p>&copy; 2023 Погода в городах</p>
</footer>
</body>
</html>
