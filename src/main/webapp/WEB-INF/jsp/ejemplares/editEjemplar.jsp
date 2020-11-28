<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="ejemplares">
<jsp:body>
    <h2>
        Ejemplares
    </h2>
    <form:form modelAttribute="ejemplar" class="form-horizontal" id="add-ejemplar-form" action="/ejemplares/save">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Disponibilidad" name="disponibilidad"/>
            <petclinic:areaField label="Estado" name="estado"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
           		<input type="hidden" name="id" value="${ejemplar.id}"/>
               	<button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>