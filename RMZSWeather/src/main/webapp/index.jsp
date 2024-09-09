<%@ page import="main.com.weather.jg.service.AuthenticationService" %>
<%@ page import="main.com.weather.jg.model.User" %>
<%@ page import="main.com.weather.jg.service.LocationService" %>
<%@ page import="main.com.weather.jg.dto.LocationDto" %>

<%

    LocationDto locationDto = null;

%>

<jsp:include page="fragments/header.jsp"/>

<main>
    <form action="" method="get" class="search-container">
        <input type="text" name="city" placeholder="Поиск города..." id="search">
        <input class="button-arounder" type="submit" value="Найти">
    </form>
    <div class="location-list">
        <%
            if (session.getAttribute("locationDto")!=null) {
                locationDto = (LocationDto) session.getAttribute("locationDto");
                if (locationDto==null) {
                    return;
                }
                %>
                    <div class="weather-info">
                            <h2>Город: <%= locationDto.getAddress() %></h2>
                            <p>Погода: <%= locationDto.getDays()[0].getTemp() + "°C, " + locationDto.getDays()[0].getConditions()%></p>
                        <%
                            if (!locationDto.isAdded()) {
                                %>
                                    <form method="post" action="?city=<%=locationDto.getAddress()%>">
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
    <div class="wrapper">
        <div class="content"> 2023 Погода в городах </div>
    </div>
</footer>
</body>
</html>
