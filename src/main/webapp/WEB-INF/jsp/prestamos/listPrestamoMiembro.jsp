<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="prestamos">
	
    <h2>Mis prestamos</h2>
        <table id="prestamosTable" class="table table-striped">
        <thead>
        <tr>
            <th >Libro</th>
            <th >Fecha del préstamo</th>   
            <th >Fecha de devolución</th>
            <th >Concedido por</th>
            <th >Estado</th> 
            <th >Tu valoración</th> 
            <th ></th>            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prestamos}" var="prestamo">
            <tr>
                <td>                    
                    <c:out value="${prestamo.ejemplar.libro.titulo}"/>
                </td>
                <td>
                    <c:out value="${prestamo.fechaPrestamo}"/>
                </td>  
                <td>
                    <c:out value="${prestamo.fechaDevolucion}"/>
                </td>
                <td>
                	<c:if test="${empty prestamo.bibliotecario}">
                		-
                	</c:if>
                	<c:if test="${not empty prestamo.bibliotecario}">
                    	<c:out value="${prestamo.bibliotecario.apellidos}"/>, <c:out value="${prestamo.bibliotecario.nombre}"/>
                    </c:if>
                </td>
                <c:choose>
                	<c:when test="${prestamo.finalizado}">
                		<td>Finalizado</td>
                	</c:when>
                	<c:when test="${prestamo.ejemplar.disponibilidad=='RESERVADO'}">
                		<td>Pendiente de recoger</td>
                	</c:when>
                	<c:when test="${prestamo.ejemplar.disponibilidad=='EN_PRESTAMO'}">
                		<td>En préstamo</td>
                	</c:when>
                </c:choose>
                <td>
                	<c:set var="punt" value="-"/>
                	<c:forEach items="${puntuaciones}" var="puntuacion">
                		<c:if test="${puntuacion.key.id == prestamo.id}">
                			<c:set var="punt" value="${puntuacion.value.puntaje}"/>
                		</c:if> 
                	</c:forEach>
                	<c:out value="${punt}"/>
                </td>
                <td>
                	<sec:authorize access="hasAuthority('miembro')">
                		<spring:url value="/puntuacion/valorar/{libroId}" var="libroUrl">
                         	<spring:param name="libroId" value="${prestamo.ejemplar.libro.id}"/>
                     	</spring:url>
                     	<a href ="${fn:escapeXml(libroUrl)}">Valorar</a>
                	</sec:authorize>
                </td>
                  
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <br/> 

</petclinic:layout>