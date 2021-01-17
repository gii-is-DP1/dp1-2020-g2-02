<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="novedades">
    <h1>Novedades</h1>
        <c:forEach items="${novedades}" var="novedad">
        	<div style="border-radius: 10px;
    			border-color: black;
  				border-style: solid;
    			background-color: white;
    			padding: 1.5%;
    			margin: 1.5%;
    		">
            <h2>${novedad.titulo}</h2>
            <em>Publicada el ${novedad.fechaPublicacion} por ${novedad.bibliotecario.nombre}&nbsp;${novedad.bibliotecario.apellidos}</em>
            <p style="margin-top:1%;">${novedad.contenido}</p>
            </div>
        </c:forEach>
    <sec:authorize access="hasAuthority('bibliotecario')">
		<a class="btn btn-default" href='<spring:url value="/novedades/new" htmlEscape="true"/>'>Añadir novedad</a>
	</sec:authorize>
</petclinic:layout>