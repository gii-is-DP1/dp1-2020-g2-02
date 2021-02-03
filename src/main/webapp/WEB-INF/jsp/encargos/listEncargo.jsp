<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="encargos">    
        <h1>Encargos</h1>
        <c:forEach items="${encargos}" var="encargos">
        	<div style="border-radius: 10px;
    			border-color: black;
  				border-style: solid;
    			background-color: white;
    			padding: 1.5%;
    			margin: 1.5%;
    		">
            <h2>${encargos.proveedor.nombre}</h2>
            <em>Realizado el ${encargos.fechaRealizacion} y entregado el ${encargos.fechaEntrega}</em>
            <c:forEach items="${encargos.cantidad}" var="cantidad">
            <p style="margin-top:1%;">Se han pedido ${cantidad.unidades} unidades del libro ${cantidad.libro.titulo}, con un coste por unidad de ${cantidad.precioUnitario} euros</p>
            </c:forEach>
            </div>
        </c:forEach>
    
   <sec:authorize access="hasAuthority('admin') || hasAuthority('bibliotecario')">
    	<a class="btn btn-default" href='<spring:url value="/encargos/new" htmlEscape="true"/>'>Añadir encargo</a>
	</sec:authorize> 
</petclinic:layout>