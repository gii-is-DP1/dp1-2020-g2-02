<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="sugerencias">
	
	<h1>Sugerencias</h1>
	
	
	
        <c:forEach items="${sugerencias}" var="sugerencias">
        	<div style="border-radius: 10px;
    			border-color: black;
  				border-style: solid;
    			background-color: white;
    			padding: 1.5%;
    			margin: 1.5%;
    		">
            <h2>${sugerencias.tituloLibro}</h2>
            <em style="margin-top:1%;">Autor: ${sugerencias.nombreAutor}</em>
            <p>Sugerencia por: ${sugerencias.miembro.nombre}&nbsp;${sugerencias.miembro.apellidos}</p>
            </div>
        </c:forEach>
	<br>
	
	<sec:authorize access="hasAuthority('miembro')">
		<a class="btn btn-default"
			href='<spring:url value="/sugerencias/new" htmlEscape="true"/>'>Añadir sugerencia</a>
	</sec:authorize>
</petclinic:layout>