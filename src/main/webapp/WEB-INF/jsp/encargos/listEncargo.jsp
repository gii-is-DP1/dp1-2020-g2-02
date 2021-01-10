<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="encargos">
    <h2>Encargos</h2>
        <table id="encargosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Proveedor</th>
            <th>Libro</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Fecha Realización</th> 
            <th>Fecha Entrega</th>             
        </tr>
        </thead>
        <tbody>
         
        <c:forEach items="${encargos}" var="encargos">
            <tr>
                <td>                    
                    <c:out value="${encargos.proveedor.nombre} "/>
                </td>
                <td>
                <c:forEach items="${encargos.cantidad}" var="cantidad">
               		<c:out value="${cantidad.libro.titulo}"/><br/>
               		</c:forEach>
                </td>
                
                <td>
                <c:forEach items="${encargos.cantidad}" var="cantidad">
                   <c:out value="${cantidad.unidades}"/><br/>
                   </c:forEach>
                </td>  
                <td>
                <c:forEach items="${encargos.cantidad}" var="cantidad">
                   <c:out value="${cantidad.precioUnitario}"/><br/>
                   </c:forEach>
                </td>
                <td>
                    <c:out value="${encargos.fechaRealizacion}"/>
                </td>
                <td>
                    <c:out value="${encargos.fechaEntrega}"/>
                </td>                
            </tr>
            </c:forEach>
        
        </tbody>
    </table>
   <%--     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/encargos/new" htmlEscape="true"/>'>Añadir encargo</a>
	</sec:authorize>--%> 
</petclinic:layout>