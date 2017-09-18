<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- importujemy to c opotrzebujemy, nie trzeba servletow -->

<c:set value="${CategoryRepository.findAll()}" var="categoryList"/>

<c:forEach items="${categoryList}" var="categoryDTO">
    <div class="col-md-3">
		<span class="category-item fa ${categoryDTO.iconName}">
            <a href="products.jsp?category=${categoryDTO.category}"> ${categoryDTO.name}</a>
		</span>
    </div>
</c:forEach>