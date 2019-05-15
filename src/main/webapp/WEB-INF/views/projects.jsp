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
                <a class="nav-link" href="statusy.html">Statusy</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="priorytety.html">Priorytety</a>
            </li>
        </ul>
    </div>
</nav>
<!-- HEADER END -->

<div class="row m-5">
    <div class="col-8">
        <h1>Lista projektów</h1>
    </div>
    <div class="col-4 text-right">
        <a href="projects_add_new" class="btn btn-primary">Dodaj nowy projekt</a>
    </div>
    <div class="clearfix"></div>
    <table class="table">
        <thead>
        <tr>
            <th>Data utworzenia</th>
            <th>Nazwa</th>
            <th>Identyfikator</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projects}" var="project">
            <tr>
                <td>${project.created}</td>
                <td>${project.name}</td>
                <td>${project.ident}</td>
                <td>
                    <a href="<c:url value="project_task">
                        <c:param name="project_id" value="${project.id}"/>
                    </c:url>">Zadania</a>
                    /
                    <a href="<c:url value="project_description">
                        <c:param name="project_id" value="${project.id}"/>
                    </c:url>">Szczegoly</a>
                    /
                    <a href="<c:url value="project_edition">
                        <c:param name="project_id" value="${project.id}"/>
                    </c:url>">Edycja</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
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