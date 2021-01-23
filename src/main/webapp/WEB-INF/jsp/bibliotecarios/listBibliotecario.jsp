<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="bibliotecarios">
    <h2>Bibliotecarios</h2>
        <table id="bibliotecariosTable" class="table table-striped">
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
        <c:forEach items="${bibliotecarios}" var="bibliotecario">
            <tr>
                <td>                    
                    <c:out value="${bibliotecario.nombre}"/>
                </td>
                <td>
                    <c:out value="${bibliotecario.apellidos}"/>
                </td>
                <td>
                    <c:out value="${bibliotecario.dni}"/>
                </td>  
                <td>
                    <c:out value="${bibliotecario.telefono}"/>
                </td> 
                <td>
                    <c:out value="${bibliotecario.email}"/>
                </td>
                <td>
                    <c:out value="${bibliotecario.user.username}"/>
                </td>
                <c:if test="${bibliotecario.user.enabled}">
                 	<td>Habilitado</td>
                 	<td> <spring:url value="/bibliotecarios/deshabilitar/{biblioId}" var="ejemplarUrl">
                        <spring:param name="biblioId" value="${bibliotecario.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Deshabilitar</a>
                    </td>
                 </c:if>
                 <c:if test="${!bibliotecario.user.enabled}">
                 	<td>Deshabilitado</td>
                 	<td> <spring:url value="/bibliotecarios/habilitar/{biblioId}" var="ejemplarUrl">
                        <spring:param name="biblioId" value="${bibliotecario.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Habilitar</a>
                    </td>
                 </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/bibliotecarios/new" htmlEscape="true"/>'>Añadir bibliotecario</a>
	</sec:authorize>

</petclinic:layout>