<%--
  Created by IntelliJ IDEA.
  User: hylio
  Date: 23.10.2018
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap4/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/style.css">
</head>
<body>
<%--<jsp:useBean id="category" scope="request" class="com.web.securedApp.model.domain.product.Category"/>--%>
<div class="container">
    <%-- HEADER--%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="controller?command=Home">
            <img src="../../resources/images/vitrina.jpg" width="30" height="30" class="d-inline-block align-top"
                 alt=""> MYSTORE
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="controller?command=Home">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Blog</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Dropdown
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <!-- if(session!=null) hide -->
                <li class="nav-item">
                    <a class="nav-link " href="#">Contact Us</a>
                </li>
            </ul>
            <div class="form-inline my-2 my-lg-0">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="login">
                    <button class="btn btn-outline-success mr-5 btn-sm" type="submit">Log In</button>
                </form>
                <%--<c:if test="${sessionScope.user != null}">--%>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="logout">
                    <button class="btn btn-outline-danger mr-5 btn-sm" type="submit">Log Out</button>
                </form>
                <%--</c:if>--%>

                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>

            </div>
        </div>
    </nav>
    <%-- HEADER--%>

    <%--LEFT PANEL--%>

    <%--<div class="list-group col-2 mt-3 category-aligment">--%>
    <div class="btn-group-vertical col-2 mt-3 category-aligment" role="group" aria-label="Basic example">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="home">
            <button type="submit" class="btn btn-secondary">
                All products
            </button>
        </form>

        <c:forEach var="category" items="${categories}" varStatus="i">
            <form action="controller" method="post">
                <button type="submit" class="btn btn-secondary">
                    <c:out value="${category.name}"/>
                </button>
                <input type="hidden" name="command" value="category">
                <input type="hidden" name="categoryId" value="${category.id}">
            </form>

            <%--<a href="controller" name="command" class="list-group-item list-group-item-action">--%>
            <%--<c:out value="${cat.name}"/>--%>
            <%--</a>--%>
        </c:forEach>
    </div>

    <%--LEFT PANEL--%>

    <%--PRODUCTS--%>
    <c:forEach var="product" items="${products}" varStatus="i">
        <div class="card col-4 card-alignment mt-3 ml-3 mr-3">
            <img class="card-img-top img-fluid picture-aligment" src=../../resources/images/product.jpg
                 alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${product.name}</h5>
                <p class="card-text">${product.description}</p>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${product.cost}$</li>
                </ul>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="showproduct">
                    <input type="hidden" name="productId" value="${product.id}">
                    <button class="btn btn-primary">more</button>
                </form>
            </div>
        </div>
    </c:forEach>

    <%--PRODUCTS--%>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/style/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap4/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap4/js/main.js"></script>
</body>
</html>
