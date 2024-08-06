<%@ page import="main.com.weather.jg.service.AuthenticationService" %>
<%@ page import="main.com.weather.jg.model.User" %>
<%@ page import="main.com.weather.jg.model.CurrentWeatherApi" %>
<%@ page import="main.com.weather.jg.service.LocationService" %>
<%@ page import="main.com.weather.jg.model.FiveDayForecastApi" %>
<%@ page import="java.util.Iterator" %>

<%

    AuthenticationService authenticationService = new AuthenticationService();
    User user = (User) session.getAttribute("user");

    FiveDayForecastApi fiveDayForecast = null;
    if (session.getAttribute("fiveDayForecast")!=null) {
        fiveDayForecast = (FiveDayForecastApi) session.getAttribute("fiveDayForecast");
    }


%>

<jsp:include page="fragments/header.jsp"/>

<main>
    <form action="" method="post" class="search-container">
        <input type="text" name="city" placeholder="Поиск города..." id="search">
        <input class="button-arounder" type="submit" value="Найти">
    </form>
    <div class="location-list">
        <%
            if (fiveDayForecast!=null) {
                Iterator iterator = fiveDayForecast.getList().iterator();
        %>

        <h3>Город: <%=fiveDayForecast.getCity()%></h3>

        <%
                while (iterator.hasNext()) {
                    FiveDayForecastApi.Days days = (FiveDayForecastApi.Days) iterator.next();
                    %>
                    <div class="weather-info">
                        <p>День и время: <%= days.getDt_txt()%></p>
                        <p>Погода: <%= days.getMain().getTemp() + ", " + days.getWeather()[0].getDescription()%></p>
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
