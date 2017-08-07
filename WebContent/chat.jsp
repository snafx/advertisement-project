<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="ogloszenia.repository.*,java.util.List,ogloszenia.model.*,java.util.Optional"%>

<%
    Integer conversationId = Integer.valueOf(request.getParameter("conversationId"));
    Optional<Conversation> conversation = ConversationRepository.findById(conversationId);
    if (conversation.isPresent()) {
        pageContext.setAttribute("conversation", conversation.get());
        List<ConversationMessage> conversationMessages = ConversationMessageRepository.findByConversationId(conversationId);
        pageContext.setAttribute("conversationMessages", conversationMessages);
    }
%>

        <!DOCTYPE html>

        <head>
            <title>Serwis z ogloszeniami</title>
            <meta charset="utf-8">

            <link rel="stylesheet" href="style.css">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


            <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
            <script src="function.js"></script>
        </head>

        <body>

        <!-- header strony -->
        <div class="container header">
            <div class="logo col-md-2">
                <img src="marketlogo.png" alt="brak zdjecia" />
            </div>
            <div class="col-md-6">
            </div>
            <div class="logo col-md-4 menu">

                <c:import url="user-status.jsp" />

                <div id="nav-icon1">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>

            </div>
        </div>

        <!-- wyszukiwarka -->
        <div class="container">
            <div>
                <form action="/search" method="post">
                    <div class="form-group row col-md-5">
                        <input type="text" placeholder="Wpisz nazwę..." name="phrase" class="form-control">
                    </div>
                    <div class="form-group row col-md-5">
                        <input type="text" placeholder="Wpisz miejscowość..." name="location" class="form-control">
                    </div>
                    <div class="form-group row col-md-2">
                        <button type="submit" class="btn btn-classic">Szukaj</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- kategorie -->
        <div class="container category">
            <c:import url="category.jsp" />
        </div>

        <!-- kontener z tytulem ogloszenia -->
        <div class="container category">
            <div class="col-md-6">
                <h2>Ford Mustang</h2>
            </div>
            <div class="col-md-4">
                <h3 class="pull-right">lokalizacja: Poznań</h3>
            </div>
        </div>

        <!-- kontener z contentem -->
        <div class="container ad">

            <div>
                <!-- lewa strona: imie usera, wyslij wiadomosc i inne teksty -->
                <div class="col-md-2">
                    <h5>Dane autora:</h5>
                    <img src="https://iceland-photo-tours.com/wp-content/uploads/2015/02/fb-avatar.jpg" class="avatar" />
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                </div>

                <!-- srodek - czat -->
                <div class="col-md-8">
                    <div class="message-wrapper">


                        <c:forEach items="${conversationMessages}" var="cm">

                            <c:if test="${cm.author.id==8}">
                                <div class="col-md-12">
                                    <div class="panel panel-default my-message">
                                        <div class="panel-heading date-panel ">${cm.createDate}</div>
                                        <div class="panel-body">${cm.messageContent}</div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${cm.author.id !=8}">
                                <div class="col-md-12">
                                    <div class="panel panel-default other-message">
                                        <div class="panel-heading date-panel ">${cm.createDate}</div>
                                        <div class="panel-body">${cm.messageContent}</div>
                                    </div>
                                </div>
                            </c:if>


                        </c:forEach>


                        <!-- wiadomosc AAA -->
                        <!--
                        <div class="col-md-12">
                        <div class="panel panel-default my-conversation">
                            <div class="panel-heading date-panel">
                                Panel heading without title
                            </div>
                            <div class="panel-body">
                                Panel content
                            </div>
                        </div>
                        </div>
                        -->

                        <!-- wiadomosc BBB -->
                        <!--
                        <div class="col-md-12">
                        <div class="panel panel-default other-conversation">
                            <div class="panel-heading date-panel">
                                <h3 class="panel-title">Some other title</h3>
                            </div>
                            <div class="panel-body">
                                Panel content
                            </div>
                        </div>
                        </div>
                        -->

                    </div>

                    <div class="message-container">
                        <form action="/send-message" method="post">
                            <textarea name="message" class="form-control" rows="6"></textarea>
                            <input type="hidden" name="conversationId" value="${conversation.id}"/>
                            <button class="btn btn-classic col-md-12" type="submit">Wyslij</button>
                        </form>
                    </div>
                </div>

                <!-- prawa strona: imie usera, wyslij wiadomosc i inne teksty -->
                <div class="col-md-2">
                    <h5>Dane autora:</h5>
                    <img src="https://iceland-photo-tours.com/wp-content/uploads/2015/02/fb-avatar.jpg" class="avatar" />
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                    <h4>lorem</h4>
                </div>
            </div>

        </div>


        <!-- footer -->
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
