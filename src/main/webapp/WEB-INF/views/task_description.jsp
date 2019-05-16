<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <title>CRM</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="">CRM</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="../index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../projects">Projekty</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../users">Użytkownicy</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../statuses">Statusy</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../priorities">Priorytety</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../logout">Wyloguj</a>
            </li>
        </ul>
    </div>
</nav>
<!-- HEADER END -->

<div class="row m-5">
    <div class="col-12">
        <h1>Szczegóły zadania</h1>
    </div>
    <div class="col-12">
        <table class="table">
            <tbody>
            <tr>
                <td>Temat</td>
                <td><b>${task.topic}</b></td>
            </tr>
            <tr>
                <td>Opis</td>
                <td><b>${task.describes}</b></td>
            </tr>
            <tr>
                <td>Data utworzenia</td>
                <td><b>${task.created}</b></td>
            </tr>
            <tr>
                <td>Priorytet</td>
                <td><b>${task.priority.name}</b></td>
            </tr>
            <tr>
                <td>Przydzielony użytkownik</td>
                <td><b>${task.project.user.login}</b></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<!-- FOOTER START -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>