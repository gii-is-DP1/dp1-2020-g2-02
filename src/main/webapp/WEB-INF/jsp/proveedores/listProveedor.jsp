<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="proveedores">
    <h2>Proveedores</h2>
        <table id="proveedoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>NIF</th>
            <th>Dirección</th> 
            <th>Teléfono</th>
            <th>Email</th>  
            <th>Estado</th>   
            <th>Acciones</th>                
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${proveedor}" var="proveedor">
            <tr>
                <td>                    
                    <c:out value="${proveedor.nombre}"/>
                </td>
                <td>
                    <c:out value="${proveedor.nif}"/>
                </td>
                <td>
                    <c:out value="${proveedor.direccion}"/>
                </td>  
                <td>
                    <c:out value="${proveedor.telefono}"/>
                </td> 
                <td>
                    <c:out value="${proveedor.email}"/>
                </td>
                
                 <c:if test="${proveedor.user.enabled}">
                 	<td>En uso</td>
                 	<td> <spring:url value="/proveedores/deshabilitar/{proveedorId}" var="ejemplarUrl">
                        <spring:param name="proveedorId" value="${proveedor.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Dejar de usar</a>
                    </td>
                 </c:if>
                 <c:if test="${!proveedor.user.enabled}">
                 	<td>No se usa</td>
                 	<td> <spring:url value="/proveedores/habilitar/{proveedorId}" var="ejemplarUrl">
                        <spring:param name="proveedorId" value="${proveedor.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Volver a usar</a>
                    </td>
                 </c:if>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAuthority('admin') || hasAuthority('bibliotecario')">
    	<a class="btn btn-default" href='<spring:url value="/proveedores/new" htmlEscape="true"/>'>Añadir proveedor</a>
	</sec:authorize>
</petclinic:layout>