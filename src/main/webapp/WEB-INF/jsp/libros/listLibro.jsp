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
            <th >ISBN</th>
            <th >T�tulo</th>
            <th >Autor</th>
            <th >Idioma</th>   
            <th>G�neros</th>
            <th>Fecha de publicaci�n</th>
            <th>Acciones</th>              
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${libros}" var="libro">
            <tr>
                <td>                    
                    <c:out value="${libro.key.ISBN}"/>
                </td>
                <td>
                    <c:out value="${libro.key.titulo}"/>
                </td>
                <td>
                    <c:out value="${libro.key.autor.nombre} ${libro.key.autor.apellidos}"/>
                </td>     
                
                <td>
                    <c:out value="${libro.key.idioma}"/>
                </td>               
                <td>
                	<c:forEach items="${libro.value}" var="generoLibro">
                    	<c:out value="${generoLibro.genero}"/><br/>
        			</c:forEach>
                </td>  
                <td>
                    <c:out value="${libro.key.fecha_publicacion}"/>
                </td> 
                <td>
                	<spring:url value="/libros/delete/{libroId}" var="libroUrl">
                        <spring:param name="libroId" value="${libro.key.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(libroUrl)}">Borrar libro</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <br/> 
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/libros/new" htmlEscape="true"/>'>A�adir libro</a>
	</sec:authorize>
</petclinic:layout>