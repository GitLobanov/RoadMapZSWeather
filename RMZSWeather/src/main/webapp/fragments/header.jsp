<%@ page import="main.com.weather.jg.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
%>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Погода в городах</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/button_arounder.css">
    <link rel="stylesheet" href="css/style_notification.css">
</head>
<body>

<header>
    <h1>Погода в городах</h1>
    <nav>
        <ul>
            <li><a href="main">Главная</a></li>
            <li><a href="profile">Профиль</a></li>
            <li><a href="auth/logout">Выход</a></li>
            <li><b><%= user.getLogin() %></b></li>
        </ul>
    </nav>
</header>
