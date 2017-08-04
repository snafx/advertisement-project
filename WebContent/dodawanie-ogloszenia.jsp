<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- zaciagamy biblioteke jstl -->
<%@ page import="ogloszenia.repository.*,java.util.List,ogloszenia.model.*" %>
<%@ page import="ogloszenia.model.dto.CategoryDTO" %>
<!-- importujemy to co potrzebujemy, nie trzeba servletow -->

<%
   List<CategoryDTO> allCategories = CategoryRepository.findAll();
   pageContext.setAttribute("allCategories", allCategories);
%>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>Serwis z ogloszeniami</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="style.css">

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="function.js"></script>
</head>

<body>

    <div class="container header">
        <div class="logo col-md-2">
            <img src="https://securet9.classistatic.com/assets/images/pl_PL/logo-11309c4e19.png" />
        </div>
        <div class="col-md-6">

        </div>
        <div class="col-md-4 menu">
            <div>Login: testowe</div>

            <div>menu</div>
            <div id="nav-icon1">
                <span></span>
                <span></span>
                <span></span>
            </div>


        </div>
    </div>

    <div class="container">
        <div>
            <form action="/search" method="post">
                <div class="form-group row col-md-5">
                    <input type="text" placeholder="wpisz nazwę" name="phrase" class="form-control" />
                </div>
                <div class="form-group row col-md-5">
                    <input type="text" placeholder="wpisz miejscowość" name="location" class="form-control" />
                </div>
                <div class="form-group row col-md-2">
                    <button type="submit" class="btn btn-classic">szukaj</button>
                </div>
            </form>
        </div>
    </div>


    <div class="container category">
        <div class="col-md-6">
            <h2>Dodaj nowe ogłoszenie</h2>
        </div>

    </div>
    <div class="container ad">
        <form action="ad-new-ad" method="post">
            <div class="form-group">
                <label>Tytuł ogłoszenia</label>
                <input class="form-control" name="title" type="text" required/>
            </div>
            <div class="form-group">
                <label>Cena</label>
                <input class="form-control" name="price" type="number" min="0" step="0.01" required/>
            </div>
            <div class="form-group">
                <label>Lokalizacja</label>
                <input class="form-control" name="location" type="text" required/>
            </div>
            <div class="form-group">
                <label>Opis</label>
                <textarea class="form-control" name="description" required></textarea>
                <input class="form-control" name="img" type="file"/>
            </div>
            <div class="form-group">
                <label>Kategoria</label>
                <select name="category" class="form-control" required>
                <c:forEach item="${categoryList}" var="categoryDTO"
                <option value="${categoryDTO.category}">${categoryDTO.name}</option>
                </c:forEach>
                </select>


            <div class="form-group">
                <button type="submit" class="btn btn-classic">Wyślij</button>
            </div>

        </form>
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
