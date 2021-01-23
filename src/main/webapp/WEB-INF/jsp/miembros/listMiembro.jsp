<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="miembros">
    <h2>Miembros</h2>
        <table id="miembrosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>DNI</th>
            <th>Teléfono</th>
            <th>Email</th>
            <th>Usuario</th>  
            <th>Estado</th>   
            <th>Acciones</th>                 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${miembros}" var="miembro">
            <tr>
                <td>                    
                    <c:out value="${miembro.nombre}"/>
                </td>
                <td>
                    <c:out value="${miembro.apellidos}"/>
                </td>
                <td>
                    <c:out value="${miembro.dni}"/>
                </td>  
                <td>
                    <c:out value="${miembro.telefono}"/>
                </td> 
                <td>
                    <c:out value="${miembro.email}"/>
                </td> 
                <td>
                    <c:out value="${miembro.user.username}"/>
                </td>  
                
                 <c:if test="${miembro.user.enabled}">
                 	<td>Habilitado</td>
                 	<td> <spring:url value="/miembros/deshabilitar/{miembroId}" var="ejemplarUrl">
                        <spring:param name="miembroId" value="${miembro.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Deshabilitar</a>
                    </td>
                 </c:if>
                 <c:if test="${!miembro.user.enabled}">
                 	<td>Deshabilitado</td>
                 	<td> <spring:url value="/miembros/habilitar/{miembroId}" var="ejemplarUrl">
                        <spring:param name="miembroId" value="${miembro.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Habilitar</a>
                    </td>
                 </c:if>
                 
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAuthority('admin') || hasAuthority('bibliotecario')">
    	<a class="btn btn-default" href='<spring:url value="/miembros/new" htmlEscape="true"/>'>Añadir miembro</a>
	</sec:authorize>
    
</petclinic:layout>