<%@ page import="java.util.Iterator" %>
<%@ page import="main.com.weather.jg.dto.LocationDto" %>

<%

    LocationDto locationDto = null;
    if (request.getAttribute("locationDto") != null) {
        locationDto = (LocationDto) request.getAttribute("locationDto");
    }


%>

<jsp:include page="fragments/header.jsp"/>

<main>
    <form action="" method="post" class="search-container">
        <input type="text" name="city" placeholder="Поиск города..." id="search">
        <input class="button-arounder" type="submit" value="Найти">
    </form>
    <div class="weather-detailed">
        <%
            if (locationDto != null) {
                Iterator iteratorDays = locationDto.getDays().iterator();
            %>

            <h3><%=locationDto.getAddress()%>
            </h3>

            <%
                while (iteratorDays.hasNext()) {
                    LocationDto.Days days = (LocationDto.Days) iteratorDays.next();
            %>
            <div class="weather-info-detailed">
                <p> <%= days.getDatetime()%>
                </p>
                <p>Сейчас: <%= days.getTemp() + ", " + days.getConditions()%>
                </p>

                <div class="weather-hours">
                    <%
                        if (days.getHours() == null) continue;
                        Iterator iteratorHours = days.getHours().iterator();
                        while (iteratorHours.hasNext()) {
                            LocationDto.Days.Hours hour = (LocationDto.Days.Hours) iteratorHours.next();
                    %>
                    <div class="weather-info-hours">
                        <p><%= hour.getDatetime()%>
                        </p>
                        <p><%= hour.getTemp() + ", " + hour.getConditions()%>
                        </p>
                    </div>
                    <%
                                }
                        %>
                </div>
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
