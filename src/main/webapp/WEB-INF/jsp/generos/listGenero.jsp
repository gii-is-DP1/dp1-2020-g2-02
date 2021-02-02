<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="generos">
    <h1>Géneros disponibles</h1>
    
        <c:forEach items="${generos}" var="genero">
        	<div style="border-radius: 10px;
    			border-color: black;
  				border-style: solid;
    			background-color: white;
    			padding: 1%;
    			margin: 1.5%;
    		">
            <h4>${genero.genero}</h4>
           
            </div>
        </c:forEach>
        <br>
    <sec:authorize access="hasAuthority('admin') || hasAuthority('bibliotecario')">
		<a class="btn btn-default" href='<spring:url value="/novedades/new" htmlEscape="true"/>'>Añadir género</a>
	</sec:authorize>
</petclinic:layout>