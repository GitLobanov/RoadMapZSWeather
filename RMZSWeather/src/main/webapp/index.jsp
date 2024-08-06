<%@ page import="main.com.weather.jg.service.AuthenticationService" %>
<%@ page import="main.com.weather.jg.model.User" %>
<%@ page import="main.com.weather.jg.model.CurrentWeatherApi" %>
<%@ page import="main.com.weather.jg.service.LocationService" %>

<%

    AuthenticationService authenticationService = new AuthenticationService();
    User user = (User) session.getAttribute("user");

    CurrentWeatherApi currentWeatherApi = null;
    if (session.getAttribute("currentWeatherApi")!=null) {
        currentWeatherApi = (CurrentWeatherApi) session.getAttribute("currentWeatherApi");
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
            if (currentWeatherApi!=null) {
                %>
                    <div class="weather-info">
                            <h2>Город: <%= currentWeatherApi.getName() %></h2>
                            <p>Погода: <%= currentWeatherApi.getMain().getTemp() + ", " + currentWeatherApi.getWeather()[0].getDescription()%></p>
                            <p>Широта: <%= currentWeatherApi.getCoord().getLat() %>° N</p>
                            <p>Долгота: <%= currentWeatherApi.getCoord().getLon() %>° E</p>
                        <%
                            if (!currentWeatherApi.isAdded()) {
                                %>
                                    <form method="post" action="?lon=<%=currentWeatherApi.getCoord().getLon()%>&lat=<%=currentWeatherApi.getCoord().getLat()%>&city=<%=currentWeatherApi.getName()%>">
                                        <input class="button-arounder" value="Добавить" type="submit">
                                    </form>
                                <%
                            } else {
                                %>
                                    <h3 style="color: #333">добавлено</h3>
                                <%
                            }
                        %>

                    </div>
                <%
            }
        %>

    </div>
</main>
<footer>
    <p>&copy; 2023 Погода в городах</p>
</footer>
</body>
</html>
