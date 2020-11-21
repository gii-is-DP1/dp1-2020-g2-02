<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Libros">
	
    <h2>Libros</h2>
        <table id="LibrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 25%;">ISBN</th>
            <th style="width: 25%;">Título</th>
            <th style="width: 20%;">Idioma</th>   
            <th style="width: 20%;">Fecha de publicación</th> 
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
                    <c:out value="${libro.idioma}"/>
                </td>  
                <td>
                    <c:out value="${libro.fecha_publicacion}"/>
                </td> 
                <td>
                	<spring:url value="/libros/delete/{libroId}" var="libroUrl">
                        <spring:param name="libroId" value="${libro.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(libroUrl)}">Borrar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>