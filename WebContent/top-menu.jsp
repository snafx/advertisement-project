<%@ page language="java" contentType="text/html; harset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ogloszenia.model.User,ogloszenia.repository.UserRepository,java.util.Optional" %>

<%
    Integer userId = (Integer) request.getSession().getAttribute("userId");
    pageContext.setAttribute("userId", userId);
    if (userId != null) {
        Optional<User> user = UserRepository.findById(userId);
        if (user.isPresent()) {
            String nick = user.get().getNick();
            pageContext.setAttribute("nick", nick);
        }
    }
%>

<div class="logo col-md-2">
    <a href="index.jsp"><img src="img/marketlogo.png"></a>

</div>
<div class="col-md-6"></div>
<div class="col-md-4 menu">
    <div>
        <c:if test="${! empty nick}">
            Login: ${nick} <a href="logout">(wyloguj się)</a>
        </c:if>
        <c:if test="${empty nick}">
            <a href="login.html">Zaloguj się</a> / <a href="add-new-user.html">Zarejestruj się</a>
        </c:if>
    </div>
</div>

<div class="dropdown">
    <button onclick="myFunction()" class="dropbtn">Menu</button>
    <div id="myDropdown" class="dropdown-content">
        <a href="dodawanie-ogloszenia.jsp">Dodaj ogloszenie</a>
    </div>
</div>