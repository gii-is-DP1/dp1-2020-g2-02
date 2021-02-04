<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="sugerencias">
<jsp:body>
    <h2>
        Añadir Sugerencia
    </h2>
    <form:form modelAttribute="sugerencia" class="form-horizontal" id="add-sugerencia-form" action="/sugerencias/save">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Título" name="tituloLibro"/>
            <petclinic:inputField label="Autor" name="nombreAutor"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
               <button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>