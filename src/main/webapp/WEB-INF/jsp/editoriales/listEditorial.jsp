<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="Editoriales">
	
    <h2>Editoriales</h2>
        <table id="EditorialesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>NIF</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Email</th>   
            <th>Página web</th>   
            <th>Acciones</th> 
        </tr>
        </thead>
        <tbody>
       
        
        <c:forEach items="${editoriales}" var="editorial">
            <tr>
                <td>                    
                    <c:out value="${editorial.nombre}"/>
                </td>
                <td>
                	<c:out value="${editorial.nif}"/>
                </td>
                <td>
                    <c:out value="${editorial.direccion}"/>
                </td>
                <td>
                    <c:out value="${editorial.telefono}"/>
                </td>  
                <td>
                    <c:out value="${editorial.email}"/>
                </td>  
                <td>
                    <c:out value="${editorial.web}"/>
                </td>  
                <td>
                	<spring:url value="/editoriales/{editorialId}" var="editorialUrl">
                        <spring:param name="editorialId" value="${editorial.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(editorialUrl)}">Ver libros</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <br/> 
    <sec:authorize access="hasAuthority('admin') || hasAuthority('bibliotecario')">
		<a class="btn btn-default" href='<spring:url value="/libros/new" htmlEscape="true"/>'>Añadir libro</a>
	</sec:authorize>
</petclinic:layout>