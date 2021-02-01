<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="editoriales">
<jsp:body>
    <h2>
        Añadir editorial
    </h2>
    <form:form modelAttribute="editorial" class="form-horizontal" id="add-editorial-form" action="/editoriales/save">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="Nombre" name="nombre" />
            <petclinic:inputField label="NIF" name="nif"/>
            <petclinic:inputField label="Dirección" name="direccion"/>
            <petclinic:inputField label="Teléfono" name="telefono"/>
            <petclinic:inputField label="Email" name="email" />
            <petclinic:inputField label="Web" name="web" />
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
           		<input type="hidden" name="id" value="${libro.id}"/>
               	<button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>
