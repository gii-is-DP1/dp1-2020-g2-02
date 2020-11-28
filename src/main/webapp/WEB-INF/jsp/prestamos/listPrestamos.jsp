<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="Libros">
	
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
                    <c:out value="${libro.miembro_id}"/>
                </td>
                <td>
                    <c:out value="${libro.bibliotecario_id}"/>
                </td> 
                <td>
                    <c:out value="${libro.fecha_prestamo}"/>
                </td>  
                <td>
                    <c:out value="${libro.fecha_devolucion}"/>
                </td>  
                <td>
                	<spring:url value="/libros/delete/{libroId}" var="libroUrl">
                        <spring:param name="libroId" value="${libro.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(libroUrl)}">Borrar libro</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/libros/new" htmlEscape="true"/>'>Añadir libro</a>
	</sec:authorize>

</petclinic:layout>