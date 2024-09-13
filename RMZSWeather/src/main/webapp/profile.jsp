<%@ page import="main.com.weather.jg.model.User" %>
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
                locationDtos = (List<LocationDto>) request.getAttribute("locationDtos");
            if (locationDtos == null) return;
            Iterator iterator = locationDtos.iterator();
            while (iterator.hasNext()) {
                LocationDto locationDto = (LocationDto) iterator.next();
                %>
                <div class="weather-info">
                    <div class="weather-info-left">
                        <h2> <%= locationDto.getAddress() %></h2>
                        <p><%= locationDto.getDays().get(0).getTemp() + "°C, " + locationDto.getDays().get(0).getConditions()%></p>

                        <form method="get" action="fiveday">
                            <input style="position: absolute;visibility: hidden;" name="city" value="<%= locationDto.getAddress()%>">
                            <input class="button-arounder" value="Подробнее" type="submit">
                        </form>
                    </div>

                    <div class="weather-info-right">
                        <p>Скорость ветра: <%= locationDto.getDays().get(0).getWindspeed()%></p>
                        <p>Давление: <%= locationDto.getDays().get(0).getPressure()%></p>
                        <p>Влажность: <%= locationDto.getDays().get(0).getHumidity()%></p>
                        <img src="weather-icon/<%= locationDto.getDays().get(0).getIcon()%>.svg">
                    </div>

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
