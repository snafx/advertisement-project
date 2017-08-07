<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- zaciagamy biblioteke jstl -->
<%@ page import="ogloszenia.repository.*,java.util.List,ogloszenia.model.*" %>
<%@ page import="java.util.Optional" %>
<!-- importujemy to c opotrzebujemy, nie trzeba servletow -->

<%
    Integer userId = (Integer) request.getSession().getAttribute("userId");
    if (userId != null) {
        Optional<User> user = UserRepository.findById(userId);
        if (user.isPresent()) {
            String nick = user.get().getNick();
            pageContext.setAttribute("nick", nick);

        }
    }
%>

<div>
    <c:if test="${! empty nick}">
        Login: ${nick} <a href="/logout">(wyloguj się)</a>
    </c:if>
    <c:if test="${empty nick}">
        <a href="login.jsp">Zaloguj się</a> / <a href="add-new-user.jsp">Zarejestruj się</a>
    </c:if>
</div>
