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
            <petclinic:inputField label="Nombre Proveedor" name="proveedor"/>
            <%--<petclinic:inputField label="Libro" name="Libro"/>--%>
            <petclinic:inputField label="Fecha Realización" name="fechaRealizacion"/>
            <petclinic:inputField label="Fecha Entrega" name="fechaEntrega"/>
            <petclinic:inputField label="Cantidad" name="cantidad"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <input type="hidden" name="id" value="${encargo.id}"/>
               <button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>