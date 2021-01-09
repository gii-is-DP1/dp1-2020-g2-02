<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

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
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/proveedores/new" htmlEscape="true"/>'>Añadir proveedor</a>
	</sec:authorize>
</petclinic:layout>