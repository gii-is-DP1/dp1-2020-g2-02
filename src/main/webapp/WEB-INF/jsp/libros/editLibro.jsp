<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="libros">
<jsp:body>
    <h2>
        Añadir libro
    </h2>
    <form:form modelAttribute="libro" class="form-horizontal" id="add-libro-form" action="/libros/save">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="ISBN" name="ISBN"/>
            <petclinic:inputField label="Título" name="titulo"/>
            <petclinic:inputField label="Autor" name="autores"/>
            <petclinic:inputField label="Idioma" name="titulo"/>
            <petclinic:inputField label="Géneros" name="generos"/>
            <petclinic:inputField label="Editorial" name="editorial.nombre"/>
            <petclinic:inputField label="Fecha publicación" name="fecha_publicacion"/>
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