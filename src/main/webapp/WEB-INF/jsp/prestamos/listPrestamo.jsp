<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="prestamos">
	
    <h2>Prestamos</h2>
        <table id="prestamosTable" class="table table-striped">
        <thead>
        <tr>
            <th >ID ejemplar</th>
            <th >Libro</th>
            <th >Miembro</th>
            <th >Fecha del préstamo</th>   
            <th >Fecha de devolución</th>
            <th >Concedido por</th>
            <th >Estado</th>
            <th>Acciones</th>              
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prestamos}" var="prestamo">
        <c:if test="${!prestamo.finalizado}">
            <tr>
                <td>                    
                    <c:out value="${prestamo.ejemplar.id}"/>
                </td>
                <td>                    
                    <c:out value="${prestamo.ejemplar.libro.titulo}"/>
                </td>
                <td>
                    <c:out value="${prestamo.miembro.apellidos}"/>, <c:out value="${prestamo.miembro.nombre}"/>
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
                		<td> - </td>
                	</c:when>
                	<c:when test="${prestamo.ejemplar.disponibilidad=='RESERVADO'}">
                		<td>Pendiente de recoger</td>
                		<td>
                			<a href="<c:url value="/prestamos/conceder/${prestamo.id}" />" class="btn btn-default btn-sm" style="min-width:100%; margin-bottom:1%">Conceder</a> 
                			<a href="<c:url value="/prestamos/rechazar/${prestamo.id}" />" class="btn btn-default btn-sm" style="min-width:100%">Rechazar</a>
						</td>
                	</c:when>
                	<c:when test="${prestamo.ejemplar.disponibilidad=='EN_PRESTAMO'}">
                		<td>En préstamo</td>
                		<td>
                			<a href="<c:url value="/prestamos/finalizar/${prestamo.id}" />" class="btn btn-default" style="min-width:100%">Finalizar</a>
                		</td>
                	</c:when>
                </c:choose>
            </tr>
        </c:if>
        </c:forEach>
        </tbody>
    </table>
    
    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/prestamos/new" htmlEscape="true"/>'>Añadir prestamo</a>
	</sec:authorize>

</petclinic:layout>