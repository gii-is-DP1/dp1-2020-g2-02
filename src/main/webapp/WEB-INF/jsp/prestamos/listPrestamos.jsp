<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="prestamo">
	
    <h2>Prestamos</h2>
        <table id="PrestamosTable" class="table table-striped">
        <thead>
        <tr>
            <th >Ejemplar</th>
            <th >Miembro</th>
            <th >Bibliotecario</th>
            <th >FechaPrestamo</th>   
            <th >FechaDevolución</th>
            <th>Acciones</th>              
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${prestamo}" var="prestamo">
            <tr>
                <td>                    
                    <c:out value="${prestamo.ejemplar_id}"/>
                </td>
                <td>
                    <c:out value="${prestamo.miembro_id}"/>
                </td>
                <td>
                    <c:out value="${prestamo.bibliotecario_id}"/>
                </td> 
                <td>
                    <c:out value="${prestamo.fecha_prestamo}"/>
                </td>  
                <td>
                    <c:out value="${prestamo.fecha_devolucion}"/>
                </td>  
                <td>
                	<spring:url value="/prestamo/delete/{prestamoId}" var="prestamoUrl">
                        <spring:param name="prestamoId" value="${prestamo.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(libroUrl)}">Borrar prestamo</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/libros/new" htmlEscape="true"/>'>Añadir prestamo</a>
	</sec:authorize>

</petclinic:layout>