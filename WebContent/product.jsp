<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="ogloszenia.repository.*,java.util.List,ogloszenia.model.*,java.util.Optional"%>

	<%
	Integer advertisementId = Integer.valueOf(request.getParameter("advertisementId"));
	Optional<Advertisement> ad = AdvertisementRepository.findById(advertisementId);
	if(ad.isPresent()){
		pageContext.setAttribute("advertisement", ad.get());
	}
%>

	<c:set value="${advertisement}"
	var="ad" />

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
                    <button type="submit" class="btn btn-classic">Szukaj</button>
                </div>
            </form>
        </div>
    </div>


    <div class="container category">
        <div class="col-md-6">
        <h2>${ad.title}</h2>
        </div>
        <div class="col-md-4">
            <h3 class="pull-right">Lokalizacja: ${ad.cityName}</h3>
        </div>
    </div>

    <div class="container ad">
        <div>
            <div class="col-md-10">

                <div class="col-md-12">
                    <a href="#">
                        <img class="media-object img-responsive center-block" src="http://blog.caranddriver.com/wp-content/uploads/2016/11/Ford-Mustang-Shelby-GT350-lead.jpg" alt="brak zdjeci">
                    </a>
                </div>
                <div class="col-md-12 ad-info">
                    ${ad.text}
                    <h3 class="price">
                        ${ad.price} zł
                    </h3>
                </div>
            </div>
            <div class="col-md-2">
            <h5>Dane autora:</h5>
            <img src="https://iceland-photo-tours.com/wp-content/uploads/2015/02/fb-avatar.jpg" class="avatar" />

            <h5>${ad.owner.nick}</h5>
            <h5>${ad.owner.email}</h5>
            <h5>${ad.owner.cityName}</h5>


            <div class="message-container">
            <form action="add-new-conv" method="post">
            <input type="hidden" name="idAdvertisement" value="${ad.id}"/>
            <textarea name="message" class="form-control" rows="6" required="required"></textarea>
            <button class="btn btn-classic" type="submit">Wyślij</button>
            </form>
            </div>

            </div>
        </div>
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