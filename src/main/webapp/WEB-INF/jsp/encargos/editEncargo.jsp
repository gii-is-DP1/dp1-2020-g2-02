<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="encargos">
<jsp:body>
    <h2>
        Encargo
    </h2>
    <form:form modelAttribute="encargo" class="form-horizontal" id="add-encargo-form" action="/encargos/save">
        <div class="form-group has-feedback">
            <petclinic:selectFieldMap name="proveedor" label="Proveedor" names="${listaProveedores}" size="5"/>
            <petclinic:inputField label="Fecha Entrega" name="fechaEntrega"/>
            
            	<div class="row" style="margin-bottom:1.5%">
            		<label class="col-sm-2 control-label">Añadir libro</label>
            		<div class="col-sm-10">
            			<select class="form-control" size="5" name="libroEncargo">
  					 	<c:forEach items="${listaLibros}" var="libroLista">
  					 		<option value="${libroLista.id}">${libroLista.titulo}</option>
  					 	</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
            		<label class="col-sm-2 control-label">Cantidad y Precio</label>
            		<div class="col-sm-2">
						<input class="form-control" type="number" name="numEjemplares" id="numEjemplares" value="1"/>
					</div>
					<div class="col-sm-2">
						<input class="form-control" type="text" name="precioUnitario" id="precioUnitario" value="1"/>
					</div>
					<div class="col-sm-1">
						<input class="btn btn-default" type="submit" name="addLibro" value="Añadir libro al encargo">
					</div>
				</div>
				<div style="border-radius: 10px;
    			border-color: black;
  				border-style: solid;
    			background-color: white;
    			padding: 1.5%;
    			margin: 3.5%;
    			">
				<c:forEach items="${lineasPedido}" var="lineaPedido">
				<p>${lineaPedido.libro.titulo} x${lineaPedido.unidades} - ${lineaPedido.precioUnitario} Euros/ud</p>
				</c:forEach>
				</div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            <c:set var = "now" value = "<%= new java.util.Date()%>" />
               <input type="hidden" name="id" value="${encargo.id}"/>
               <input type="hidden" name="fechaRealizacion" value="<fmt:formatDate pattern = "dd/MM/yyyy" value = "${now}" />"/>
               <button class="btn btn-default" type="submit" name="guardar">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>