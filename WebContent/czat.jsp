<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ogloszenia.model.Conversation,ogloszenia.model.ConversationMessage,ogloszenia.repository.ConversationMessageRepository,ogloszenia.repository.ConversationRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>

<%
    Integer conversationId = Integer.valueOf(request.getParameter("conversationId"));
    Optional<Conversation> conversation = ConversationRepository.findById(conversationId);
    if (conversation.isPresent()) {
        pageContext.setAttribute("conversation", conversation.get());
        List<ConversationMessage> conversationMessages = ConversationMessageRepository
                .findByConversationId(conversationId);
        pageContext.setAttribute("conversationMessages", conversationMessages);
    }
%>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>Serwis z ogloszeniami</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
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

<div class="container category">
    <c:import url="category.jsp"/>
</div>

<div class="container category">
    <div class="col-md-6">
        <h2>Ford Mustang</h2>
    </div>
    <div class="col-md-4">
        <h3 class="pull-right">Lokalizacja: Poznań</h3>
    </div>
</div>

<div class="container ad">
    <div>
        <div class="col-md-2">
            <h5>Dane autora:</h5>
            <img
                    src="img/face.png"
                    class="avatar"/>
            <h5>Imie uzytkownika</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>

        </div>

        <div class="col-md-8">
            <div class="message-wrapper">
                <c:forEach items="${conversationMessages}" var="cm">
                    <c:if test="${cm.owner.id==1}">
                        <div class="col-md-12">
                            <div class="panel panel-default my-message">
                                <div class="panel-heading date-panel ">${cm.createDate}</div>
                                <div class="panel-body">${cm.messageContent}</div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${cm.owner.id !=1}">
                        <div class="col-md-12">
                            <div class="panel panel-default other-message">
                                <div class="panel-heading date-panel ">${cm.createDate}</div>
                                <div class="panel-body">${cm.messageContent}</div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="message-container">
                <form action="send-message" method="post">
                    <textarea name="message" class="form-control" rows="6"></textarea>
                    <input type="hidden" name="conversationId"
                           value="${conversation.id}"/>
                    <button class="btn btn-classic col-md-12" type="submit">Wyslij</button>
                </form>
            </div>
        </div>
        <div class="col-md-2">

            <h5>Dane autora:</h5>
            <img
                    src="img/face.png"
                    class="avatar"/>
            <h5>Imie uzytkownika</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
            <h5>lorem</h5>
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
