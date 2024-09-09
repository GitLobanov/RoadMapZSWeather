<%@ page import="main.com.weather.jg.model.User" %>
<%@ page import="main.com.weather.jg.model.CurrentWeatherApi" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="main.com.weather.jg.dto.LocationDto" %>

<%

    User user = (User) session.getAttribute("user");

%>

<jsp:include page="fragments/header.jsp"/>

<main>
    <form action="" method="post" class="search-container">
        <input type="text" name="city" placeholder="Поиск города..." id="search">
        <input type="submit" value="Найти">
    </form>
    <div class="location-list">
        <%  List<LocationDto> locationDtos = null;
            if (request.getAttribute("locationDtos") != null) {
                locationDtos = (List<LocationDto>) session.getAttribute("locationDtos");
            if (locationDtos == null) return;
            Iterator iterator = locationDtos.iterator();
            while (iterator.hasNext()) {
                LocationDto locationDto = (LocationDto) iterator.next();
                %>
                <div class="weather-info">
                    <h2>Город: <%= locationDto.getAddress() %></h2>
                    <p>Погода: <%= locationDto.getDays()[0].getTemp() + "°C, " + locationDto.getDays()[0].getConditions()%></p>

                    <form method="get" action="fiveday">
                        <input style="position: absolute;visibility: hidden;" name="city" value="<%= locationDto.getAddress()%>">
                        <input class="button-arounder" value="Подробнее" type="submit">
                    </form>
                </div>
                <%
            }
        }
        %>

    </div>

    <%-- notify handler  --%>

    <%
        if (request.getAttribute("error") != null) {
            %>
                <jsp:include page="notification/error_notify.jsp"/>
            <%
        }
    %>


</main>
<footer>
    <p>&copy; 2023 Погода в городах</p>
</footer>
</body>
</html>
