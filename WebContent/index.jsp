<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ogloszenia.repository.AdvertisementRepository" %>
<!-- importujemy to c opotrzebujemy, nie trzeba servletow -->

<%
    pageContext.setAttribute("randomAdList", AdvertisementRepository.findRandomThreeAd());
%>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>Serwis z ogloszeniami</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="function.js"></script>
</head>
<body>

<div class="container header">
    <c:import url="top-menu.jsp"></c:import>
</div>

<div class="container">
    <div>
        <form action="search" method="post">
            <div class="form-group row col-md-5">
                <input type="text" placeholder="wpisz nazwę" name="phrase"
                       class="form-control"/>
            </div>
            <div class="form-group row col-md-5">
                <input type="text" placeholder="wpisz miejscowość" name="location"
                       class="form-control"/>
            </div>
            <div class="form-group row col-md-2">
                <button type="submit" class="btn btn-classic">szukaj</button>
            </div>
        </form>
    </div>
</div>

<div class="container category">
    <c:import url="category.jsp"/>
</div>

<div class="container ad">
    <c:forEach items="${randomAdList}" var="ad">
        <div class="media panel">

            <div class="media-left media-middle">
                <a href="#"> <img class="media-object small-object"
                                  src="http://blog.caranddriver.com/wp-content/uploads/2016/11/Ford-Mustang-Shelby-GT350-lead.jpg"
                                  alt="brak zdjecia">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading"><a href="product.jsp?advertisementId=${ad.id}">${ad.title}</a></h4>
                    ${ad.text}
                <h3 class="price">${ad.price} zł</h3>
            </div>
        </div>
    </c:forEach>
</div>

<footer>
    <div class="container footer form-inline">
        <div class="col-md-3">
            <a href="#">Strona główna</a>
        </div>
        <div class="col-md-3">
            <a href="#">Profil</a>
        </div>
        <div class="col-md-3">
            <a href="#">Aukcje</a>
        </div>
        <div class="col-md-3">
            <a href="#">Kontakt</a>
        </div>
    </div>
</footer>

</body>
