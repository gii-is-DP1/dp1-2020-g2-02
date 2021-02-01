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
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            <c:set var = "now" value = "<%= new java.util.Date()%>" />
               <input type="hidden" name="id" value="${encargo.id}"/>
               <input type="hidden" name="fechaRealizacion" value="<fmt:formatDate pattern = "dd/MM/yyyy" value = "${now}" />"/>
               <button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>