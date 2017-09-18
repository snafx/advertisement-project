<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ogloszenia.model.Advertisement,ogloszenia.repository.AdvertisementRepository,java.util.List" %>

<%
    String phrase = request.getParameter("phrase");
    String location = request.getParameter("location");
    if (phrase.isEmpty()) {
        pageContext.setAttribute("WARNING", "Proszę wypełnić szukaną frazę!!");
    } else if (location == null || location.isEmpty()) {
        List<Advertisement> ad = AdvertisementRepository.findByPhrase(phrase);
        pageContext.setAttribute("searchedAds", ad);
    } else {
        List<Advertisement> ad = AdvertisementRepository.findByPhraseAndLocation(phrase, location);
        pageContext.setAttribute("searchedAds", ad);
    }
%>

<c:set value="${AdvertisementRepository.findByCategory(category)}"
       var="adList"/>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Serwis z ogloszeniami</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="function.js"></script>
</head>
<body>

<div class="container header">
    <c:import url="top-menu.jsp"></c:import>
</div>

<div class="container">
    <div>
        <form action="/search" method="post">
            <div class="form-group row col-md-5">
                <input type="text" placeholder="wpisz nazwę" name="phrase"
                       class="form-control"/>
            </div>
            <div class="form-group row col-md-5">
                <input type="text" placeholder="wpisz miejscowość"
                       name="location" class="form-control"/>
            </div>
            <div class="form-group row col-md-2">
                <button type="submit" class="btn btn-classic">szukaj</button>
            </div>
        </form>
    </div>
</div>

<!-- kategorie -->
<div class="container category">
    <c:import url="category.jsp"/>
</div>
<div class="container category">
    <div class="col-md-7">
        <h2>${categoryName}</h2>
    </div>
    <div class="col-md-5">
        <div class="col-md-4 sorting-right">
            <label class="sorting-label">Sortuj po:</label>
        </div>
        <div class="col-md-8">
            <select class="form-control">
                <option value="1">cena malejaco</option>
                <option value="2">cena rosnąco</option>
                <option value="3">data: od najstarszych</option>
                <option value="4">data: od najnowszych</option>
            </select>
        </div>
    </div>
</div>

<!-- kontener z contentem -->
<div class="container ad">
    ${WARNING}
    <c:forEach items="${searchedAds}" var="ad">
        <div class="media panel">
            <div class="media-left media-middle">
                <a href="product.jsp?advertisementId=${ad.id}"> <img
                        class="media-object small-object"
                        src="http://blog.caranddriver.com/wp-content/uploads/2016/11/Ford-Mustang-Shelby-GT350-lead.jpg"
                        alt="brak zdjecia">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">
                    <a href="product.jsp?advertisementId=${ad.id}">${ad.title}</a>
                </h4>
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
