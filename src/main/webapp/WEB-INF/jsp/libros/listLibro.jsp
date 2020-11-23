<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="Libros">
	
    <h2>Libros</h2>
        <table id="LibrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 15%;">ISBN</th>
            <th style="width: 25%;">Título</th>
            <th style="width: 25%;">Autor</th>
            <th style="width: 15%;">Idioma</th>   
            <th style="width: 10%;">Fecha de publicación</th>
            <th style="width: 10%;">Acciones</th>              
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${libros}" var="libro">
            <tr>
                <td>                    
                    <c:out value="${libro.ISBN}"/>
                </td>
                <td>
                    <c:out value="${libro.titulo}"/>
                </td>
                <td>
                    <c:out value="${libro.autor.nombre} ${libro.autor.apellidos}"/>
                </td> 
                <td>
                    <c:out value="${libro.idioma}"/>
                </td>  
                <td>
                    <c:out value="${libro.fecha_publicacion}"/>
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